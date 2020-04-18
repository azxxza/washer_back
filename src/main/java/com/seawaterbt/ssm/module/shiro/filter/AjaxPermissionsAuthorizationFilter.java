package com.seawaterbt.ssm.module.shiro.filter;

import com.seawaterbt.ssm.module.global.StateCode;
import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.module.shiro.constant.UserStatus;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import com.seawaterbt.ssm.core.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Slf4j
public class AjaxPermissionsAuthorizationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("当前url" + ((HttpServletRequest) request).getRequestURI());
        boolean isKitouted = this.judgeIsKitouted();
        if (isKitouted) {
            return false;
        }
        if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    private boolean judgeIsKitouted() {
        Session session = SecurityUtils.getSubject().getSession();
        Object isKitouted = session.getAttribute(ShiroConstant.SESSION_KEY_KICKOUT);
        return isKitouted != null && (boolean) isKitouted;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        StateCode stateCode;
        if (this.judgeIsKitouted()) {
            stateCode = StateCode.ACCOUNT_REPEAT_LOGIN;
            SecurityUtils.getSubject().logout();
        } else {
            stateCode = StateCode.NO_LOGIN;
        }
        ResponseUtils.setResponse(response, stateCode);
        return false;
    }

    @Bean
    public FilterRegistrationBean<AjaxPermissionsAuthorizationFilter> registration(AjaxPermissionsAuthorizationFilter filter) {
        FilterRegistrationBean<AjaxPermissionsAuthorizationFilter> registration = new FilterRegistrationBean<>(filter);
        registration.setEnabled(false);
        return registration;
    }
}
