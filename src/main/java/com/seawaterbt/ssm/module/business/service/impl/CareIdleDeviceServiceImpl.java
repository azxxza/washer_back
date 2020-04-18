package com.seawaterbt.ssm.module.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.dao.CareIdleDeviceMapper;
import com.seawaterbt.ssm.module.business.service.ICareIdleDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CareIdleDeviceServiceImpl implements ICareIdleDeviceService {


    @Resource
    private CareIdleDeviceMapper careIdleDeviceMapper;


    @Override
    public Page<Map<String, Object>> getIdleDevicePage(Map<String, Object> params) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage(params);
        List<Map<String, Object>> list = careIdleDeviceMapper.selectIdleDeviceList(page, params);
        for (Map<String, Object> map : list) {
            Timestamp createTime = (Timestamp) map.get("careCreateTime");
            Timestamp updateTime = (Timestamp) map.get("careUpdateTime");
            if (updateTime != null) {
                map.put("opttime", updateTime.toLocalDateTime());
            } else {
                if (createTime != null) {
                    map.put("opttime", createTime.toLocalDateTime());
                }

            }
        }
        page.setRecords(list);
        return page;
    }

    @Override
    public List<Map<String, Object>> getIdleDeviceList(Map<String, Object> params) {
        return careIdleDeviceMapper.selectIdleDeviceList(null, params);
    }
}
