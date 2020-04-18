package com.seawaterbt.ssm.module.business.controller.wechat;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.entity.CareWechatUsers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("wechatLogin")
@Slf4j
public class WechatLogin extends BaseController {

    @GetMapping("login")
    public Object resultModel(String rawData, String signature, String code) {
        log.info("Start get SessionKey");


        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("用户非敏感信息" + rawData);

        JSONObject rawDataJson = JSON.parseObject(rawData);

        System.out.println("签名" + signature);
        JSONObject SessionKeyOpenId = null;
//                getSessionKeyOrOpenId(code);
        System.out.println("post请求获取的SessionAndopenId=" + SessionKeyOpenId);

        String openid = SessionKeyOpenId.getString("openid");

        String sessionKey = SessionKeyOpenId.getString("session_key");

        System.out.println("openid=" + openid + ",session_key=" + sessionKey);

        CareWechatUsers user = new CareWechatUsers();
//                userService.findByOpenid(openid);
        //uuid生成唯一key
        String skey = UUID.randomUUID().toString();
        if (user == null) {
            //入库
            String nickName = rawDataJson.getString("nickName");
            String avatarUrl = rawDataJson.getString("avatarUrl");
            String gender = rawDataJson.getString("gender");
            String city = rawDataJson.getString("city");
            String country = rawDataJson.getString("country");
            String province = rawDataJson.getString("province");
            user.insert();
        } else {
            //已存在
            log.info("用户openid已存在,不需要插入");
        }

        //把新的sessionKey和oppenid返回给小程序
        map.put("skey", skey);
        map.put("result", "0");
//        JSONObject userInfo = getUserInfo(encrypteData, sessionKey, iv);
//        System.out.println("根据解密算法获取的userInfo=" + userInfo);
//        userInfo.put("balance", user.getUbalance());
//        map.put("userInfo", userInfo);


        return map;
    }
}
