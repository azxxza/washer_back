package com.seawaterbt.ssm.module.business.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareInstall;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author azx
 * @since 2020-03-07
 */
public interface CareInstallMapper extends BaseMapper<CareInstall> {

    List<CareInstall> selectCareInstallList(Page<CareInstall> page, @Param("params") Map<String, Object> params);

    List<Map<String, Object>> selectBasicInfo(@Param("sn") String sn);

    List<String> selectSnList(List<String> list);

}
