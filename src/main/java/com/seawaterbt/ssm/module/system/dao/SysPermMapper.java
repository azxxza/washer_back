package com.seawaterbt.ssm.module.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.system.entity.SysPerm;
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
public interface SysPermMapper extends BaseMapper<SysPerm> {

    List<Map<String, Object>> selectPermList(Map<String, Object> queryMap);

    List<Map<String, Object>> selectAllPermList(@Param("title") String title);

    List<Map<String, Object>> selectCascader();

    List<Integer> selectPermByRoleId(Integer roleId);

    int deleteByRoleId(Integer roleId);

    List<SysPerm> selectPermPage(Page<SysPerm> page, @Param("params") Map<String, Object> params);
}