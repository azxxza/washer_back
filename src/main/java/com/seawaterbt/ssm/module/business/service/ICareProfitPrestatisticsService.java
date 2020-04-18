package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareConsumePrestatistics;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-20
 */
public interface ICareProfitPrestatisticsService extends IService<CareConsumePrestatistics> {

    Page<Map<String, Object>> getProfitPage(Map<String, Object> params);

    List<Map<String, Object>> getProfitList(Map<String, Object> params);


}
