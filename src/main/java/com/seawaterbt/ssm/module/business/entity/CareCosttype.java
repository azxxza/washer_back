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
 * @since 2020-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CareCosttype对象", description = "")
public class CareCosttype extends Model<CareCosttype> {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String costtypename;

    private Integer monval;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String COSTTYPENAME = "costtypename";

    public static final String MONVAL = "monval";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
