package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("Care_WasherHeartbeat")
@ApiModel(value="CareWasherheartbeat对象", description="")
public class CareWasherheartbeat extends Model<CareWasherheartbeat> {

    private static final long serialVersionUID=1L;

    private Long id;

      private Long simnum;

    private LocalDateTime heartbeattime;

    private LocalDateTime logintime;


    public static final String ID = "id";

    public static final String SIMNUM = "simnum";

    public static final String HEARTBEATTIME = "heartbeattime";

    public static final String LOGINTIME = "logintime";

    @Override
    protected Serializable pkVal() {
        return this.simnum;
    }

}
