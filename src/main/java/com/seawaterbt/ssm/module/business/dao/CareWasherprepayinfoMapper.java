package com.seawaterbt.ssm.module.business.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareWasherprepayinfo;
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
 * @since 2020-04-10
 */
public interface CareWasherprepayinfoMapper extends BaseMapper<CareWasherprepayinfo> {
    List<Map<String,Object>> selectOrderList(Page<Map<String,Object>> page, @Param("params") Map<String, Object> params);
}
