package com.seawaterbt.ssm.module.business.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.seawaterbt.ssm.module.global.PageTdo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
@Getter
@Setter
@ToString
public class CareValidpayinfoPageTdo extends PageTdo {

    private String openid;

    private String washerSN;

    private String washerModel;

    private String renewMoney;


    @ApiModelProperty(value = "0代表失败，1代表成功")
    private String orderStatus;

    private Integer costtype;

    private Long costid;

    private Integer discount;

    private Integer deposit;

    private String cost;

    @ApiModelProperty(value = "支付类型.0代表微信支付")
    private Integer paytype;

    private Integer paymodel;

    private Double walletFee;

    private Double walletGive;

    private String price;

    private String OrderCode;

    private String nickname;

    private String school;

    private String floor;

    private String dormitory;

    private String costtypeName;

    private String telephone;

    @DateTimeFormat( pattern = "yyyy-MM-dd")
    private LocalDate begTime;

    @DateTimeFormat( pattern = "yyyy-MM-dd")
    private LocalDate endTime;


}
