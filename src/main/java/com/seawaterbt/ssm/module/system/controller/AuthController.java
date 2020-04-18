package com.seawaterbt.ssm.module.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.core.shiro.constant.ShiroConstant;
import com.seawaterbt.ssm.core.shiro.utils.ShiroKit;
import com.seawaterbt.ssm.core.utils.TreeUtils;
import com.seawaterbt.ssm.module.global.StateCode;
import com.seawaterbt.ssm.module.shiro.constant.PermStatus;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import com.seawaterbt.ssm.module.system.entity.SysPerm;
import com.seawaterbt.ssm.module.system.entity.SysUser;
import com.seawaterbt.ssm.module.system.service.ISysPermService;
import com.seawaterbt.ssm.module.system.service.ISysUserService;
import com.seawaterbt.ssm.module.system.vo.LoginTdo;
import com.seawaterbt.ssm.module.system.vo.UserTdo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author azx
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@Api(tags = "权限和个人信息接口")
public class AuthController extends BaseController {

    @Resource
    private ISysUserService userService;

    @Resource
    private ISysPermService permService;

    @PostMapping("/login")
    @ApiOperation(value = "登陆")
    public ResultModel login(@RequestBody @Valid LoginTdo loginTdo) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginTdo.getUserName(), loginTdo.getPassword(), false);
        try {
            currentUser.login(token);
            if (currentUser.isAuthenticated()) {
                ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
                List<Map<String, Object>> pagePermList = new ArrayList<>();
                List<Map<String, Object>> btnPermList = new ArrayList<>();
                List<Map<String, Object>> permList = user.getPermUrlList();
                for (Map<String, Object> perm : permList) {
                    Integer isMenu = (Integer) perm.get(SysPerm.IS_MENU);
                    if (isMenu == PermStatus.MENU.getCode()) {
                        pagePermList.add(perm);
                    } else {
                        btnPermList.add(perm);
                    }
                }
                Map<String, Object> data = new HashMap<>(3);
                data.put(ShiroConstant.TOKEN, SecurityUtils.getSubject().getSession().getId().toString());
                data.put("list", TreeUtils.tree(pagePermList, null));
                data.put("permsBtnList", btnPermList);
                userService.updateUserError(user.getId(), null, 0, null);
                return successModel(data);
            } else {
                token.clear();
                log.error("登陆验证失败，请重新登陆");
                return errorModel();
            }

        } catch (Exception e) {
            if (e instanceof LockedAccountException) {
                return resultModel(StateCode.ACCOUNT_LOCK);
            } else if (e instanceof UnknownAccountException) {
                return resultModel(StateCode.ACCOUNT_NOT);
            } else if (e instanceof IncorrectCredentialsException) {
                return resultModel(StateCode.ACCOUNT_PASS_ERROR);
            } else {
                e.printStackTrace();
                return errorModel();

            }
        }
    }

    @ApiOperation(value = "退出登陆")
    @PostMapping("/logout")
    public Object logOut() {
        SecurityUtils.getSubject().logout();
        return successModel();
    }

    @GetMapping("/getInfo")
    @ApiOperation(value = "获取用户基本信息")
    public ResultModel getUserInfo() {
        Session session = SecurityUtils.getSubject().getSession();
        ShiroUser sysUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        if (sysUser != null) {
            Object isKitouted = session.getAttribute(ShiroConstant.SESSION_KEY_KICKOUT);
            if (isKitouted != null && (boolean) isKitouted) {
                session.stop();
                return resultModel(StateCode.ACCOUNT_REPEAT_LOGIN);
            }
            Map<String, Object> map = new HashMap<>(1);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(sysUser));
            map.put("user", jsonObject);
            List<Map<String, Object>> pagePermList = new ArrayList<>();
            List<Map<String, Object>> btnPermList = new ArrayList<>();
            List<Map<String, Object>> permList = sysUser.getPermUrlList();
            for (Map<String, Object> perm : permList) {
                Integer isMenu = (Integer) perm.get(SysPerm.IS_MENU);
                if (isMenu == PermStatus.MENU.getCode()) {
                    pagePermList.add(perm);
                } else {
                    btnPermList.add(perm);
                }
            }
            map.put("list", TreeUtils.tree(pagePermList, null));
            map.put("permsBtnList", btnPermList);
            return successModel(map);
        }
        log.error("检测到用户未登录");
        return resultModel(StateCode.NO_LOGIN);
    }

    @ApiOperation(value = "修改用户密码")
    @PostMapping("/updateUserPwd")
    public ResultModel updatePwd(@RequestBody String param) {
        JSONObject json = JSONObject.parseObject(param);
        String oldPwd = json.get("oldPwd").toString();
        String pwd = json.get("pwd").toString();
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        String password = ShiroKit.md5(oldPwd, sysUser.getSalt());
        if (!password.equals(sysUser.getPassword())) {
            return resultModel(StateCode.OLD_PWD_ERROR);
        }
        sysUser.setSalt(ShiroKit.getRandomSalt(ShiroConstant.RANDOM_SALT_LENGTH))
                .setPassword(ShiroKit.md5(pwd, sysUser.getSalt()));
        userService.updateUser(sysUser, null);
        return successModel();
    }


    @PostMapping("updateUserInfo")
    @ApiOperation(value = "修改个人信息")
    public ResultModel updateUserInfo(@RequestBody @Valid UserTdo userTdo) {
        SysUser sysUser = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        sysUser.setPhone(userTdo.getPhone());
        sysUser.setEmail(userTdo.getEmail());
        boolean succ = userService.updateById(sysUser);
        if (succ) {
            return successModel();
        }
        return errorModel();
    }
}
