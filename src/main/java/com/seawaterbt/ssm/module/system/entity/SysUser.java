package com.seawaterbt.ssm.module.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author azx
 * @since 2020-03-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_sys_user")
@ApiModel(value = "SysUser对象", description = "用户")
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "账号/用户名")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "加密串")
    private String salt;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别（0: 男；1: 女）")
    private Integer sex;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "0: 禁用；1:正常")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Integer createTime;

    @ApiModelProperty(value = "修改时间")
    private Integer updateTime;


    public static final String ID = "id";

    public static final String AVATAR = "avatar";

    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String SALT = "salt";

    public static final String NAME = "name";

    public static final String SEX = "sex";

    public static final String EMAIL = "email";

    public static final String PHONE = "phone";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
