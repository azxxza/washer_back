package com.seawaterbt.ssm.module.business.vo;

import com.seawaterbt.ssm.module.global.PageTdo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CareInstallPageTdo extends PageTdo {

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
}
