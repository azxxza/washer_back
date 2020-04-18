package com.seawaterbt.ssm.module.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.core.shiro.utils.ShiroKit;
import com.seawaterbt.ssm.core.utils.DateUtils;
import com.seawaterbt.ssm.module.global.StateCode;
import com.seawaterbt.ssm.module.shiro.constant.UserStatus;
import com.seawaterbt.ssm.module.system.entity.SysUser;
import com.seawaterbt.ssm.module.system.service.ISysUserService;
import com.seawaterbt.ssm.module.system.vo.UserPageTdo;
import com.seawaterbt.ssm.module.system.vo.UserTdo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author azx
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Resource
    private ISysUserService sysUserService;

    @GetMapping("/listPage")
    public ResultModel page(UserPageTdo userPageVo) {
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(userPageVo));
        Page<Map<String, Object>> page = sysUserService.selectUserPageList(jsonObject);
        return pageModel(page);
    }

    @DeleteMapping("deleteUser")
    @ApiOperation(value = "删除用户")
    public ResultModel delete(@RequestParam Integer userId) {
        boolean success = sysUserService.deleteUser(userId);
        return defaultModel(success);
    }


    @PostMapping("/saveUser")
    @ApiOperation(value = "新增用户")
    public ResultModel save(@RequestBody UserTdo userTdo) {
        SysUser temp = sysUserService.findUserByUsername(userTdo.getAccount());
        if (temp != null) {
            return resultModel(StateCode.USER_NAME_EXIT);
        }
        SysUser sysUser = new SysUser();
        sysUser
                .setAccount(userTdo.getAccount())
                .setName(userTdo.getName())
                .setPassword(userTdo.getPassword())
                .setPhone(userTdo.getPhone())
                .setSalt(ShiroKit.getRandomSalt(ShiroConstant.RANDOM_SALT_LENGTH))
                .setPassword(ShiroKit.md5(sysUser.getPassword(), sysUser.getSalt()))
                .setCreateTime(DateUtils.getTimeStamp())
                .setUpdateTime(DateUtils.getTimeStamp())
                .setStatus(UserStatus.Normal.getCode());
        boolean success = sysUserService.saveUser(sysUser, userTdo.getRoleId());
        return defaultModel(success);
    }

    @PutMapping("updateUser")
    @ApiOperation(value = "更新用户")
    public ResultModel update(@RequestBody UserTdo userTdo) {
        SysUser sysUser = new SysUser();
        sysUser
                .setId(userTdo.getId())
                .setSex(userTdo.getSex())
                .setName(userTdo.getName())
                .setPhone(userTdo.getPhone());
        sysUserService.updateUser(sysUser, userTdo.getRoleId());
        return successModel();
    }

    @PutMapping("resetPwd")
    @ApiOperation(value = "重置密码")
    public ResultModel resetPwd(@RequestParam Integer userId) {
        SysUser sysUser = sysUserService.getById(userId);
        sysUser
                .setSalt(ShiroKit.getRandomSalt(ShiroConstant.RANDOM_SALT_LENGTH))
                .setPassword(ShiroKit.md5(ShiroConstant.DEFAULT_PWD, sysUser.getSalt()));
        sysUserService.updateUser(sysUser, null);
        return successModel();
    }

    @PutMapping("freezeUser")
    @ApiOperation(value = "用户冻结")
    public ResultModel freezeUser(@RequestParam Integer userId) {
        SysUser sysUser = sysUserService.getById(userId);
        sysUser.setStatus(UserStatus.Freeze.getCode());
        sysUserService.updateUser(sysUser, null);
        return successModel();
    }

    @PutMapping("unFreezeUser")
    @ApiOperation(value = "用户解除冻结")
    public ResultModel unFreezeUser(@RequestParam Integer userId) {
        SysUser sysUser = sysUserService.getById(userId);
        sysUser.setStatus(UserStatus.Normal.getCode());
        sysUserService.updateUser(sysUser, null);
        return successModel();
    }
}

