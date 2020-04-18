package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.entity.CareWasherheartbeat;
import com.seawaterbt.ssm.module.business.dao.CareWasherheartbeatMapper;
import com.seawaterbt.ssm.module.business.service.ICareWasherheartbeatService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-17
 */
@Service
public class CareWasherheartbeatServiceImpl extends ServiceImpl<CareWasherheartbeatMapper, CareWasherheartbeat> implements ICareWasherheartbeatService {

    @Resource
    private CareWasherheartbeatMapper careWasherheartbeatMapper;

    @Override
    public Page<CareWasherheartbeat> getWasherHeartBeatPage(Map<String, Object> params) {
        Page<CareWasherheartbeat> page = new PageFactory<CareWasherheartbeat>().defaultPage(params);
        List<CareWasherheartbeat> list = careWasherheartbeatMapper.selectWasherHeartBeatList(page, params);
        page.setRecords(list);
        return page;
    }
}
