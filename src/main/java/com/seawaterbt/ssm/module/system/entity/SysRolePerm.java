package com.seawaterbt.ssm.module.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_role_perm")
@ApiModel(value = "SysRolePerm对象", description = "角色权限")
public class SysRolePerm extends Model<SysRolePerm> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "权限Id")
    private Integer permId;

    @ApiModelProperty(value = "角色Id")
    private Integer roleId;


    public static final String ID = "id";

    public static final String PERM_ID = "perm_id";

    public static final String ROLE_ID = "role_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
