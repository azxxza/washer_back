package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.entity.CareFault;
import com.seawaterbt.ssm.module.business.service.ICareFaultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author azx
 * @since 2020-03-17
 */
@RestController
@RequestMapping("/careFault")
public class CareFaultController extends BaseController {

    @Resource
    private ICareFaultService careFaultService;

    @GetMapping("listPage")
    public ResultModel page(CareFault careFault) {
        JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(careFault));
        Page<Map<String, Object>> page = careFaultService.getCareFaultPage(params);
        return pageModel(page);
    }
}

