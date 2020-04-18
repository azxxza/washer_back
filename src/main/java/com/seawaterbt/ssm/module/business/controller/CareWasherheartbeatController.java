package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.entity.CareWasherheartbeat;
import com.seawaterbt.ssm.module.business.service.ICareWasherheartbeatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 心跳报表
 * </p>
 *
 * @author azx
 * @since 2020-03-17
 */
@RestController
@RequestMapping("/careWasherheartbeat")
public class CareWasherheartbeatController extends BaseController {

    @Resource
    private ICareWasherheartbeatService careWasherheartbeatService;

    @GetMapping("listPage")
    public ResultModel page(CareWasherheartbeat washerheartbeat) {
        JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(washerheartbeat));
        Page<CareWasherheartbeat> page = careWasherheartbeatService.getWasherHeartBeatPage(params);
        return pageModel(page);
    }
}

