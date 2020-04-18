package com.seawaterbt.ssm.module.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.system.entity.SysOperationLog;
import com.seawaterbt.ssm.module.system.dao.SysOperationLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seawaterbt.ssm.module.system.service.ISysOperationLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统操作日志 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
@Service
public class SysOperationLogServiceImpl extends ServiceImpl<SysOperationLogMapper, SysOperationLog> implements ISysOperationLogService {

    @Resource
    private SysOperationLogMapper sysOperationLogMapper;

    @Override
    public Page<Map<String, Object>> selectOperationLogList(Map<String, Object> params) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage(params);
        List<Map<String, Object>> list = sysOperationLogMapper.selectOperationLogList(page, params);
        page.setRecords(list);
        return page;
    }
}
