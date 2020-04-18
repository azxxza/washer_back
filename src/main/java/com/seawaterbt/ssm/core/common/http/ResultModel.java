package com.seawaterbt.ssm.core.common.http;

import com.seawaterbt.ssm.module.global.StateCode;

import java.util.HashMap;

public class ResultModel extends HashMap<String, Object> {

    public static final String CODE = "status";
    public static final String MSG = "message";
    public static final String DATA = "response";

    public ResultModel(int code, String msg) {
        put(CODE, code);
        put(MSG, msg);
    }

    public ResultModel(StateCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg());
    }

    public ResultModel(Object object) {
        this(StateCode.SUCCESS.getCode(), StateCode.SUCCESS.getMsg());
        put(DATA, object);
    }
}
