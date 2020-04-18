package com.seawaterbt.ssm.module.system.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.system.entity.SysExpLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统异常信息 Mapper 接口
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
public interface SysExpLogMapper extends BaseMapper<SysExpLog> {
    List<SysExpLog> selectLogList(Page<SysExpLog> pagination, @Param("params") Map<String, Object> queryMap);
}
