package com.seawaterbt.ssm.module.business.vo;

import com.seawaterbt.ssm.module.global.PageTdo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CareProfitPageTdo extends PageTdo {

    private int statType;

    private String statDate;
}
