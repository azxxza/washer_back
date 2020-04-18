package com.seawaterbt.ssm.module.system.dao;

import com.seawaterbt.ssm.module.system.entity.SysUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    void insertUserRoleBatch(List<SysUserRole> list);

    int deleteByUserId(@Param("userId") Integer userId);

    List<Integer> selectUserIdByRoleId(@Param("roleId") Integer roleId);
}
