package com.seawaterbt.ssm.module.business.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareOutpower;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-17
 */
public interface ICareOutpowerService extends IService<CareOutpower> {

    Page<CareOutpower> getOutPokerList(JSONObject params);
}
