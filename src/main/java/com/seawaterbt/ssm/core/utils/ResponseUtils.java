package com.seawaterbt.ssm.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.global.StateCode;
import org.springframework.http.MediaType;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class ResponseUtils {

    public static void setErrorResponse(ServletResponse response) {
        setResponse(response, StateCode.ERROR);
    }


    public static void setResponse(ServletResponse response, StateCode stateCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(ResultModel.CODE, stateCode.getCode());
        jsonObject.put(ResultModel.MSG, stateCode.getMsg());
        PrintWriter out = null;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setCharacterEncoding(StandardCharsets.UTF_8.name());
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            out = response.getWriter();
            out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                out.flush();
                out.close();
            }
        }
    }

}
