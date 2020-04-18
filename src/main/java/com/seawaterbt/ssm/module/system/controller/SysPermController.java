package com.seawaterbt.ssm.module.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.model.Cascader;
import com.seawaterbt.ssm.core.common.model.Node;
import com.seawaterbt.ssm.core.common.model.Tree;
import com.seawaterbt.ssm.core.utils.TreeUtils;
import com.seawaterbt.ssm.module.system.entity.SysPerm;
import com.seawaterbt.ssm.module.system.service.ISysPermService;
import com.seawaterbt.ssm.module.system.service.ISysRolePermService;
import com.seawaterbt.ssm.module.system.vo.PermPageTdo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author azx
 * @since 2018-10-19
 */
@RestController
@RequestMapping("/sysPerm")
@Api(tags = "菜单权限接口")
public class SysPermController extends BaseController {

    @Resource
    private ISysPermService permService;

    @Resource
    private ISysRolePermService sysRolePermService;

    @GetMapping("listPage")
    @ApiOperation(value = "列表（分页）")
    public Object list(PermPageTdo permTdo) {
        JSONObject params = JSONObject.parseObject(JSONObject.toJSONString(permTdo));
        Page<SysPerm> page = permService.selectPermPage(params);
        return pageModel(page);
    }

    @GetMapping("listTree")
    @ApiOperation(value = "数型结构")
    public Object tree(Integer roleId, String title) {
        List<Map<String, Object>> list = permService.selectAllPermList(null);
        List<Integer> permIdList = null;
        if (roleId != null) {
            permIdList = permService.selectPermByRoleId(roleId);
        }
        List<Node> tree = TreeUtils.tree(list, permIdList);
        return successModel(new HashMap<String, Object>(1) {{
            put("list", tree);
        }});
    }

    @GetMapping("list")
    @ApiOperation(value = "菜单列表")
    public Object list(@ApiParam(required = true) Integer userId) {
        List<Map<String, Object>> list = permService.selectPermList(new HashMap<String, Object>(1) {{
            put("userId", userId);
        }});
        return listModel(list);
    }

    @GetMapping("listRolePerm")
    @ApiOperation(value = "角色的菜单列表")
    public Object rolePermList(@ApiParam(required = true) Integer roleId) {
        List<Integer> list = permService.selectPermByRoleId(roleId);
        return listModel(list);
    }

    @PostMapping("save")
    @ApiOperation(value = "新增")
    public Object savePerm(@RequestBody SysPerm sysPerm) {
        boolean success = permService.save(sysPerm);
        return defaultModel(success);

    }

    @PutMapping("update")
    @ApiOperation(value = "更新")
    public Object updatePerm(@RequestBody SysPerm sysPerm) {
        boolean success = permService.updateById(sysPerm);
        return defaultModel(success);
    }

    @PostMapping("saveRolePerm")
    @ApiOperation(value = "权限分配")
    public Object saveRolePerm(@RequestBody String data) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        Integer roleId = jsonObject.getInteger("roleId");
        String perms = jsonObject.getString("perms");
        String[] array = perms.split(",");
        boolean success = sysRolePermService.saveRolePerm(roleId, array);
        return defaultModel(success);
    }

    @DeleteMapping("deletePerm")
    @ApiOperation(value = "删除")
    public Object deletePerm(@RequestParam Integer permId) {
        boolean success = permService.removeById(permId);
        return defaultModel(success);
    }

    @GetMapping("getPermTree")
    @ApiOperation(value = "菜单树状遍历")
    public Object getPermTree() {
        List<Map<String, Object>> list = permService.selectAllPermList(null);
        List<Tree> trees = new ArrayList<>();
        List<Tree> treeList = new ArrayList<>();
        for (Map<String, Object> perm : list) {
            Tree tree = new Tree();
            tree.setPId(perm.get("parentId").toString())
                    .setId(perm.get("id").toString())
                    .setTitle(perm.get("title").toString());
            trees.add(tree);
        }

        for (Tree tree : trees) {
            if ("0".equals(tree.getPId())) {
                treeList.add(tree);
            }
            for (Tree it : trees) {
                if (it.getPId().equals(tree.getTitle())) {
                    if (tree.getChildren() == null || tree.getChildren().isEmpty()) {
                        tree.setChildren(new ArrayList<>());
                    }
                    tree.getChildren().add(it);
                }
            }
        }
        return listModel(treeList);
    }

    @ApiOperation(value = "菜单新增级联显示")
    @GetMapping("listPermCascader")
    public Object getPermCascader() {
        List<Map<String, Object>> list = permService.selectCascader();
        List<Cascader> trees = new ArrayList<>();
        List<Cascader> treeList = new ArrayList<>();
        for (Map<String, Object> perm : list) {
            Cascader tree = new Cascader();
            tree.setPId(perm.get(SysPerm.PARENT_ID).toString());
            tree.setValue(perm.get(SysPerm.ID).toString());
            tree.setLabel(perm.get(SysPerm.TITLE).toString());
            trees.add(tree);
        }

        for (Cascader tree : trees) {
            if ("0".equals(tree.getPId())) {
                treeList.add(tree);
            }
            for (Cascader it : trees) {
                if (it.getPId().equals(tree.getValue())) {
                    if (tree.getChildren() == null || tree.getChildren().isEmpty()) {
                        tree.setChildren(new ArrayList<>());
                    }
                    tree.getChildren().add(it);
                }
            }
        }
        return listModel(treeList);
    }
}

