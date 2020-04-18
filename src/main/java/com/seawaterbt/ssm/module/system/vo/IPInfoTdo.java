package com.seawaterbt.ssm.module.system.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 **/
@Getter
@Setter
@ToString
public class IPInfoTdo {
    private String ip;
    private String country;
    private String area;
    private String region;
    private String city;
    private String county;
    private String isp;
    private String GPS;

}
