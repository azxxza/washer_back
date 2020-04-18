package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("care_wechat_users")
@ApiModel(value = "CareWechatUsers对象", description = "")
public class CareWechatUsers extends Model<CareWechatUsers> {

    private static final long serialVersionUID = 1L;

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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String APPID = "appid";

    public static final String OPENID = "openid";

    public static final String UNIONID = "unionid";

    public static final String NAME = "name";

    public static final String NICKNAME = "nickname";

    public static final String SEX = "sex";

    public static final String LANGUAGE = "language";

    public static final String CITY = "city";

    public static final String PROVINCE = "province";

    public static final String COUNTRY = "country";

    public static final String HEADIMGURL = "headimgurl";

    public static final String SUBSCRIBE = "subscribe";

    public static final String SUBSCRIBE_TIME = "subscribe_time";

    public static final String REMARK = "remark";

    public static final String GROUPID = "groupid";

    public static final String TYPE = "type";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
