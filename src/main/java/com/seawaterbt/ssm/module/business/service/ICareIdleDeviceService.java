package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

public interface ICareIdleDeviceService {
    Page<Map<String, Object>> getIdleDevicePage(Map<String, Object> params);

    List<Map<String, Object>> getIdleDeviceList(Map<String, Object> params);
}
