package com.seawaterbt.ssm.module.system.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@ApiModel(value = "userTdo", description = "用户")
public class UserTdo {

    @ApiModelProperty(value = "用户Id", name = "id", example = "admin")
    private Integer id;

    @ApiModelProperty(value = "账号", name = "account", example = "admin")
    private String account;

    @ApiModelProperty(value = "姓名", name = "name", example = "admin")
    private String name;

    @ApiModelProperty(value = "密码", name = "password", example = "admin")
    private String password;

    @ApiModelProperty(value = "用户名", name = "sex", example = "admin")
    private Integer sex;

    @ApiModelProperty(value = "用户名", name = "birthday", example = "admin")
    private Date birthday;

    @ApiModelProperty(value = "用户名", name = "phone", example = "admin")
    private String phone;

    @ApiModelProperty(value = "角色列表", name = "roleId", example = "[1,2]")
    private List<Integer> roleId;

    @ApiModelProperty(value = "邮箱", name = "email", example = "112@qq.com")
    private String email;
}
