package com.seawaterbt.ssm.module.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.system.dao.SysPermMapper;
import com.seawaterbt.ssm.module.system.dao.SysRolePermMapper;
import com.seawaterbt.ssm.module.system.entity.SysPerm;
import com.seawaterbt.ssm.module.system.service.ISysPermService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class SysPermServiceImpl extends ServiceImpl<SysPermMapper, SysPerm> implements ISysPermService {

    @Resource
    private SysPermMapper permMapper;

    @Override
    public Page<SysPerm> selectPermPage(Map<String, Object> params) {
        Page<SysPerm> page = new PageFactory<SysPerm>().defaultPage(params);
        List<SysPerm> list = permMapper.selectPermPage(page, params);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<Map<String, Object>> selectPermList(Map<String, Object> params) {
        return permMapper.selectPermList(params);
    }

    @Override
    public List<Map<String, Object>> selectCascader() {
        return permMapper.selectCascader();
    }

    @Override
    public List<Map<String, Object>> selectAllPermList(String title) {
        return permMapper.selectAllPermList(title);
    }

    @Override
    public List<Integer> selectPermByRoleId(Integer roleId) {
        return permMapper.selectPermByRoleId(roleId);
    }

}
