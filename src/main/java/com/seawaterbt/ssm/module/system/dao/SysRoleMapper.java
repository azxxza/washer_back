package com.seawaterbt.ssm.module.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.system.entity.SysRole;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> selectRoleList(Page<SysRole> page, Map<String, Object> params);

    List<String> selectRoleIdByUserId(@Param("userId") Integer userId);
}