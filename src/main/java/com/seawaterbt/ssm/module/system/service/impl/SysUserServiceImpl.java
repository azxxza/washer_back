package com.seawaterbt.ssm.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.system.dao.SysRoleMapper;
import com.seawaterbt.ssm.module.system.dao.SysUserMapper;
import com.seawaterbt.ssm.module.system.dao.SysUserRoleMapper;
import com.seawaterbt.ssm.module.system.entity.SysUser;
import com.seawaterbt.ssm.module.system.entity.SysUserRole;
import com.seawaterbt.ssm.module.system.service.ISysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * ${table.comment} 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public Page<Map<String, Object>> selectUserPageList(Map<String, Object> params) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage(params);
        List<Map<String, Object>> list = sysUserMapper.selectUserPageList(page, params);
        for (Map<String, Object> map : list) {
            if (map.get("roleId") != null) {
                String roleId = map.get("roleId").toString();
                String[] array = roleId.split(",");
                Integer[] integers = new Integer[array.length];
                for (int i = 0; i < array.length; i++) {
                    integers[i] = Integer.parseInt(array[i]);
                }
                map.put("roleId", integers);
            } else {
                map.put("roleId", "");
            }
        }
        page.setRecords(list);
        return page;
    }

    @Override
    public SysUser findUserByUsername(String username) {
        return sysUserMapper.selectOne(new QueryWrapper<>(new SysUser().setAccount(username)));
    }

    @Transactional
    @Override
    public boolean saveUser(SysUser sysUser, List<Integer> roleId) {
        int count = sysUserMapper.insert(sysUser);
        List<SysUserRole> list = new ArrayList<>();
        if (count > 0) {
            for (Integer id : roleId) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setRoleId(id);
                list.add(sysUserRole);
            }
            sysUserRoleMapper.insertUserRoleBatch(list);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public void updateUser(SysUser sysUser, List<Integer> roleId) {
        if (roleId != null) {
            sysUserRoleMapper.deleteByUserId(sysUser.getId());
            for (Integer id : roleId) {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setRoleId(id);
                sysUserRoleMapper.insert(sysUserRole);
            }
        }
        sysUserMapper.updateById(sysUser);
    }

    @Transactional
    @Override
    public boolean deleteUser(Integer userId) {
        sysUserMapper.deleteById(userId);
        sysUserRoleMapper.deleteByUserId(userId);
        return true;
    }

    @Override
    public boolean updateUserError(Integer userId, Integer status, Integer errotTimes, String avatar) {
        return false;
    }

}
