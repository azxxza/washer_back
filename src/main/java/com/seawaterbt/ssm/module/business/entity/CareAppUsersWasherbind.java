package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author azx
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CareAppUsersWasherbind对象", description="")
public class CareAppUsersWasherbind extends Model<CareAppUsersWasherbind> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String unionid;

    private String phone;

    @TableField("washerSN")
    private String washerSN;

    @TableField("washerName")
    private String washerName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String UNIONID = "unionid";

    public static final String PHONE = "phone";

    public static final String WASHERSN = "washerSN";

    public static final String WASHERNAME = "washerName";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
