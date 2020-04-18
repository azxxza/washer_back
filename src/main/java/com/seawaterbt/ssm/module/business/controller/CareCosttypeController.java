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
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author azx
 * @since 2020-03-09
 */
@RestController
@RequestMapping("/careCosttype")
public class CareCosttypeController extends BaseController {

    @Resource
    private ICareCosttypeService careCosttypeService;

    @GetMapping("list")
    public ResultModel list(CareCosttype careCosttype) {
        List<CareCosttype> list = careCosttypeService.list(new QueryWrapper<>(careCosttype));
        return defaultModel(list);
    }
}

