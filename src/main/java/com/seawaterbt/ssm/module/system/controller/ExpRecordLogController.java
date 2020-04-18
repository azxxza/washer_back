package com.seawaterbt.ssm.module.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.system.entity.SysExpLog;
import com.seawaterbt.ssm.module.system.service.ISysExpLogService;
import com.seawaterbt.ssm.module.system.vo.ExpRecordLogPageTdo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author azx
 */
@RestController
@RequestMapping("/expRecordLog")
@Slf4j
@Api(tags = "异常操作日志接口")
public class ExpRecordLogController extends BaseController {

    @Resource
    private ISysExpLogService expLogService;

    @GetMapping("/listPage")
    @ApiOperation(value = "列表（分页）")
    public ResultModel page(ExpRecordLogPageTdo expRecordLogPageTdo) throws Exception {
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(expRecordLogPageTdo));
        Page<SysExpLog> list = expLogService.getList(jsonObject);
        return pageModel(list);
    }

    @GetMapping("/listDetail")
    @ApiOperation(value = "获取日志明细")
    public Map<String, Object> queryDetail(@RequestParam Integer id) throws Exception {
        SysExpLog expRecordLog = expLogService.getById(id);
        return defaultModel(expRecordLog);
    }


}
