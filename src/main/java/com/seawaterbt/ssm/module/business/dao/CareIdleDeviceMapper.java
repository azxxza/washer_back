package com.seawaterbt.ssm.module.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareAgentSampling;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
public interface CareIdleDeviceMapper {

    List<Map<String, Object>> selectIdleDeviceList(Page<Map<String, Object>> page, @Param("params") Map<String, Object> params);

}
