package com.seawaterbt.ssm.module.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.entity.CareOutpower;
import com.seawaterbt.ssm.module.business.dao.CareOutpowerMapper;
import com.seawaterbt.ssm.module.business.service.ICareOutpowerService;
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
public class CareOutpowerServiceImpl extends ServiceImpl<CareOutpowerMapper, CareOutpower> implements ICareOutpowerService {

    @Resource
    private CareOutpowerMapper careOutpowerMapper;

    @Override
    public Page<CareOutpower> getOutPokerList(JSONObject params) {
        Page<CareOutpower> page = new PageFactory<CareOutpower>().defaultPage(params);
        List<CareOutpower> list = careOutpowerMapper.selectOutPokerList(page, params);
        page.setRecords(list);
        return page;
    }
}
