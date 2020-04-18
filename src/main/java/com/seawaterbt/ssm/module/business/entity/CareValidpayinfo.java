package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("care_validPayInfo")
@ApiModel(value="CareValidpayinfo对象", description="")
public class CareValidpayinfo extends Model<CareValidpayinfo> {

    private static final long serialVersionUID=1L;

    private String openid;

    @TableField("washerSN")
    private String washerSN;

    @TableField("washerModel")
    private String washerModel;

    @TableField("RenewMoney")
    private String RenewMoney;

    @TableField("OrderCode")
    private String OrderCode;

    @TableField("Opttime")
    private LocalDateTime Opttime;

    private String nickname;

    private String school;

    private String floor;

    private String dormitory;

    @TableField("costtypeName")
    private String costtypeName;

    private Integer deposit;

    private String telephone;

    private Integer paymodel;

    private String paytype;


    public static final String OPENID = "openid";

    public static final String WASHERSN = "washerSN";

    public static final String WASHERMODEL = "washerModel";

    public static final String RENEWMONEY = "RenewMoney";

    public static final String ORDERCODE = "OrderCode";

    public static final String OPTTIME = "Opttime";

    public static final String NICKNAME = "nickname";

    public static final String SCHOOL = "school";

    public static final String FLOOR = "floor";

    public static final String DORMITORY = "dormitory";

    public static final String COSTTYPENAME = "costtypeName";

    public static final String DEPOSIT = "deposit";

    public static final String TELEPHONE = "telephone";

    public static final String PAYMODEL = "paymodel";

    public static final String PAYTYPE = "paytype";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
