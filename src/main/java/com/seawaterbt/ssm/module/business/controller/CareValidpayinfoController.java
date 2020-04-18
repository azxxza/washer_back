package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.entity.CareWasherprepayinfo;
import com.seawaterbt.ssm.module.business.service.ICareValidpayinfoService;
import com.seawaterbt.ssm.module.business.service.ICareWasherprepayinfoService;
import com.seawaterbt.ssm.module.business.vo.CareValidpayinfoPageTdo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 消费明细
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
@RestController
@RequestMapping("/careValidpayinfo")
public class CareValidpayinfoController extends BaseController {

    @Resource
    private ICareWasherprepayinfoService careWasherprepayinfoService;

    @GetMapping("listPage")
    public ResultModel page(CareValidpayinfoPageTdo validpayinfo) {
        Map<String, Object> params = JSONObject.parseObject(JSONObject.toJSONString(validpayinfo));
        Page<Map<String,Object>> page = careWasherprepayinfoService.getOrderPage(params);
        return pageModel(page);
    }

    @GetMapping("list")
    public ResultModel list(CareValidpayinfoPageTdo validpayinfo) {
        Map<String, Object> params = JSONObject.parseObject(JSONObject.toJSONString(validpayinfo));
        List<Map<String,Object>> list = careWasherprepayinfoService.getOrderList(params);
        return listModel(list);
    }
}

