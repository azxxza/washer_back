package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareCustomerPhone;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author azx
 * @since 2020-04-09
 */
public interface ICareCustomerPhoneService extends IService<CareCustomerPhone> {
    Page<CareCustomerPhone> getCareCustomerPhonePage(Map<String,Object> params);
}
