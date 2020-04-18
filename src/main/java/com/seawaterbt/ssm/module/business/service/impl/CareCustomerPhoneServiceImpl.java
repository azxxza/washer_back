package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.entity.CareCustomerPhone;
import com.seawaterbt.ssm.module.business.dao.CareCustomerPhoneMapper;
import com.seawaterbt.ssm.module.business.service.ICareCustomerPhoneService;
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
 * @since 2020-04-09
 */
@Service
public class CareCustomerPhoneServiceImpl extends ServiceImpl<CareCustomerPhoneMapper, CareCustomerPhone> implements ICareCustomerPhoneService {

    @Resource
    private CareCustomerPhoneMapper careCustomerPhoneMapper;

    @Override
    public Page<CareCustomerPhone> getCareCustomerPhonePage(Map<String, Object> params) {
        Page<CareCustomerPhone> page = new PageFactory<CareCustomerPhone>().defaultPage(params);
        List<CareCustomerPhone> list = careCustomerPhoneMapper.selectCareCustomerPhonePage(page, params);
        page.setRecords(list);
        return page;
    }
}
