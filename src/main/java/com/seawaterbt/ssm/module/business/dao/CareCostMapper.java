package com.seawaterbt.ssm.module.business.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareCost;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
public interface CareCostMapper extends BaseMapper<CareCost> {

    List<Map<String,Object>> selectCareCostList(Page<Map<String,Object>> page,@Param("params") Map<String, Object> params);
}
