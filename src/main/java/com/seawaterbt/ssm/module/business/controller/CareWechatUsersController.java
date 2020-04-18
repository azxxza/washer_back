package com.seawaterbt.ssm.module.business.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.service.ICareWechatUsersService;
import com.seawaterbt.ssm.module.business.vo.CareWechatUserPageTdo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 消费者管理
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
@RestController
@RequestMapping("/careWechatUsers")
public class CareWechatUsersController extends BaseController {

    @Resource
    private ICareWechatUsersService careWechatUsersService;

    @GetMapping("listPage")
    public ResultModel page(CareWechatUserPageTdo wechatUserPageTdo) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(wechatUserPageTdo));
        Page<Map<String, Object>> page = careWechatUsersService.getWechatUserPage(jsonObject);
        return pageModel(page);
    }

    @GetMapping("list")
    public ResultModel list(CareWechatUserPageTdo wechatUserPageTdo) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(wechatUserPageTdo));
        List<Map<String, Object>> list = careWechatUsersService.getWechatUserList(jsonObject);
        return listModel(list);
    }
}

