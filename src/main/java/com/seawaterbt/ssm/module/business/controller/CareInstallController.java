package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.microsoft.sqlserver.jdbc.SQLServerResultSet;
import com.seawaterbt.ssm.core.common.excel.ExcelToolKit;
import com.seawaterbt.ssm.core.common.excel.Line;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.module.business.entity.CareCar;
import com.seawaterbt.ssm.module.business.entity.CareInstall;
import com.seawaterbt.ssm.module.business.service.ICareCarService;
import com.seawaterbt.ssm.module.business.service.ICareInstallService;
import com.seawaterbt.ssm.module.business.vo.CareInstallPageTdo;
import com.seawaterbt.ssm.module.global.StateCode;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 安装管理
 * </p>
 *
 * @author azx
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/careInstall")
public class CareInstallController extends BaseController {

    @Resource
    private ICareInstallService careInstallService;

    @Resource
    private ICareCarService careCarService;

    @GetMapping("listPage")
    public ResultModel page(CareInstallPageTdo careInstallPageTdo) {
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(careInstallPageTdo));
        Page<CareInstall> page = careInstallService.getCareInstallPage(jsonObject);
        return pageModel(page);
    }

    @GetMapping("list")
    public ResultModel list(CareInstall careInstall) {
        Map<String, Object> params = JSONObject.parseObject(JSONObject.toJSONString(careInstall));
        List<CareInstall> list = careInstallService.getCareInstallList(params);
        return defaultModel(list);
    }

    @PostMapping("save")
    public ResultModel save(@RequestBody CareInstall careInstall) {
        CareInstall install = careInstallService.getOne(
                new QueryWrapper<>(new CareInstall().setSn(careInstall.getSn())));
        if (install != null) {
            return new ResultModel(StateCode.DEVICE_SN_EXIT);
        }
        CareCar car = careCarService.getOne(new QueryWrapper<>(new CareCar().setSn(String.valueOf(careInstall.getSn()))));
        if (car == null) {
            return new ResultModel(StateCode.DEVICE_SN__NO_EXIT);
        }
        boolean success = careInstallService.saveCareInstall(careInstall);
        return defaultModel(success);
    }

    @PutMapping("update")
    public ResultModel update(@RequestBody CareInstall careInstall) {
        CareInstall install = careInstallService.getOne(
                new QueryWrapper<>(new CareInstall().setSn(careInstall.getSn())));
        if (install != null && install.getId().intValue() != careInstall.getId()) {
            return new ResultModel(StateCode.DEVICE_SN_EXIT);
        }
        CareCar car = careCarService.getOne(new QueryWrapper<>(new CareCar().setSn(String.valueOf(careInstall.getSn()))));
        if (car == null) {
            return new ResultModel(StateCode.DEVICE_SN__NO_EXIT);
        }
        boolean success = careInstallService.updateCareInstall(careInstall);
        return defaultModel(success);
    }

    @DeleteMapping("del")
    public ResultModel del(@RequestParam Integer installId) {
        boolean success = careInstallService.deleteCareInstall(installId);
        return defaultModel(success);
    }

    @PostMapping("uploadExcel")
    public ResultModel uploadExcel(@RequestParam("file") MultipartFile file) {
        //第一个参数为Excel表，第二个参数为从第几行读取Excel的内容，返回值为一个字符串数组集合（每一个数组代表Excel表的一行数据）
        List<String> columnNames = Arrays.asList(
                CareInstall.AGENT_NO,
                CareInstall.PROVINCE,
                CareInstall.CITY,
                CareInstall.SCHOOL,
                CareInstall.FLOOR,
                CareInstall.DORMITORY,
                CareInstall.SN,
                CareInstall.REMARK);
        Map<String, Object> data = ExcelToolKit.uploadExcel(file, columnNames);
        if ((Integer) data.get("state") == 1) {
            List<CareInstall> list = buildUploadData((List<Line>) data.get("lines"));
            Map<String, Object> result = careInstallService.saveCareInstallBatch(list);
            return mapModel(result);
        }
        return errorModel();
    }

    private List<CareInstall> buildUploadData(List<Line> lineList) {
        ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        List<CareInstall> list = new ArrayList<>();
        for (Line line : lineList) {
            String agentNo = line.getColumns().getOrDefault(CareInstall.AGENT_NO, StringUtils.EMPTY);
            String province = line.getColumns().getOrDefault(CareInstall.PROVINCE, StringUtils.EMPTY);
            String city = line.getColumns().getOrDefault(CareInstall.CITY, StringUtils.EMPTY);
            String school = line.getColumns().getOrDefault(CareInstall.SCHOOL, StringUtils.EMPTY);
            String dormitory = line.getColumns().getOrDefault(CareInstall.DORMITORY, StringUtils.EMPTY);
            String floor = line.getColumns().getOrDefault(CareInstall.FLOOR, StringUtils.EMPTY);
            Long sn = Long.parseLong(line.getColumns().getOrDefault(CareInstall.SN, StringUtils.EMPTY));
            String remark = line.getColumns().getOrDefault(CareInstall.REMARK, StringUtils.EMPTY);

            CareInstall careInstall = new CareInstall();

            careInstall
                    .setAgentNo(agentNo)
                    .setProvince(province)
                    .setCity(city)
                    .setSchool(school)
                    .setDormitory(dormitory)
                    .setFloor(floor)
                    .setSn(sn)
                    .setRemark(remark)
                    .setCreatedAt(LocalDateTime.now())
                    .setUpdatedAt(LocalDateTime.now())
                    .setOperator(String.valueOf(shiroUser.getAccount()));
            list.add(careInstall);
        }
        return list;
    }

    @GetMapping("listBasicInfo")
    public ResultModel getBasicInfo(@RequestParam String sn) {
        List<Map<String, Object>> list = careInstallService.getBasicInfo(sn);
        return listModel(list);
    }
}

