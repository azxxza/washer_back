package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.entity.CareFault;
import com.seawaterbt.ssm.module.business.dao.CareFaultMapper;
import com.seawaterbt.ssm.module.business.service.ICareFaultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-17
 */
@Service
public class CareFaultServiceImpl extends ServiceImpl<CareFaultMapper, CareFault> implements ICareFaultService {

    @Resource
    private CareFaultMapper careFaultMapper;

    @Override
    public Page<Map<String, Object>> getCareFaultPage(Map<String, Object> params) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage(params);
        List<Map<String, Object>> careFaultList = careFaultMapper.selectCareFaultList(page, params);
        page.setRecords(careFaultList);
        return page;
    }
}
