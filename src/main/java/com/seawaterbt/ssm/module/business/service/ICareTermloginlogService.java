package com.seawaterbt.ssm.module.business.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareTermloginlog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-17
 */
public interface ICareTermloginlogService extends IService<CareTermloginlog> {

    Page<CareTermloginlog> getTeamLoginLogList(Map<String,Object> params);
}
