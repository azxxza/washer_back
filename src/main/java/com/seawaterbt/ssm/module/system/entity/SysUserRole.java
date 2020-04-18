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
 * 用户角色
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user_role")
@ApiModel(value = "SysUserRole对象", description = "用户角色")
public class SysUserRole extends Model<SysUserRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户Id")
    private Integer userId;

    @ApiModelProperty(value = "角色Id")
    private Integer roleId;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String ROLE_ID = "role_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
