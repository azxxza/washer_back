package com.seawaterbt.ssm.module.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seawaterbt.ssm.module.system.entity.SysOperationLog;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
public interface ISysOperationLogService extends IService<SysOperationLog> {
    Page<Map<String, Object>> selectOperationLogList(Map<String,Object> params);
}
