package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.module.business.entity.CareCost;
import com.seawaterbt.ssm.module.business.dao.CareCostMapper;
import com.seawaterbt.ssm.module.business.service.ICareCostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CareCostServiceImpl extends ServiceImpl<CareCostMapper, CareCost> implements ICareCostService {

    @Resource
    private CareCostMapper careCostMapper;

    @Override
    public Page<Map<String, Object>> getCareCostPage(Map<String, Object> params) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage(params);
        List<Map<String, Object>> list = careCostMapper.selectCareCostList(page, params);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<Map<String, Object>> getCareCostList(Map<String, Object> params) {
        return careCostMapper.selectCareCostList(null, params);
    }

    @Override
    public boolean deleteCareCost(Integer costId) {
        int count = careCostMapper.deleteById(costId);
        return count >= 1;
    }

    @Override
    public boolean saveCareCost(CareCost careCost) {
        careCost.setCreatedAt(LocalDateTime.now());
        careCost.setUpdatedAt(LocalDateTime.now());
        ShiroUser shiroUser =(ShiroUser) SecurityUtils.getSubject().getPrincipal();
        careCost.setOperator(shiroUser.getAccount());
        int count = careCostMapper.insert(careCost);
        return count >= 1;
    }

    @Override
    public boolean updateCareCost(CareCost careCost) {
        careCost.setUpdatedAt(LocalDateTime.now());
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        careCost.setOperator(shiroUser.getAccount());
        int count = careCostMapper.updateById(careCost);
        return count >= 1;
    }
}
