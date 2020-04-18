package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("care_WasherUserMonthCard")
@ApiModel(value = "CareWasherusermonthcard对象", description = "")
public class CareWasherusermonthcard extends Model<CareWasherusermonthcard> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    @TableField("wechatID")
    private String wechatID;

    @TableField("bgnDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime bgnDate;

    @TableField("endDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static final String ID = "ID";

    public static final String WECHATID = "wechatID";

    public static final String BGNDATE = "bgnDate";

    public static final String ENDDATE = "endDate";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
