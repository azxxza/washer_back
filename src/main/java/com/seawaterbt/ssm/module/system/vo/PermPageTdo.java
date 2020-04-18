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
@ApiModel(value = "permTdo", description = "菜单")
public class PermPageTdo extends PageTdo {

    @ApiModelProperty(value = "菜单名称", name = "title", example = "用户管理")
    private String title;

    @ApiModelProperty(value = "ID", name = "id", example = "1")
    private Integer id;

    @ApiModelProperty(value = "是否菜单", name = "isMenu", example = "1")
    private Integer isMenu;

    @ApiModelProperty(value = "上级菜单ID", name = "parentId", example = "1")
    private Integer parentId;

}
