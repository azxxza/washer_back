package com.seawaterbt.ssm.module.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.module.system.service.ISysOperationLogService;
import com.seawaterbt.ssm.module.system.vo.LogPageTdo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author azx
 * @since 2018-10-12
 */
@RestController
@RequestMapping("/log")
@Api(tags = "操作日志接口")
public class SysOperationLogController extends BaseController {

    @Resource
    private ISysOperationLogService operationLogService;

    @GetMapping("/listPage")
    @ApiOperation(value = "列表（分页）")
    public Object page(LogPageTdo logTdo) {
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(logTdo));
        Page<Map<String, Object>> page = operationLogService.selectOperationLogList(jsonObject);
        return pageModel(page);
    }
}
