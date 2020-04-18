package com.seawaterbt.ssm.module.business.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareWechatUsers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
public interface ICareWechatUsersService extends IService<CareWechatUsers> {

    Page<Map<String,Object>> getWechatUserPage(Map<String,Object> params);

    List<Map<String,Object>> getWechatUserList(Map<String,Object> params);
}
