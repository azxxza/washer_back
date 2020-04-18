package com.seawaterbt.ssm.module.shiro.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PermStatus {
    MENU(1, "菜单"), BUTTON(0, "按钮");
    private int code;
    private String description;
}
