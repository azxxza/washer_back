package com.seawaterbt.ssm.module.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seawaterbt.ssm.module.system.entity.SysUser;


import java.util.List;
import java.util.Map;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-05
 */
public interface ISysUserService extends IService<SysUser> {

	Page<Map<String,Object>> selectUserPageList (Map<String,Object> params);

	SysUser findUserByUsername(String username);

	boolean saveUser(SysUser sysUser, List<Integer> roleId);

	void updateUser(SysUser sysUser, List<Integer> roleId);

	boolean deleteUser(Integer userId);

	boolean updateUserError(Integer userId, Integer status, Integer errotTimes, String avatar);
}
