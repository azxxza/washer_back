package com.seawaterbt.ssm.module.business.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareCustomerPhone;
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
 * @since 2020-04-09
 */
public interface CareCustomerPhoneMapper extends BaseMapper<CareCustomerPhone> {

    List<CareCustomerPhone> selectCareCustomerPhonePage(Page<CareCustomerPhone> page, @Param("params") Map<String, Object> params);
}
