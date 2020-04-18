package com.seawaterbt.ssm.module.business.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.seawaterbt.ssm.module.global.PageTdo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author azx
 * @since 2020-03-20
 */
@Getter
@Setter
@ToString
public class CareCarPageTdo extends PageTdo {

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

    private String SWVersion;

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

}
