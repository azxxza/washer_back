package com.seawaterbt.ssm.module.business.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.seawaterbt.ssm.module.global.PageTdo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CareCostPageTdo extends PageTdo {

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
    
}
