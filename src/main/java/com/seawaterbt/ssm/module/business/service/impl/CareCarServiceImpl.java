package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.module.business.dao.CareInstallMapper;
import com.seawaterbt.ssm.module.business.entity.CareCar;
import com.seawaterbt.ssm.module.business.dao.CareCarMapper;
import com.seawaterbt.ssm.module.business.entity.CareInstall;
import com.seawaterbt.ssm.module.business.service.ICareCarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2020-03-20
 */
@Service
public class CareCarServiceImpl extends ServiceImpl<CareCarMapper, CareCar> implements ICareCarService {

    @Resource
    private CareCarMapper careCarMapper;

    @Resource
    private CareInstallMapper careInstallMapper;

    @Override
    public Page<Map<String, Object>> getCareCarList(Map<String, Object> params) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage(params);
        List<Map<String, Object>> list = careCarMapper.selectCareCarList(page, params);
        for (Map<String, Object> map : list) {
            String[] array = ((String) map.getOrDefault("primaryKey", StringUtils.EMPTY)).split(",");
            StringBuilder key = new StringBuilder();
            for (String s : array) {
                if (StringUtils.isNotBlank(s)) {
                    String a = Integer.toHexString(Integer.parseInt(s.trim()));
                    if (a.length() == 1) {
                        a = "0" + a;
                    }
                    key.append(a);
                }
            }
            map.put("primaryKey", key.toString().toUpperCase());
        }
        page.setRecords(list);
        return page;
    }

    @Override
    public boolean saveCareCar(CareCar careCar) {
        careCar.setCreatedAt(LocalDateTime.now());
        setCarData(careCar);
        int count = careCarMapper.insert(careCar);
        return count >= 1;
    }

    @Override
    public boolean updateCareCar(CareCar careCar) {
        setCarData(careCar);
        int count = careCarMapper.updateById(careCar);
        return count >= 1;
    }

    @Override
    @Transactional
    public boolean removeCareCar(Integer carId) {
        CareCar car = careCarMapper.selectById(carId);
        careCarMapper.deleteById(carId);
        careInstallMapper.delete(new UpdateWrapper<>(new CareInstall().setSn(Long.parseLong(car.getSn()))));
        return true;
    }

    @Override
    public Map<String, Object> saveCareCarBatch(List<CareCar> list) {
        Map<String, Object> result = new HashMap<>();
        Integer code = 0;
        Map<String, CareCar> successMap = new HashMap<>();
        Map<String, CareCar> errorMap = new HashMap<>();
        try {
            List<CareCar> careCars = new ArrayList<>();

            for (CareCar careCar : list) {
                if (!careCars.contains(careCar)) {
                    careCars.add(careCar);
                    successMap.put(String.valueOf(careCar.getSn()), careCar);
                } else {
                    errorMap.put(String.valueOf(careCar.getSn()), careCar);
                }
            }
            // care_car 存在的编码
            if (!successMap.keySet().isEmpty()) {
                List<String> exitList = careCarMapper.selectCareCarSnList(new ArrayList<>(successMap.keySet()));
                for (Map.Entry<String, CareCar> entry : successMap.entrySet()) {
                    String sn = entry.getKey();
                    CareCar careCar = entry.getValue();
                    if (exitList.contains(sn)) {
                        errorMap.put(sn, careCar);
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

        for (Map.Entry<String, CareCar> entry : successMap.entrySet()) {
            CareCar careCar = entry.getValue();
            careCar.setCreatedAt(LocalDateTime.now());
            setCarData(careCar);
            careCarMapper.insert(careCar);
        }
        result.put("code", code);
        result.put("msg", "成功导入" + successMap.keySet().size() + "记录，失败" + errorMap.keySet().size() + "条");
        result.put("errorList", errorMap.values());
        return result;
    }

    private void setCarData(CareCar careCar) {
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        String key = careCar.getPrimaryKey();
        StringBuilder primaryKey = new StringBuilder();
        for (int i = 0; i < key.length(); i = i + 2) {
            String temp = key.substring(i, i + 2);
            long dec_num = Long.parseLong(temp, 16);
            if (primaryKey.length() > 0) {
                primaryKey.append(",");
            }
            primaryKey.append(dec_num);
        }
        careCar.setPrimaryKey(primaryKey.toString());
        careCar.setMac(careCar.getMac().toLowerCase());
        careCar.setCarnum(careCar.getSn());
        careCar.setSid(careCar.getCarnum());
        careCar.setSimnum(careCar.getCarnum());
        careCar.setOperator(shiroUser.getAccount());
        careCar.setUpdatedAt(LocalDateTime.now());
    }

    public static void main(String[] args) {
        String key = "49 91 86 06 6D 67 FA 07 EC 01 9F8DBF7BC0C4";
        String abc = "49 91 86 6  6D 67 FA  7 EC  1 9F8DBF7BC0C4";
//        System.out.println(key.length());
//        key = "2712DE1078BF7BA55BBD33B7F77B522F";
//        System.out.println(key.length());
//        key = "276BDE1078FF33775BC433B7F77A522F";
//        System.out.println(key.length());
//        key = "277BDE1078DF33675BD433B7F7DA522F";
//        System.out.println(key.length());
//        key = "2788DE1078DF33755B2733B7F7DF522F";
//        System.out.println(key.length());
//        System.out.println(key.length());
//        StringBuilder primaryKey = new StringBuilder();
//        for (int i = 0; i < key.length(); i = i + 2) {
//            String temp = key.substring(i, i + 2);
//            long dec_num = Long.parseLong(temp, 16);
//            if (primaryKey.length() > 0) {
//                primaryKey.append(",");
//            }
//            primaryKey.append(dec_num);
//        }
        String a = String.format("%02d", 1);
        System.out.println(a);
    }


}
