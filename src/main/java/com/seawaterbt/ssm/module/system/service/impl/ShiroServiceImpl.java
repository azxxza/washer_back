package com.seawaterbt.ssm.module.system.service.impl;

import com.seawaterbt.ssm.module.shiro.filter.AjaxPermissionsAuthorizationFilter;
import com.seawaterbt.ssm.module.shiro.filter.URLPathMatchingFilter;
import com.seawaterbt.ssm.module.system.service.IShiroService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShiroServiceImpl implements IShiroService {

    @Override
    public Map<String, Object> loadFilterChainDefinitionMap() {
        // 权限控制map

        Map<String, Object> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("authc", new AjaxPermissionsAuthorizationFilter());

        filterChainDefinitionMap.put("requestURL", new URLPathMatchingFilter());

        return filterChainDefinitionMap;

    }


    @Override
    public void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean, Integer roleId, Boolean
            isRemoveSession) {

    }

    @Override
    public void updatePermissionByRoleId(Integer roleId, Boolean isRemoveSession) {

    }
}
