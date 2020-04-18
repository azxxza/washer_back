package com.seawaterbt.ssm.module.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "CareCar对象", description = "")
public class CareCar extends Model<CareCar> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String simnum;

    private String sn;

    private String imsi;

    private String carnum;

    private String areaId;

    private Integer termId;

    private Integer typeId;

    private String ownername;

    private String ownertele;

    private LocalDateTime svrstarttime;

    private LocalDateTime svrendtime;

    private String icon;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String iccid;

    @TableField("SWVersion")
    private String sWVersion;

    private String operator;

    private String sid;

    @ApiModelProperty(value = "最后一次支付日期")
    private LocalDateTime lastpaytime;

    private String protocol;

    private String termtype;

    @ApiModelProperty(value = "蓝牙模块的mac地址")
    private String mac;

    private Integer commSket;

    @ApiModelProperty(value = "末次订单号")
    private String lastordercode;

    @ApiModelProperty(value = "密钥")
    private String primaryKey;


    public static final String ID = "id";

    public static final String SIMNUM = "simnum";

    public static final String SN = "sn";

    public static final String IMSI = "imsi";

    public static final String CARNUM = "carnum";

    public static final String AREA_ID = "area_id";

    public static final String TERM_ID = "term_id";

    public static final String TYPE_ID = "type_id";

    public static final String OWNERNAME = "ownername";

    public static final String OWNERTELE = "ownertele";

    public static final String SVRSTARTTIME = "svrstarttime";

    public static final String SVRENDTIME = "svrendtime";

    public static final String ICON = "icon";

    public static final String CREATED_AT = "created_at";

    public static final String UPDATED_AT = "updated_at";

    public static final String ICCID = "iccid";

    public static final String SWVERSION = "SWVersion";

    public static final String OPERATOR = "operator";

    public static final String SID = "sid";

    public static final String LASTPAYTIME = "lastpaytime";

    public static final String PROTOCOL = "protocol";

    public static final String TERMTYPE = "termtype";

    public static final String MAC = "mac";

    public static final String COMM_SKET = "comm_sket";

    public static final String LASTORDERCODE = "lastordercode";

    public static final String PRIMARY_KEY = "primaryKey";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
