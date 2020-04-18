package com.seawaterbt.ssm.module.system.vo;

import com.seawaterbt.ssm.module.global.PageTdo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExpRecordLogPageTdo extends PageTdo {
    @ApiModelProperty(value = "账号", name = "userName")
    private String userName;
}
