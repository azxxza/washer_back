package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.module.business.dao.CareCarMapper;
import com.seawaterbt.ssm.module.business.dao.CareInstallMapper;
import com.seawaterbt.ssm.module.business.entity.CareInstall;
import com.seawaterbt.ssm.module.business.service.ICareInstallService;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-07
 */
@Service
public class CareInstallServiceImpl extends ServiceImpl<CareInstallMapper, CareInstall> implements ICareInstallService {

    @Resource
    private CareInstallMapper careInstallMapper;

    @Resource
    private CareCarMapper careCarMapper;

    @Override
    public Page<CareInstall> getCareInstallPage(Map<String, Object> params) {
        Page<CareInstall> page = new PageFactory<CareInstall>().defaultPage(params);
        List<CareInstall> list = careInstallMapper.selectCareInstallList(page, params);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<CareInstall> getCareInstallList(Map<String, Object> params) {
        return careInstallMapper.selectCareInstallList(null, params);
    }

    @Override
    public List<Map<String, Object>> getBasicInfo(String sn) {
        return careInstallMapper.selectBasicInfo(sn);
    }

    @Override
    public boolean saveCareInstall(CareInstall careInstall) {
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        careInstall.setCreatedAt(LocalDateTime.now());
        careInstall.setUpdatedAt(LocalDateTime.now());
        careInstall.setOperator(shiroUser.getAccount());
        int count = careInstallMapper.insert(careInstall);
        return count >= 1;
    }

    @Override
    public boolean deleteCareInstall(int id) {
        int count = careInstallMapper.deleteById(id);
        return count >= 1;
    }

    @Transactional
    @Override
    public Map<String, Object> saveCareInstallBatch(List<CareInstall> list) {

        Map<String, Object> result = new HashMap<>();
        Integer code = 0;
        Map<String, CareInstall> successMap = new HashMap<>();
        Map<String, CareInstall> errorMap = new HashMap<>();
        try {
            List<CareInstall> careInstalls = new ArrayList<>();

            for (CareInstall careInstall : list) {
                if (!careInstalls.contains(careInstall)) {
                    careInstalls.add(careInstall);
                    successMap.put(String.valueOf(careInstall.getSn()), careInstall);
                } else {
                    errorMap.put(String.valueOf(careInstall.getSn()), careInstall);
                }
            }
            // care_car 存在的编码
            if (!successMap.keySet().isEmpty()) {
                List<String> exitList = careCarMapper.selectCareCarSnList(new ArrayList<>(successMap.keySet()));
                for (Map.Entry<String, CareInstall> entry : successMap.entrySet()) {
                    String sn = entry.getKey();
                    CareInstall careInstall = entry.getValue();
                    if (!exitList.contains(sn)) {
                        errorMap.put(sn, careInstall);
                    }
                }
                for (String errorId : errorMap.keySet()) {
                    successMap.remove(errorId);
                }
            }
            // 判断在安装信息中是否存在编码
            if (!successMap.keySet().isEmpty()) {
                List<String> exitList = careInstallMapper.selectSnList(new ArrayList<>(successMap.keySet()));
                for (Map.Entry<String, CareInstall> entry : successMap.entrySet()) {
                    String sn = entry.getKey();
                    CareInstall careInstall = entry.getValue();
                    if (exitList.contains(sn)) {
                        errorMap.put(sn, careInstall);
                    }
                }
                for (String errorId : errorMap.keySet()) {
                    successMap.remove(errorId);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", code);
            result.put("msg", "失败");
            return result;
        }

        for (Map.Entry<String, CareInstall> entry : successMap.entrySet()) {
            CareInstall careInstall = entry.getValue();
            careInstallMapper.insert(careInstall);
        }
        result.put("code", code);
        result.put("msg", "成功导入" + successMap.keySet().size() + "记录，失败" + errorMap.keySet().size() + "条");
        result.put("errorList", errorMap.values());
        return result;
    }

    @Override
    public boolean updateCareInstall(CareInstall careInstall) {
        ShiroUser shiroUser =(ShiroUser) SecurityUtils.getSubject().getPrincipal();
        careInstall.setOperator(shiroUser.getAccount());
        careInstall.setUpdatedAt(LocalDateTime.now());
        int count = careInstallMapper.updateById(careInstall);
        return count >= 1;
    }

}
