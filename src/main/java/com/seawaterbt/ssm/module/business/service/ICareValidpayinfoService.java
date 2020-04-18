package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareValidpayinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
public interface ICareValidpayinfoService extends IService<CareValidpayinfo> {

    Page<Map<String, Object>> getValidPayInfoPage(Map<String, Object> params);

    List<Map<String,Object>> getValidPayInfoList(Map<String, Object> params);
}
