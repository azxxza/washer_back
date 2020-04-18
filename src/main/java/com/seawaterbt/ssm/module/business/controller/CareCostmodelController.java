package com.seawaterbt.ssm.module.business.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.entity.CareCostmodel;
import com.seawaterbt.ssm.module.business.entity.CareCosttype;
import com.seawaterbt.ssm.module.business.service.ICareCostmodelService;
import com.seawaterbt.ssm.module.business.service.ICareCosttypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author azx
 * @since 2020-03-09
 */
@RestController
@RequestMapping("/careCostmodel")
public class CareCostmodelController extends BaseController {
    @Resource
    private ICareCostmodelService careCostmodelService;

    @GetMapping("list")
    public ResultModel list(CareCostmodel careCostmodel) {
        List<CareCostmodel> list = careCostmodelService.list(new QueryWrapper<>(careCostmodel));

        Map<Integer, CareCostmodel> costmodelMap = new HashMap<>();
        for (CareCostmodel costmodel : list) {
            costmodelMap.put(costmodel.getCostmodelid(), costmodel);
        }
        List<CareCostmodel> careCostmodelList = new ArrayList<>(costmodelMap.values());
        return defaultModel(careCostmodelList);
    }
}

