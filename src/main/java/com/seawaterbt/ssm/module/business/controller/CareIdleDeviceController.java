package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.service.ICareIdleDeviceService;
import com.seawaterbt.ssm.module.business.vo.CareIdleDeviceTdo;
import org.hibernate.validator.internal.util.IdentitySet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("careIdleDevice")
public class CareIdleDeviceController extends BaseController {

    @Resource
    private ICareIdleDeviceService careIdleDeviceService;

    @GetMapping("listPage")
    public ResultModel page(CareIdleDeviceTdo idleDeviceTdo) {
        Map<String, Object> params = JSONObject.parseObject(JSONObject.toJSONString(idleDeviceTdo));
        Page<Map<String, Object>> page = careIdleDeviceService.getIdleDevicePage(params);
        return pageModel(page);
    }

    @GetMapping("list")
    public ResultModel list(CareIdleDeviceTdo idleDeviceTdo) {
        Map<String, Object> params = JSONObject.parseObject(JSONObject.toJSONString(idleDeviceTdo));
        List<Map<String, Object>> list = careIdleDeviceService.getIdleDeviceList(params);
        return listModel(list);
    }

    public static void main(String[] args) {
        String string = "10E2021FDCD8";

        String result = "";
        for (int i = 0; i < string.length(); i = i + 2) {
            String temp = string.substring(i, i + 2);
            System.out.println(temp);
            long dec_num = Long.parseLong(temp, 16);
            result = result + ":" + dec_num;
        }
        System.out.println(result);

    }
}
