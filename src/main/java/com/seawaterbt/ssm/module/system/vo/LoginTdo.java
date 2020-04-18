package com.seawaterbt.ssm.module.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@ApiModel(value = "loginTdo", description = "登陆")
public class LoginTdo {

    @ApiModelProperty(value = "用户名", name = "userName", example = "admin")
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    @ApiModelProperty(value = "密码", name = "password", example = "123456")
    @NotEmpty(message = "密码不能为空")
    private String password;

}
