package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.module.business.entity.CareCar;
import com.baomidou.mybatisplus.extension.service.IService;
import com.seawaterbt.ssm.module.business.entity.CareInstall;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author azx
 * @since 2020-03-20
 */
public interface ICareCarService extends IService<CareCar> {

    Page<Map<String, Object>> getCareCarList(Map<String, Object> params);

    boolean saveCareCar(CareCar careCar);

    boolean updateCareCar(CareCar careCar);

    boolean removeCareCar(Integer carId);

    Map<String, Object> saveCareCarBatch(List<CareCar> list);
}
