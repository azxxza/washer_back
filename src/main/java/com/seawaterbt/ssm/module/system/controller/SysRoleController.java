package com.seawaterbt.ssm.module.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.module.global.StateCode;
import com.seawaterbt.ssm.module.system.entity.SysRole;
import com.seawaterbt.ssm.module.system.service.ISysRoleService;
import com.seawaterbt.ssm.module.system.vo.RolePageTdo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author azx
 * @since 2018-09-29
 */
@RestController
@RequestMapping("/sysRole")
@Slf4j
@Api(tags = "角色操作接口")
public class SysRoleController extends BaseController {

    @Resource
    private ISysRoleService roleService;

    @GetMapping("/listPage")
    @ApiOperation(value = "列表（分页）")
    public Object page(RolePageTdo rolePageTdo) {
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(rolePageTdo));
        Page<SysRole> page = roleService.selectRoleList(jsonObject);
        return pageModel(page);
    }

    @GetMapping("/listAll")
    @ApiOperation(value = "列表")
    public Object allList() {
        List<SysRole> list = roleService.list();
        return listModel(list);
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增")
    public Object saveRole(@RequestBody SysRole role) {
        SysRole theRole = roleService.selectByRoleName(role.getName());
        if (theRole != null) {
            return resultModel(StateCode.ROLE_NAME_EXIT);
        }
        boolean success = roleService.saveSysRole(role);
        return defaultModel(success);
    }


    @PutMapping("/update")
    @ApiOperation(value = "更新")
    public Object updateRole(@RequestBody SysRole role) {
        boolean success = this.roleService.updateById(role);
        return defaultModel(success);
    }


    @DeleteMapping("/deleteRole")
    @ApiOperation(value = "删除")
    public Object deleteRole(Integer roleId) {
        boolean success = this.roleService.removeById(roleId);
        return defaultModel(success);
    }


}
