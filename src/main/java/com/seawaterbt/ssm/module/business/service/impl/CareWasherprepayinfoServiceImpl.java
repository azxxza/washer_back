package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.entity.CareWasherprepayinfo;
import com.seawaterbt.ssm.module.business.dao.CareWasherprepayinfoMapper;
import com.seawaterbt.ssm.module.business.service.ICareWasherprepayinfoService;
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
 * @since 2020-04-10
 */
@Service
public class CareWasherprepayinfoServiceImpl extends ServiceImpl<CareWasherprepayinfoMapper, CareWasherprepayinfo> implements ICareWasherprepayinfoService {


    @Resource
    private CareWasherprepayinfoMapper careWasherprepayinfoMapper;

    @Override
    public List<Map<String,Object>> getOrderList(Map<String, Object> params) {
        return careWasherprepayinfoMapper.selectOrderList(null, params);
    }

    @Override
    public Page<Map<String,Object>> getOrderPage(Map<String, Object> params) {
        Page<Map<String,Object>> page = new PageFactory<Map<String,Object>>().defaultPage(params);
        List<Map<String,Object>> list = careWasherprepayinfoMapper.selectOrderList(page, params);
        page.setRecords(list);
        return page;
    }
}
