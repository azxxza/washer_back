package com.seawaterbt.ssm.module.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seawaterbt.ssm.module.shiro.model.ShiroUser;
import com.seawaterbt.ssm.module.system.dao.SysPermMapper;
import com.seawaterbt.ssm.module.system.dao.SysRolePermMapper;
import com.seawaterbt.ssm.module.system.dao.SysUserRoleMapper;
import com.seawaterbt.ssm.module.system.entity.SysRolePerm;
import com.seawaterbt.ssm.module.system.service.ISysRolePermService;
import com.seawaterbt.ssm.module.system.service.ISysRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-05
 */
@Service
public class SysRolePermServiceImpl extends ServiceImpl<SysRolePermMapper, SysRolePerm> implements ISysRolePermService {

    @Resource
    private SysPermMapper permMapper;

    @Resource
    private SysRolePermMapper rolePermMapper;

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Override
    @Transactional
    public boolean saveRolePerm(Integer roleId, String[] perms) {
        permMapper.deleteByRoleId(roleId);
//        List<SysRolePerm> rolePermList = new ArrayList<>();
        for (String perm : perms) {
            SysRolePerm sysRolePerm = new SysRolePerm();
            sysRolePerm.setRoleId(roleId);
            sysRolePerm.setPermId(Integer.parseInt(perm));
//            rolePermList.add(sysRolePerm);
            rolePermMapper.insert(sysRolePerm);
        }
        // 更新用户权限
        List<Integer> userIdList = userRoleMapper.selectUserIdByRoleId(roleId);
        if (userIdList.size() > 0) {
            List list = SecurityUtils.getSubject().getPrincipals().asList();
            for (Object object : list) {
                ShiroUser user = (ShiroUser) object;
                List<Map<String, Object>> permList = permMapper.selectPermList((new HashMap<String, Object>(1) {
                    {
                        put("userId", user.getId());
                    }
                }));
                user.setPermUrlList(permList);
            }
        }

        // org.apache.ibatis.executor.ExecutorException: Error getting generated key or setting result to parameter object. Cause: com.microsoft.sqlserver.jdbc.SQLServerException: 必须执行该语句才能获得结果。
//        saveBatch(rolePermList, 1000);
        return true;
    }
}
