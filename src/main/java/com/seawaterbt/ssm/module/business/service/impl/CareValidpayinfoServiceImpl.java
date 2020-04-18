package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.entity.CareInstall;
import com.seawaterbt.ssm.module.business.entity.CareValidpayinfo;
import com.seawaterbt.ssm.module.business.dao.CareValidpayinfoMapper;
import com.seawaterbt.ssm.module.business.service.ICareValidpayinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
@Service
public class CareValidpayinfoServiceImpl extends ServiceImpl<CareValidpayinfoMapper, CareValidpayinfo> implements ICareValidpayinfoService {

    @Resource
    private CareValidpayinfoMapper validpayinfoMapper;

    @Override
    public Page<Map<String, Object>> getValidPayInfoPage(Map<String, Object> params) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage(params);
        List<Map<String, Object>> list = validpayinfoMapper.selectValidPayInfo(page, params);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<Map<String, Object>> getValidPayInfoList(Map<String, Object> params) {
        return validpayinfoMapper.selectValidPayInfo(null, params);
    }
}
