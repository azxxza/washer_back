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
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CareOutpower对象", description="")
public class CareOutpower extends Model<CareOutpower> {

    private static final long serialVersionUID=1L;

      private Long id;

    @TableField("washerSN")
    private Long washerSN;

    private LocalDateTime instime;


    public static final String ID = "id";

    public static final String WASHERSN = "washerSN";

    public static final String INSTIME = "instime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
