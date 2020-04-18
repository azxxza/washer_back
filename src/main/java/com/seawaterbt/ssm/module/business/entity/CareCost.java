package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value = "CareCost对象", description = "")
public class CareCost extends Model<CareCost> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String costname;

    private String costtype;

    private String costmodel;

    private String cost;

    private Integer discount;

    private Integer deposit;

    @ApiModelProperty(value = "自然月,0非自然月1自然月")
    @TableField("naturalMon")
    private Integer naturalMon;

    @TableField("naturalMonDesc")
    private String naturalMonDesc;

    private String remark;

    private String operator;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "优惠描述")
    @TableField("discountDesc")
    private String discountDesc;


    public static final String ID = "id";

    public static final String COSTNAME = "costname";

    public static final String COSTTYPE = "costtype";

    public static final String COSTMODEL = "costmodel";

    public static final String COST = "cost";

    public static final String DISCOUNT = "discount";

    public static final String DEPOSIT = "deposit";

    public static final String NATURALMON = "naturalMon";

    public static final String NATURALMONDESC = "naturalMonDesc";

    public static final String REMARK = "remark";

    public static final String OPERATOR = "operator";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    public static final String DISCOUNT_DESC = "discountDesc";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
