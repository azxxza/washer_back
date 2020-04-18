package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.entity.CareWasherusermonthcard;
import com.seawaterbt.ssm.module.business.service.ICareWasherusermonthcardService;
import com.seawaterbt.ssm.module.business.vo.CareWasherUserMonthCardPageTdo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 租赁管理
 *
 * @author azx
 * @since 2020-03-08
 */
@RestController
@RequestMapping("/careWasherusermonthcard")
public class CareWasherusermonthcardController extends BaseController {

    @Resource
    private ICareWasherusermonthcardService careWasherusermonthcardService;

    @GetMapping("listPage")
    public ResultModel page(CareWasherUserMonthCardPageTdo userMonthCardPageTdo) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(userMonthCardPageTdo));
        Page<CareWasherusermonthcard> page = careWasherusermonthcardService.getWasherUserMonthCardPage(jsonObject);
        return pageModel(page);
    }

    @GetMapping("list")
    public ResultModel list(CareWasherUserMonthCardPageTdo userMonthCardPageTdo) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(userMonthCardPageTdo));
        List<CareWasherusermonthcard> list = careWasherusermonthcardService.getWasherUserMonthCardList(jsonObject);
        return listModel(list);
    }

    @PutMapping("update")
    public ResultModel update(@RequestBody CareWasherusermonthcard careWasherusermonthcard) {
        boolean success = careWasherusermonthcardService.updateWasherUserMonthCard(careWasherusermonthcard);
        return defaultModel(success);
    }
}

