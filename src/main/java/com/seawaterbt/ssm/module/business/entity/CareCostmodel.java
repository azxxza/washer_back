package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2020-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CareCostmodel对象", description="")
public class CareCostmodel extends Model<CareCostmodel> {

    private static final long serialVersionUID=1L;

      private Long id;

    private String costtype;

    private Integer costmodelid;

    private String costmodelname;

    private String displayname;

    private String modelname;

    @TableField("washerTime")
    private String washerTime;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String COSTTYPE = "costtype";

    public static final String COSTMODELID = "costmodelid";

    public static final String COSTMODELNAME = "costmodelname";

    public static final String DISPLAYNAME = "displayname";

    public static final String MODELNAME = "modelname";

    public static final String WASHERTIME = "washerTime";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
