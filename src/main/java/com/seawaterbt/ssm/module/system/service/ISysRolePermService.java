package com.seawaterbt.ssm.module.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.seawaterbt.ssm.module.system.entity.SysRolePerm;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-05
 */
public interface ISysRolePermService extends IService<SysRolePerm> {
    boolean saveRolePerm(Integer roleId, String[] perms);
}
