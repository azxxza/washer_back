package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

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
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("care_WasherPrePayInfo")
@ApiModel(value = "CareWasherprepayinfo对象", description = "")
public class CareWasherprepayinfo extends Model<CareWasherprepayinfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("WeChat")
    private String weChat;

    @TableField("washerSN")
    private String washerSN;

    @TableField("washerModel")
    private String washerModel;

    @TableField("RenewMoney")
    private String renewMoney;

    @TableField("OrderCode")
    private String orderCode;

    @ApiModelProperty(value = "0代表失败，1代表成功")
    @TableField("OrderStatus")
    private String orderStatus;

    @TableField("Opttime")
    private LocalDateTime opttime;

    private Integer costtype;

    private Long costid;

    private Integer discount;

    private Integer deposit;

    private String cost;

    @ApiModelProperty(value = "用户类型.0代表微信用户,1代表App用户")
    private Integer usertype;

    @ApiModelProperty(value = "支付类型.0代表微信支付")
    private Integer paytype;

    @TableField("waterLevel")
    private String waterLevel;

    private Integer paymodel;

    private Double walletFee;

    private Double walletGive;

    @TableField("Price")
    private String price;


    public static final String ID = "ID";

    public static final String WECHAT = "WeChat";

    public static final String WASHERSN = "washerSN";

    public static final String WASHERMODEL = "washerModel";

    public static final String RENEWMONEY = "RenewMoney";

    public static final String ORDERCODE = "OrderCode";

    public static final String ORDERSTATUS = "OrderStatus";

    public static final String OPTTIME = "Opttime";

    public static final String COSTTYPE = "costtype";

    public static final String COSTID = "costid";

    public static final String DISCOUNT = "discount";

    public static final String DEPOSIT = "deposit";

    public static final String COST = "cost";

    public static final String USERTYPE = "usertype";

    public static final String PAYTYPE = "paytype";

    public static final String WATERLEVEL = "waterLevel";

    public static final String PAYMODEL = "paymodel";

    public static final String WALLET_FEE = "wallet_fee";

    public static final String WALLET_GIVE = "wallet_give";

    public static final String PRICE = "Price";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
