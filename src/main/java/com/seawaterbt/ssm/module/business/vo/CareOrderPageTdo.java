package com.seawaterbt.ssm.module.business.vo;

import com.seawaterbt.ssm.module.global.PageTdo;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CareOrderPageTdo extends PageTdo {

    @ApiParam(required = true)
    private String sn;
}
