package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.core.utils.DateUtils;
import com.seawaterbt.ssm.module.business.entity.CareCustomerPhone;
import com.seawaterbt.ssm.module.business.service.ICareCustomerPhoneService;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author azx
 * @since 2020-04-09
 */
@RestController
@RequestMapping("/careCustomerPhone")
public class CareCustomerPhoneController extends BaseController {

    @Resource
    private ICareCustomerPhoneService careCustomerPhoneService;

    @GetMapping("listPage")
    public ResultModel page(CareCustomerPhone careCustomerPhone) {
        JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(careCustomerPhone));
        Page<CareCustomerPhone> phonePage = careCustomerPhoneService.getCareCustomerPhonePage(params);
        return pageModel(phonePage);
    }

    @PostMapping("save")
    public ResultModel save(@RequestBody CareCustomerPhone careCustomerPhone) {
        careCustomerPhone.setCreateTime(DateUtils.getTimeStamp());
        careCustomerPhone.setUpdateTime(DateUtils.getTimeStamp());
        boolean success = careCustomerPhoneService.save(careCustomerPhone);
        return defaultModel(success);
    }

    @PutMapping("update")
    public ResultModel update(@RequestBody CareCustomerPhone careCustomerPhone) {
        careCustomerPhone.setUpdateTime(DateUtils.getTimeStamp());
        boolean success = careCustomerPhoneService.updateById(careCustomerPhone);
        return defaultModel(success);
    }

    @DeleteMapping("del")
    public ResultModel delete(@RequestParam Integer customerId) {
        boolean success = careCustomerPhoneService.removeById(customerId);
        return defaultModel(success);
    }
}

