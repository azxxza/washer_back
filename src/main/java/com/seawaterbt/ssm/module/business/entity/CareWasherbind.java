package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("care_WasherBind")
@ApiModel(value="CareWasherbind对象", description="")
public class CareWasherbind extends Model<CareWasherbind> {

    private static final long serialVersionUID=1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;

    @TableField("wechatID")
    private String wechatID;

    @TableField("washerSN")
    private Long washerSN;

    @TableField("washerName")
    private String washerName;

    @TableField("bindTime")
    private LocalDateTime bindTime;

    private String corpId;


    public static final String ID = "ID";

    public static final String WECHATID = "wechatID";

    public static final String WASHERSN = "washerSN";

    public static final String WASHERNAME = "washerName";

    public static final String BINDTIME = "bindTime";

    public static final String CORP_ID = "corp_id";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
