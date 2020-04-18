package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.entity.CareCost;
import com.seawaterbt.ssm.module.business.service.ICareCostService;
import com.seawaterbt.ssm.module.business.vo.CareCostPageTdo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 费用管理
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
@RestController
@RequestMapping("/careCost")
public class CareCostController extends BaseController {

    @Resource
    private ICareCostService careCostService;

    @GetMapping("listPage")
    public ResultModel page(CareCostPageTdo careCostPageTdo) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(careCostPageTdo));
        Page<Map<String, Object>> page = careCostService.getCareCostPage(jsonObject);
        return pageModel(page);
    }

    @GetMapping("list")
    public ResultModel list(CareCostPageTdo careCostPageTdo) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(careCostPageTdo));
        List<Map<String, Object>> list = careCostService.getCareCostList(jsonObject);
        return listModel(list);
    }

    @PostMapping("save")
    public ResultModel save(@RequestBody CareCost careCost) {
        boolean success = careCostService.saveCareCost(careCost);
        return defaultModel(success);
    }

    @DeleteMapping("del")
    public ResultModel del(@RequestParam Integer costId) {
        boolean success = careCostService.deleteCareCost(costId);
        return defaultModel(success);
    }

    @PutMapping("update")
    public ResultModel update(@RequestBody CareCost careCost) {
        boolean success = careCostService.updateCareCost(careCost);
        return defaultModel(success);
    }

}

