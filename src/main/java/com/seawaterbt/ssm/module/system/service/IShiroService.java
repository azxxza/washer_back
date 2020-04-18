package com.seawaterbt.ssm.module.system.service;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import java.util.Map;

public interface IShiroService {

    /**
     * 　初始化权限 -> 拿全部权限
     */
    Map<String, Object> loadFilterChainDefinitionMap();

    /**
     * 在对uri权限进行增删改操作时，需要调用此方法进行动态刷新加载数据库中的uri权限
     */
    void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, Integer roleId, Boolean isRemoveSession);

    /**
     * shiro动态权限加载 -> 原理：删除shiro缓存，重新执行doGetAuthorizationInfo方法授权角色和权限
     */
    void updatePermissionByRoleId(Integer roleId, Boolean isRemoveSession);

}
