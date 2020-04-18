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
@TableName("care_WasherLastPrePayInfo")
@ApiModel(value="CareWasherlastprepayinfo对象", description="")
public class CareWasherlastprepayinfo extends Model<CareWasherlastprepayinfo> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("WeChat")
    private String WeChat;

    @TableField("washerSN")
    private String washerSN;

    @TableField("washerModel")
    private String washerModel;

    @TableField("RenewMoney")
    private String RenewMoney;

    @TableField("OrderCode")
    private String OrderCode;

    @TableField("OrderStatus")
    private String OrderStatus;

    @TableField("Opttime")
    private LocalDateTime Opttime;

    @TableField("Deltime")
    private LocalDateTime Deltime;

    private Integer costtype;

    private Long costid;

    private Integer discount;

    private Integer deposit;

    private String cost;

    private Integer usertype;

    private Integer paytype;

    @TableField("waterLevel")
    private String waterLevel;


    public static final String ID = "ID";

    public static final String WECHAT = "WeChat";

    public static final String WASHERSN = "washerSN";

    public static final String WASHERMODEL = "washerModel";

    public static final String RENEWMONEY = "RenewMoney";

    public static final String ORDERCODE = "OrderCode";

    public static final String ORDERSTATUS = "OrderStatus";

    public static final String OPTTIME = "Opttime";

    public static final String DELTIME = "Deltime";

    public static final String COSTTYPE = "costtype";

    public static final String COSTID = "costid";

    public static final String DISCOUNT = "discount";

    public static final String DEPOSIT = "deposit";

    public static final String COST = "cost";

    public static final String USERTYPE = "usertype";

    public static final String PAYTYPE = "paytype";

    public static final String WATERLEVEL = "waterLevel";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
