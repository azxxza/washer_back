package com.seawaterbt.ssm.module.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.core.utils.DateUtils;
import com.seawaterbt.ssm.module.system.dao.SysRoleMapper;
import com.seawaterbt.ssm.module.system.entity.SysRole;
import com.seawaterbt.ssm.module.system.service.ISysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public Page<SysRole> selectRoleList(Map<String, Object> params) {
        Page<SysRole> page = new PageFactory<SysRole>().defaultPage(params);
        List<SysRole> list = sysRoleMapper.selectRoleList(page, params);
        page.setRecords(list);
        return page;
    }

    @Override
    public SysRole selectByRoleName(String name) {
        SysRole role = new SysRole();
        role.setName(name);
        return sysRoleMapper.selectOne(new QueryWrapper<>(role));
    }

    @Override
    public boolean saveSysRole(SysRole sysRole) {
        sysRole.setCreateTime(DateUtils.getTimeStamp());
        int count = sysRoleMapper.insert(sysRole);
        return count >= 1;
    }
}
