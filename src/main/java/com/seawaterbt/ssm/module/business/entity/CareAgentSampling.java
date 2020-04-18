package com.seawaterbt.ssm.module.business.entity;

import java.math.BigDecimal;
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
@TableName("care_agent_sampling")
@ApiModel(value="CareAgentSampling对象", description="")
public class CareAgentSampling extends Model<CareAgentSampling> {

    private static final long serialVersionUID=1L;

    private Integer id;

    @ApiModelProperty(value = "登陆账户ID")
    private String username;

    @ApiModelProperty(value = "分成比例")
    private BigDecimal ratio;

    @ApiModelProperty(value = "下次抽样日期")
    @TableField("samplingDate")
    private LocalDateTime samplingDate;


    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String RATIO = "ratio";

    public static final String SAMPLINGDATE = "samplingDate";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
