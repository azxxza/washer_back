package com.seawaterbt.ssm.module.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seawaterbt.ssm.module.system.entity.SysRole;

import java.util.Map;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-05
 */
public interface ISysRoleService extends IService<SysRole> {

    Page<SysRole> selectRoleList(Map<String, Object> params);

    SysRole selectByRoleName(String name);

    boolean saveSysRole(SysRole sysRole);
}
