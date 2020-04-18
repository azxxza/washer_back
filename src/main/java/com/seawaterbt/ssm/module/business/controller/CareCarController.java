package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.excel.ExcelToolKit;
import com.seawaterbt.ssm.core.common.excel.Line;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.module.business.entity.CareCar;
import com.seawaterbt.ssm.module.business.entity.CareInstall;
import com.seawaterbt.ssm.module.business.service.ICareCarService;
import com.seawaterbt.ssm.module.business.vo.CareCarPageTdo;
import com.seawaterbt.ssm.module.global.StateCode;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
@RequestMapping("/careCar")
public class CareCarController extends BaseController {

    @Resource
    private ICareCarService careCarService;

    @GetMapping("listPage")
    public ResultModel page(CareCarPageTdo careCarPageTdo) {
        JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(careCarPageTdo));
        Page<Map<String, Object>> page = careCarService.getCareCarList(params);
        return pageModel(page);
    }

    @PostMapping("save")
    public ResultModel save(@RequestBody CareCar careCar) {
        CareCar car = careCarService.getOne(
                new QueryWrapper<>(new CareCar().setSn(careCar.getSn())));
        if (car != null) {
            return new ResultModel(StateCode.DEVICE_SN_EXIT);
        }
        boolean success = careCarService.saveCareCar(careCar);
        return defaultModel(success);
    }

    @PutMapping("update")
    public ResultModel update(@RequestBody CareCar careCar) {
        CareCar car = careCarService.getOne(
                new QueryWrapper<>(
                        new CareCar().setSn(careCar.getSn())
                )
        );
        if (car != null && car.getId().longValue() != careCar.getId()) {
            return new ResultModel(StateCode.DEVICE_SN_EXIT);
        }
        boolean success = careCarService.updateCareCar(careCar);
        return defaultModel(success);
    }

    @DeleteMapping("del")
    public ResultModel del(@RequestParam Integer carId) {
        boolean success = careCarService.removeCareCar(carId);
        return defaultModel(success);
    }

    @PostMapping("uploadExcel")
    public ResultModel uploadExcel(@RequestParam("file") MultipartFile file) {
        //第一个参数为Excel表，第二个参数为从第几行读取Excel的内容，返回值为一个字符串数组集合（每一个数组代表Excel表的一行数据）
        List<String> columnNames = Arrays.asList(
                CareCar.SN,
                CareCar.MAC,
                CareCar.PRIMARY_KEY);
        Map<String, Object> data = ExcelToolKit.uploadExcel(file, columnNames);
        if ((Integer) data.get("state") == 1) {
            List<CareCar> list = buildUploadData((List<Line>) data.get("lines"));
            Map<String, Object> result = careCarService.saveCareCarBatch(list);
            return mapModel(result);
        }
        return errorModel();
    }

    private List<CareCar> buildUploadData(List<Line> lineList) {
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        List<CareCar> list = new ArrayList<>();
        for (Line line : lineList) {
            String sn = line.getColumns().getOrDefault(CareCar.SN, StringUtils.EMPTY);
            String mac = line.getColumns().getOrDefault(CareCar.MAC, StringUtils.EMPTY);
            String primaryKey = line.getColumns().getOrDefault(CareCar.PRIMARY_KEY, StringUtils.EMPTY);

            CareCar careCar = new CareCar();
            careCar
                    .setSn(sn)
                    .setMac(mac)
                    .setPrimaryKey(primaryKey)
                    .setCreatedAt(LocalDateTime.now())
                    .setUpdatedAt(LocalDateTime.now())
                    .setOperator(String.valueOf(shiroUser.getAccount()));
            list.add(careCar);
        }
        return list;
    }
}

