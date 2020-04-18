package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareWasherprepayinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author azx
 * @since 2020-04-10
 */
public interface ICareWasherprepayinfoService extends IService<CareWasherprepayinfo> {

    Page<Map<String,Object>> getOrderPage(Map<String, Object> params);

    List<Map<String,Object>> getOrderList(Map<String, Object> params);

}
