package com.seawaterbt.ssm.module.shiro.realm;

import com.seawaterbt.ssm.core.common.support.SpringContextHolder;
import com.seawaterbt.ssm.core.constant.AlgorithmConstant;
import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.core.shiro.utils.ShiroKit;
import com.seawaterbt.ssm.module.shiro.constant.UserStatus;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import com.seawaterbt.ssm.module.system.entity.SysUser;
import com.seawaterbt.ssm.module.system.service.ISysPermService;
import com.seawaterbt.ssm.module.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //查询用户的权限
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        List<String> permUrlList = getPermList(user.getPermUrlList());
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permUrlList);
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        ISysUserService userService = SpringContextHolder.getApplicationContext().getBean(ISysUserService.class);
        ISysPermService permService = SpringContextHolder.getApplicationContext().getBean(ISysPermService.class);
        String loginName = (String) authcToken.getPrincipal();
        // 获取用户密码
        SysUser sysUser = userService.findUserByUsername(loginName);
        if (sysUser == null) {
            //账号不存在
            throw new UnknownAccountException();
        }
        if (sysUser.getStatus() == UserStatus.Freeze.getCode()) {
            // 账号被锁定
            throw new LockedAccountException();
        }
        List<Map<String, Object>> permList = permService.selectPermList(new HashMap<String, Object>(1) {{
            put("userId", sysUser.getId());
        }});
        ShiroUser shiroUser = new ShiroUser(sysUser, permList);
        // 密码加盐处理
        String source = shiroUser.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        return new SimpleAuthenticationInfo(
                shiroUser, // 放用户对象
                sysUser.getPassword(),
                credentialsSalt,
                //ByteSource.Util.bytes("salt"), salt=username+salt,采用明文访问时，不需要此句
                getName()
        );
    }

    /**
     * 设置认证加密方式
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        HashedCredentialsMatcher md5CredentialsMatcher = new HashedCredentialsMatcher();
        md5CredentialsMatcher.setHashAlgorithmName(AlgorithmConstant.MD5);
        md5CredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        super.setCredentialsMatcher(md5CredentialsMatcher);
    }

    private List<String> getPermList(List<Map<String, Object>> permList) {
        List<String> permUrlList = new ArrayList<>();
        for (Map<String, Object> menu : permList) {
            String url = (String) menu.get("url");
            if (StringUtils.isNotBlank(url)) {
                permUrlList.add(url);
            }
        }
        return permUrlList;
    }
}
