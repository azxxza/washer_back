package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareCost;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
public interface ICareCostService extends IService<CareCost> {

    Page<Map<String,Object>> getCareCostPage(Map<String, Object> params);

    List<Map<String,Object>> getCareCostList(Map<String, Object> params);

    boolean deleteCareCost(Integer costId);

    boolean saveCareCost(CareCost careCost);

    boolean updateCareCost(CareCost careCost);
}
