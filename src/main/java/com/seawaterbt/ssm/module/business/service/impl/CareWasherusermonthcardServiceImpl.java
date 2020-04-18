package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.entity.CareInstall;
import com.seawaterbt.ssm.module.business.entity.CareWasherusermonthcard;
import com.seawaterbt.ssm.module.business.dao.CareWasherusermonthcardMapper;
import com.seawaterbt.ssm.module.business.service.ICareWasherusermonthcardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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
public class CareWasherusermonthcardServiceImpl extends ServiceImpl<CareWasherusermonthcardMapper, CareWasherusermonthcard> implements ICareWasherusermonthcardService {

    @Resource
    private CareWasherusermonthcardMapper careWasherusermonthcardMapper;

    @Override
    public Page<CareWasherusermonthcard> getWasherUserMonthCardPage(Map<String, Object> params) {
        Page<CareWasherusermonthcard> page = new PageFactory<CareWasherusermonthcard>().defaultPage(params);
        List<CareWasherusermonthcard> list = careWasherusermonthcardMapper.selectWasherusermonthcard(page, params);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<CareWasherusermonthcard> getWasherUserMonthCardList(Map<String, Object> params) {
        return careWasherusermonthcardMapper.selectWasherusermonthcard(null, params);
    }

    @Override
    public boolean updateWasherUserMonthCard(CareWasherusermonthcard careWasherusermonthcard) {
        careWasherusermonthcard.setUpdatedAt(LocalDateTime.now());
        int count = careWasherusermonthcardMapper.updateById(careWasherusermonthcard);
        return count >= 1;
    }
}
