package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.core.utils.BigDecimalUtils;
import com.seawaterbt.ssm.module.business.entity.CareConsumePrestatistics;
import com.seawaterbt.ssm.module.business.dao.CareConsumePrestatisticsMapper;
import com.seawaterbt.ssm.module.business.service.ICareProfitPrestatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-20
 */
@Service
public class CareProfitPrestatisticsServiceImpl extends ServiceImpl<CareConsumePrestatisticsMapper, CareConsumePrestatistics> implements ICareProfitPrestatisticsService {

    @Resource
    private CareConsumePrestatisticsMapper careConsumePrestatisticsMapper;

    @Override
    public Page<Map<String, Object>> getProfitPage(Map<String, Object> params) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage(params);
        Integer statType = (Integer) params.getOrDefault("statType", 1);
        List<Map<String, Object>> list;
        if (statType == StatTypeEnum.DAY.value) {
            params.put("length", 10);
            list = careConsumePrestatisticsMapper.selectProfitList(page, params);
        } else if (statType == StatTypeEnum.MONTH.value) {
            params.put("length", 7);
            list = careConsumePrestatisticsMapper.selectProfitList(page, params);
        } else {
            list = new ArrayList<>();
        }
        page.setRecords(list);
        return page;
    }

    @Override
    public List<Map<String, Object>> getProfitList(Map<String, Object> params) {
        Integer statType = (Integer) params.getOrDefault("statType", StatTypeEnum.DAY.value);
        List<Map<String, Object>> list;
        if (statType == StatTypeEnum.DAY.value) {
            params.put("length", 10);
            list = careConsumePrestatisticsMapper.selectProfitList(null, params);
        } else if (statType == StatTypeEnum.MONTH.value) {
            params.put("length", 7);
            list = careConsumePrestatisticsMapper.selectProfitList(null, params);
        } else {
            list = new ArrayList<>();
        }
        return list;
    }

    enum StatTypeEnum {
        DAY(1), MONTH(2);

        private int value;

        public int getValue() {
            return value;
        }

        StatTypeEnum(int value) {
            this.value = value;
        }
    }
}


