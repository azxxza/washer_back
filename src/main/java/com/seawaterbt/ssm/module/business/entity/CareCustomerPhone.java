package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-04-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CareCustomerPhone对象", description="")
public class CareCustomerPhone extends Model<CareCustomerPhone> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String phone;

    private String trueName;

    private Integer createTime;

    private Integer updateTime;

    private Integer status;


    public static final String ID = "id";

    public static final String PHONE = "phone";

    public static final String TRUE_NAME = "true_name";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    public static final String STATUS  = "status";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
