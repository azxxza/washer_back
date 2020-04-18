package com.seawaterbt.ssm.module.business.entity;

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
 * @since 2020-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CareConsumePrestatistics对象", description="")
public class CareConsumePrestatistics extends Model<CareConsumePrestatistics> {

    private static final long serialVersionUID=1L;

    private String school;

    private String floor;

    @TableField("termCount")
    private Integer termCount;

    @TableField("DCRenewMoney")
    private Double DCRenewMoney;

    @TableField("DCRenewCount")
    private Integer DCRenewCount;

    @TableField("BYRenewMoney")
    private Double BYRenewMoney;

    @TableField("BYRenewCount")
    private Integer BYRenewCount;

    private Double deposit;

    private LocalDateTime opttime;


    public static final String SCHOOL = "school";

    public static final String FLOOR = "floor";

    public static final String TERMCOUNT = "termCount";

    public static final String DCRENEWMONEY = "DCRenewMoney";

    public static final String DCRENEWCOUNT = "DCRenewCount";

    public static final String BYRENEWMONEY = "BYRenewMoney";

    public static final String BYRENEWCOUNT = "BYRenewCount";

    public static final String DEPOSIT = "deposit";

    public static final String OPTTIME = "opttime";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
