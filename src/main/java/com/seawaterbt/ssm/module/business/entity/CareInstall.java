package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

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
 * @since 2020-03-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CareInstall对象", description = "")
public class CareInstall extends Model<CareInstall> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String agentNo;

    private String province;

    private String city;

    private String school;

    private String floor;

    private String dormitory;

    private Long sn;

    private String sid;

    private String operator;

    private String remark;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public static final String ID = "id";

    public static final String AGENT_NO = "agent_no";

    public static final String PROVINCE = "province";

    public static final String CITY = "city";

    public static final String SCHOOL = "school";

    public static final String FLOOR = "floor";

    public static final String DORMITORY = "dormitory";

    public static final String SN = "sn";

    public static final String SID = "sid";

    public static final String OPERATOR = "operator";

    public static final String REMARK = "remark";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
