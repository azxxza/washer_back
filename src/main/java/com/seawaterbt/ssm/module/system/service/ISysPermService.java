package com.seawaterbt.ssm.module.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seawaterbt.ssm.module.system.entity.SysPerm;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * ${table.comment} 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-05
 */
public interface ISysPermService extends IService<SysPerm> {

    List<Map<String, Object>> selectPermList(Map<String, Object> params);

    Page<SysPerm> selectPermPage(Map<String, Object> params);

    List<Map<String, Object>> selectCascader();

    List<Map<String, Object>> selectAllPermList(String title);

    List<Integer> selectPermByRoleId(Integer roleId);


}
