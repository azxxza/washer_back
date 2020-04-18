package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seawaterbt.ssm.module.business.entity.CareCar;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-20
 */
public interface ICareOrderService{
    Page<Map<String, Object>> getCareOrderPage(Map<String, Object> params);
}
