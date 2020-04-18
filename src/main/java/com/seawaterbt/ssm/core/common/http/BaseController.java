package com.seawaterbt.ssm.core.common.http;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.global.StateCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseController {

    private static final String TOTAL = "total";

    private static final String RECORDS = "records";

    protected ResultModel successModel() {
        return new ResultModel(StateCode.SUCCESS);
    }

    protected ResultModel errorModel() {
        return new ResultModel(StateCode.ERROR);
    }

    protected ResultModel resultModel(StateCode stateCode) {
        return new ResultModel(stateCode);
    }

    protected ResultModel resultMsg(int code, String msg) {
        return new ResultModel(code, msg);
    }

    protected ResultModel successModel(Map<String, ?> data) {
        return new ResultModel(data);
    }

    protected ResultModel pageModel(Page<?> page) {
        Map<String, Object> map = new HashMap<>();
        map.put(TOTAL, page.getTotal());
        map.put(RECORDS, page.getRecords());
        return new ResultModel(map);
    }

    protected ResultModel pageModel(Page<?> page, Map<String, Object> extraData) {
        ResultModel resultModel = this.pageModel(page);
        resultModel.putAll(extraData);
        return resultModel;
    }

    protected ResultModel listModel(List<?> list) {
        return new ResultModel(list);
    }

    protected ResultModel mapModel(Map<String, Object> map) {
        return new ResultModel(map);
    }

    protected ResultModel defaultModel(boolean isSuccess) {
        return isSuccess ? successModel() : errorModel();
    }

    protected ResultModel defaultModel(Model<?> model) {
        return new ResultModel(model);
    }

    protected ResultModel defaultModel(List<?> list) {
        return new ResultModel(list);
    }

}
