package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareFault;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seawaterbt.ssm.module.business.entity.CareInstall;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-17
 */
public interface ICareFaultService extends IService<CareFault> {
    Page<Map<String, Object>> getCareFaultPage(Map<String, Object> params);
}
