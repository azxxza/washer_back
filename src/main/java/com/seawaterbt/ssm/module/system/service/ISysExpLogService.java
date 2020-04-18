package com.seawaterbt.ssm.module.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.system.entity.SysExpLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 系统异常信息 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
public interface ISysExpLogService extends IService<SysExpLog> {

    void doAdd(Exception exception) throws Exception;

    Page<SysExpLog> getList(Map<String, Object> map) throws Exception;
}
