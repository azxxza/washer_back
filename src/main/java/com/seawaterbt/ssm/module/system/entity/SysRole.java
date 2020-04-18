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
 * 角色
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_role")
@ApiModel(value = "SysRole对象", description = "角色")
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "别名")
    private String tips;

    @ApiModelProperty(value = "创建时间")
    private Integer createTime;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String TIPS = "tips";

    public static final String CREATE_TIME = "create_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
