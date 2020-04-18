package com.seawaterbt.ssm.module.system.vo;

import com.seawaterbt.ssm.module.global.PageTdo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "userTdo", description = "用户")
public class UserPageTdo extends PageTdo {

    @ApiModelProperty(value = "账号", name = "account", example = "admin")
    private String account;

    @ApiModelProperty(value = "姓名", name = "name", example = "admin")
    private String name;
}
