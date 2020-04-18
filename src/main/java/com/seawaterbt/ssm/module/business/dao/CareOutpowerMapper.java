package com.seawaterbt.ssm.module.business.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareOutpower;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author azx
 * @since 2020-03-17
 */
public interface CareOutpowerMapper extends BaseMapper<CareOutpower> {

    List<CareOutpower> selectOutPokerList(Page<CareOutpower> page, @Param("params") JSONObject params);
}
