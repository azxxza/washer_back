package com.seawaterbt.ssm.module.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.service.ICareOrderService;
import com.seawaterbt.ssm.module.business.vo.CareOrderPageTdo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/careOrder")
public class CareOrderController extends BaseController {

    @Resource
    private ICareOrderService careOrderService;

    @GetMapping("listPage")
    public ResultModel page(CareOrderPageTdo careOrderPageTdo) {
        JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(careOrderPageTdo));
        Page<Map<String, Object>> page = careOrderService.getCareOrderPage(params);
        return pageModel(page);
    }
}
