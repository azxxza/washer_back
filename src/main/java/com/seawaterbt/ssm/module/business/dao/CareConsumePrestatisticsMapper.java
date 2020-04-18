package com.seawaterbt.ssm.module.business.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareConsumePrestatistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author azx
 * @since 2020-03-20
 */
public interface CareConsumePrestatisticsMapper extends BaseMapper<CareConsumePrestatistics> {

    List<Map<String, Object>> selectProfitList(Page<Map<String, Object>> page, @Param("params") Map<String, Object> params);

}
