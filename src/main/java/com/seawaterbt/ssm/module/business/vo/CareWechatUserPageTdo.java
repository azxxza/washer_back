package com.seawaterbt.ssm.module.business.vo;

import com.seawaterbt.ssm.module.global.PageTdo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CareWechatUserPageTdo extends PageTdo {

    private Long id;

    private String appid;

    private String openid;

    private String unionid;

    private String name;

    private String nickname;

    private String sex;

    private String language;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private String subscribe;

    private String subscribeTime;

    private String remark;

    private String groupid;

    @ApiModelProperty(value = "0公众号1小程序2app")
    private Integer type;

    private String phone;

}
