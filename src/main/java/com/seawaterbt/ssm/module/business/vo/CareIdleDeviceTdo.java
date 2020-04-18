package com.seawaterbt.ssm.module.business.vo;

import com.seawaterbt.ssm.module.global.PageTdo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CareIdleDeviceTdo extends PageTdo {

    private String washerSN;

    private String school;

    private String floor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;

}
