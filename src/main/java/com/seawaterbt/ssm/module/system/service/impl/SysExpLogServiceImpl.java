package com.seawaterbt.ssm.module.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.core.utils.DateUtils;
import com.seawaterbt.ssm.core.utils.ExceptionUtils;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import com.seawaterbt.ssm.module.system.entity.SysExpLog;
import com.seawaterbt.ssm.module.system.dao.SysExpLogMapper;
import com.seawaterbt.ssm.module.system.service.ISysExpLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seawaterbt.ssm.module.system.service.ISysPermService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统异常信息 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
@Service
public class SysExpLogServiceImpl extends ServiceImpl<SysExpLogMapper, SysExpLog> implements ISysExpLogService {

    @Resource
    private SysExpLogMapper sysExpLogMapper;

    @Override
    public Page<SysExpLog> getList(Map<String, Object> map) {
        Page<SysExpLog> page = new PageFactory<SysExpLog>().defaultPage(map);
        List<SysExpLog> list = sysExpLogMapper.selectLogList(page, map);
        page.setRecords(list);
        return page;
    }

    @Override
    public void doAdd(Exception exception) {
        try {
            SysExpLog sysExpLog = new SysExpLog();
            sysExpLog.setCreateTime(DateUtils.getTimeStamp());
            this.initParam(exception, sysExpLog);
            new Thread(() -> sysExpLogMapper.insert(sysExpLog)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initParam(Exception exception, SysExpLog sysExpLog) {
        this.setExp(exception, sysExpLog);
        this.setUser(sysExpLog);
        this.setClientMsg(sysExpLog);
    }

    private void setExp(Exception exception, SysExpLog sysExpLog) {
        sysExpLog.setExpType(exception.getClass().getName());
        sysExpLog.setExpReason(exception.toString());
        String stackStr = ExceptionUtils.getExpStackTraceSimple(exception);
        sysExpLog.setExpContent(stackStr);
    }

    private void setUser(SysExpLog sysExpLog) {
        try {
            ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
            sysExpLog.setUserId(shiroUser.getId());
            sysExpLog.setUserName(shiroUser.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setClientMsg(SysExpLog sysExpLog) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        sysExpLog.setUrl(request.getContextPath() + " " + request.getRequestURI());
    }
}
