package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
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
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CareFault对象", description="")
public class CareFault extends Model<CareFault> {

    private static final long serialVersionUID=1L;

      private Long id;

    private Long sn;

    private String fault;

    private String remark;

    private String connectphone;

    @ApiModelProperty(value = "0:未完结1:已完结")
    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String SN = "sn";

    public static final String FAULT = "fault";

    public static final String REMARK = "remark";

    public static final String CONNECTPHONE = "connectphone";

    public static final String STATUS = "status";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
