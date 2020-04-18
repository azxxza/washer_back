package com.seawaterbt.ssm.module.system.vo;

import com.seawaterbt.ssm.module.global.PageTdo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LogPageTdo extends PageTdo {
    private String username;
    private String startTime;
    private String endTime;
}
