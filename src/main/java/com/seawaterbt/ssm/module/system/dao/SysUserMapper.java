package com.seawaterbt.ssm.module.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.system.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * ${table.comment} Mapper 接口
 * </p>
 *
 * @author azx
 * @since 2020-03-05
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<Map<String, Object>> selectUserPageList(Page<Map<String, Object>> page,@Param("params") Map<String,Object> params);
}