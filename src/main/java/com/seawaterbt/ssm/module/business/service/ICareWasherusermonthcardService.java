package com.seawaterbt.ssm.module.business.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareWasherusermonthcard;
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
public interface ICareWasherusermonthcardService extends IService<CareWasherusermonthcard> {

    Page<CareWasherusermonthcard> getWasherUserMonthCardPage(Map<String,Object> params);

    List<CareWasherusermonthcard> getWasherUserMonthCardList(Map<String,Object> params);

    boolean updateWasherUserMonthCard(CareWasherusermonthcard careWasherusermonthcard);
}
