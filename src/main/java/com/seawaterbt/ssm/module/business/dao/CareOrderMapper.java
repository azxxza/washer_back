package com.seawaterbt.ssm.module.business.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author azx
 * @since 2020-03-20
 */
public interface CareOrderMapper {

    List<Map<String, Object>> selectCareOrderList(Page<Map<String, Object>> page, @Param("params") Map<String, Object> params);
}
