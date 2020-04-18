package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.entity.CareOutpower;
import com.seawaterbt.ssm.module.business.service.ICareOutpowerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author azx
 * @since 2020-03-17
 */
@RestController
@RequestMapping("/careOutpower")
public class CareOutpowerController extends BaseController {

    @Resource
    private ICareOutpowerService outpowerService;

    @GetMapping("listPage")
    public ResultModel page(CareOutpower outpower) {
        JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(outpower));
        Page<CareOutpower> page = outpowerService.getOutPokerList(params);
        return pageModel(page);
    }
}

