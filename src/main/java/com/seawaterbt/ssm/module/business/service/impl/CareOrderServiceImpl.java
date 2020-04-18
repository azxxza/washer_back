package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.dao.*;
import com.seawaterbt.ssm.module.business.entity.*;
import com.seawaterbt.ssm.module.business.service.ICareAgentSamplingService;
import com.seawaterbt.ssm.module.business.service.ICareOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
@Service
public class CareOrderServiceImpl implements ICareOrderService {

    @Resource
    private CareOrderMapper careOrderMapper;

    @Resource
    private CareCostMapper careCostMapper;

    @Resource
    private CareWasherbindMapper careWasherbindMapper;

    @Resource
    private CareWasherusermonthcardMapper careWasherusermonthcardMapper;

    @Resource
    private CareWasherlastprepayinfoMapper careWasherlastprepayinfoMapper;

    @Resource
    private CareWasherprepayinfoMapper careWasherprepayinfoMapper;

    @Override
    public Page<Map<String, Object>> getCareOrderPage(Map<String, Object> params) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage(params);
        List<Map<String, Object>> list = careOrderMapper.selectCareOrderList(page, params);
        page.setRecords(list);
        return page;
    }

    @Transactional
    public boolean createOrder(String wechatId, Long washerSN, BigDecimal renewMoney, String orderCode, String washerModel) {
        CareWasherbind careWasherbind = careWasherbindMapper.selectOne(
                new QueryWrapper<>(new CareWasherbind()
                        .setWasherSN(washerSN)
                        .setWechatID(wechatId))
        );
        // 用户未绑定设备
        if (careWasherbind == null) {
            return false;
        }
        CareWasherusermonthcard careWasherusermonthcard = careWasherusermonthcardMapper.selectOne(
                new QueryWrapper<>(new CareWasherusermonthcard()
                        .setWechatID(wechatId))
        );
        // 包月设备
        if (careWasherusermonthcard != null) {
            // 包月未过期
            if (careWasherusermonthcard.getEndDate().isAfter(LocalDateTime.now())) {
                CareWasherlastprepayinfo careWasherlastprepayinfo = careWasherlastprepayinfoMapper.selectOne(
                        new QueryWrapper<>(new CareWasherlastprepayinfo().setWeChat(wechatId))
                );
                // 同一时刻只支持一台设备运行
                if (careWasherlastprepayinfo != null) {
                    return false;
                }
                return true;
            }
        }
        // TODO 关联表数据
        CareCost careCost = careCostMapper.selectOne(new QueryWrapper<>(new CareCost().setCostname("")));
        // 订单金额不匹配
        if (!new BigDecimal(careCost.getCost()).equals(renewMoney)) {
            return false;
        }

        // 插入订单记录
        CareWasherprepayinfo careWasherprepayinfo = new CareWasherprepayinfo();
        careWasherprepayinfo.setCost(careCost.getCost());
        careWasherprepayinfo.setCostid(careCost.getId());
        careWasherprepayinfo.setDeposit(careCost.getDeposit());
        careWasherprepayinfo.setOrderCode(orderCode);
        careWasherprepayinfo.setRenewMoney(renewMoney.toString());
        careWasherprepayinfo.setDiscount(careCost.getDiscount());
        careWasherprepayinfo.setWasherModel(washerModel);
        careWasherprepayinfo.setWasherSN(String.valueOf(washerSN));
        careWasherprepayinfo.insert();

        return true;
    }

    public boolean updateOrder(String orderCode, String status, String wechatId) {
        // TODO 从数据库中查询
        CareWasherprepayinfo careWasherprepayinfo = careWasherprepayinfoMapper.selectOne(new QueryWrapper<>(
                new CareWasherprepayinfo()
                        .setOrderCode(orderCode)
        ));
        // 订单不存在
        if (careWasherprepayinfo == null) {
            return false;
        }
        if (status.equals("1") && !careWasherprepayinfo.getOrderStatus().equals("1")) {
            careWasherprepayinfo.setOrderStatus(status);
            careWasherprepayinfoMapper.updateById(careWasherprepayinfo);
        }
        if (careWasherprepayinfo.getOrderStatus().equals("1")) {
            CareWasherlastprepayinfo careWasherlastprepayinfo = new CareWasherlastprepayinfo();
            careWasherlastprepayinfo.setCost(careWasherprepayinfo.getCost());
            careWasherlastprepayinfo.setCostid(careWasherprepayinfo.getCostid());
            careWasherlastprepayinfo.setCosttype(careWasherprepayinfo.getCosttype());
            careWasherlastprepayinfo.setOpttime(careWasherprepayinfo.getOpttime());
            careWasherlastprepayinfo.setOrderCode(careWasherprepayinfo.getOrderCode());
            careWasherlastprepayinfo.setDiscount(careWasherprepayinfo.getDiscount());
            careWasherlastprepayinfo.setOrderStatus(careWasherprepayinfo.getOrderStatus());
            careWasherlastprepayinfo.setPaytype(careWasherprepayinfo.getPaytype());
            careWasherlastprepayinfo.setWasherModel(careWasherprepayinfo.getWasherModel());
            careWasherlastprepayinfo.setWasherSN(careWasherprepayinfo.getWasherSN());
            careWasherlastprepayinfo.setRenewMoney(careWasherprepayinfo.getRenewMoney());
            careWasherlastprepayinfo.insert();
            return true;
        }
        return false;
    }
}
