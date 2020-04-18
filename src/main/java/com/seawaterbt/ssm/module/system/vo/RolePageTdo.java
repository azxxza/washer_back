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
@ApiModel(value = "roleTdo", description = "角色")
public class RolePageTdo extends PageTdo {

    @ApiModelProperty(value = "角色名称", name = "name", example = "管理员")
    private String name;
}
