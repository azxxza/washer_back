package com.seawaterbt.ssm.module.business.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareWasherheartbeat;
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
 * @since 2020-03-17
 */
public interface CareWasherheartbeatMapper extends BaseMapper<CareWasherheartbeat> {
    List<CareWasherheartbeat> selectWasherHeartBeatList(Page<CareWasherheartbeat> page, @Param("params") Map<String, Object> params);
}
