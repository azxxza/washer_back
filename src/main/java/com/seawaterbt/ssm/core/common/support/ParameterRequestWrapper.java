package com.seawaterbt.ssm.core.common.support;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.net.HttpHeaders;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> params = new HashMap<>();

    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        Map<String, String[]> requestMap = request.getParameterMap();
        this.params.putAll(requestMap);
    }

    /**
     * 重写getInputStream方法  post类型的请求参数必须通过流才能获取到值
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        // 非json类型，直接返回
        if (!super.getHeader(HttpHeaders.CONTENT_TYPE).equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            return super.getInputStream();
        }
        //为空，直接返回
        String json = IOUtils.toString(super.getInputStream(), StandardCharsets.UTF_8);
        if (StringUtils.isEmpty(json)) {
            return super.getInputStream();
        }
        Map<String, Object> map = JSON.parseObject(json);
        dealMap(map);
        ByteArrayInputStream bis = new ByteArrayInputStream(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8.name()));
        return new MyServletInputStream(bis);
    }

    private void dealMap(Map<String, Object> map) {
        Set<String> set = map.keySet();
        // 将parameter的值去除空格后重写回去
        for (String key : set) {
            Object values = map.get(key);
            if (values instanceof String) {
                values = ((String) values).trim();
            } else if (values instanceof JSONArray) {
                JSONArray json = (JSONArray) values;
                if (!json.isEmpty()) {
                    for (int i = 0; i < json.size(); i++) {
                        JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                        delJSONObject(job);
                    }
                }
            }
            map.put(key, values);
        }
    }

    private void delJSONObject(JSONObject jsonObject) {
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            Object values = entry.getValue();
            if (values instanceof String) {
                values = ((String) values).trim();
            }
            jsonObject.put(key, values);
        }
    }

    /**
     * 重写getParameter 参数从当前类中的map获取
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 重写getParameterValues
     */
    public String[] getParameterValues(String name) {//同上
        return params.get(name);
    }

    static class MyServletInputStream extends ServletInputStream {
        private ByteArrayInputStream bis;

        public MyServletInputStream(ByteArrayInputStream bis) {
            this.bis = bis;
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            //
        }

        @Override
        public int read() {
            return bis.read();
        }
    }
}
