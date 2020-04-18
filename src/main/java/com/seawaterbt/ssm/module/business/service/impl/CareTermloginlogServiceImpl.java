package com.seawaterbt.ssm.module.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.entity.CareTermloginlog;
import com.seawaterbt.ssm.module.business.dao.CareTermloginlogMapper;
import com.seawaterbt.ssm.module.business.entity.CareWasherheartbeat;
import com.seawaterbt.ssm.module.business.service.ICareTermloginlogService;
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
public class CareTermloginlogServiceImpl extends ServiceImpl<CareTermloginlogMapper, CareTermloginlog> implements ICareTermloginlogService {

    @Resource
    private CareTermloginlogMapper termloginlogMapper;

    @Override
    public Page<CareTermloginlog> getTeamLoginLogList(Map<String,Object> params) {
        Page<CareTermloginlog> page = new PageFactory<CareTermloginlog>().defaultPage(params);
        List<CareTermloginlog> list = termloginlogMapper.selectTermloginLogList(page, params);
        page.setRecords(list);
        return page;
    }
}
