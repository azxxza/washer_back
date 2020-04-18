package com.seawaterbt.ssm.module.business.controller.wechat;

import com.seawaterbt.ssm.core.wechat.tool.WxUtils;
import com.seawaterbt.ssm.module.business.service.ICareOrderService;
import com.seawaterbt.ssm.module.business.service.impl.CareOrderServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

@RestController
public class WechatPayController {

    @Resource
    private ICareOrderService orderService;

    @GetMapping("createOrder")
    public void createOrder(String orderId, BigDecimal price, String body, String ipAddress) {

    }

    @GetMapping("callback")
    public String callback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 预先设定返回的 response 类型为 xml
        response.setHeader("Content-type", "application/xml");
        // 读取参数，解析Xml为map
        Map<String, String> map = WxUtils.transferXmlToMap(WxUtils.readRequest(request));
        // 转换为有序 map，判断签名是否正确
        boolean isSignSuccess = WxUtils.checkSign(new TreeMap<String, Object>(map), "123");
        if (isSignSuccess) {
            // 签名校验成功，说明是微信服务器发出的数据
            String orderId = map.get("out_trade_no");
//            if (tradeService.hasProcessed(orderId)) // 判断该订单是否已经被接收处理过
//                return success();
//            // 可在此持久化微信传回的该 map 数据
//            //..
//            if (map.get("return_code").equals("SUCCESS")) {
//                if (map.get("result_code").equals("SUCCESS")) {
//                    orderService.finishOrder(orderId);  // 支付成功
//                } else {
//                    orderService.failOrder(orderId);    // 支付失败
//                }
//            }
            return success();
        } else {
            // 签名校验失败（可能不是微信服务器发出的数据）
            return fail();
        }
    }

    String fail() {
        return "<xml>\n" +
                "  <return_code><![CDATA[FAIL]]></return_code>\n" +
                "  <return_msg><![CDATA[]]></return_msg>\n" +
                "</xml>";
    }

    String success() {
        return "<xml>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
    }


}
