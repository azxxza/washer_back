package com.seawaterbt.ssm.module.shiro.model;

import com.seawaterbt.ssm.module.system.entity.SysUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
@Getter
@Setter
public class ShiroUser extends SysUser {

    public ShiroUser(SysUser sysUser, List<Map<String, Object>> permUrlList) {
        super(
                sysUser.getId(),
                sysUser.getAvatar(),
                sysUser.getAccount(),
                sysUser.getPassword(),
                sysUser.getSalt(),
                sysUser.getName(),
                sysUser.getSex(),
                sysUser.getEmail(),
                sysUser.getPhone(),
                sysUser.getStatus(),
                sysUser.getCreateTime(),
                sysUser.getUpdateTime());
        this.setPermUrlList(permUrlList);
    }

    public ShiroUser() {

    }

    private List<Map<String, Object>> permUrlList;
}


