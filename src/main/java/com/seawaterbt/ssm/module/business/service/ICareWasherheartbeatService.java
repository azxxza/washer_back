package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareInstall;
import com.seawaterbt.ssm.module.business.entity.CareWasherheartbeat;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-17
 */
public interface ICareWasherheartbeatService extends IService<CareWasherheartbeat> {
    Page<CareWasherheartbeat> getWasherHeartBeatPage(Map<String, Object> params);
}
