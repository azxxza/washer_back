package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.service.ICareProfitPrestatisticsService;
import com.seawaterbt.ssm.module.business.vo.CareProfitPageTdo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author azx
 * @since 2020-03-20
 */
@RestController
@RequestMapping("/careProfitPrestatistics")
public class CareProfitPrestatisticsController extends BaseController {

    @Resource
    private ICareProfitPrestatisticsService careProfitPrestatisticsService;

    @GetMapping("listPage")
    public ResultModel page(CareProfitPageTdo profitPageTdo) {
        JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(profitPageTdo));
        Page<Map<String, Object>> page = careProfitPrestatisticsService.getProfitPage(params);
        return pageModel(page);
    }

    @GetMapping("list")
    public ResultModel list(CareProfitPageTdo careProfitPageTdo) {
        JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(careProfitPageTdo));
        List<Map<String, Object>> list = careProfitPrestatisticsService.getProfitList(params);
        return listModel(list);
    }
}

