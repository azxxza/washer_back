package com.seawaterbt.ssm.module.shiro.filter;

import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.core.utils.ResponseUtils;
import com.seawaterbt.ssm.module.global.StateCode;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Slf4j
public class URLPathMatchingFilter extends PathMatchingFilter {

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        //请求的url
        String requestURL = getPathWithinApplication(request);
        log.info("请求的url :" + requestURL);
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            // 如果没有登录, 直接返回true 进入登录流程
            return true;
        }
        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        List<Map<String, Object>> permlist = shiroUser.getPermUrlList();

        boolean hasPermission = false;
        log.info("permlist:" + permlist);
        for (Map<String, Object> perm : permlist) {
            String permUrl = (String) perm.get("url");
            if (permUrl.contains("*")) {
                String subUrl = permUrl.substring(0, permUrl.indexOf("*"));
                if (requestURL.startsWith(subUrl)) {
                    hasPermission = true;
                    break;
                }
            } else {
                if (requestURL.equals(permUrl)) {
                    hasPermission = true;
                    break;
                }
            }
        }
        if (hasPermission) {
            return true;
        } else {
            log.error("当前用户{},没有访问路径{}的权限", shiroUser.getAccount(), requestURL);
            ResponseUtils.setResponse(response, StateCode.ACCESS_DENIED);
            return false;
        }

    }

    public static void main(String[] args) {
        String a = "abc*";
        String b = "abcd";
        String d = a.substring(0, a.indexOf("*"));
        System.out.println(d);
    }
}
