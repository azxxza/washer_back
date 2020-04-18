package com.seawaterbt.ssm.module.shiro.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    Freeze(0, "冻结"), Normal(1, "正常");
    private int code;
    private String description;
}

