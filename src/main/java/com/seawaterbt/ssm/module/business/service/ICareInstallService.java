package com.seawaterbt.ssm.module.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2020-03-07
 */
public interface ICareInstallService extends IService<CareInstall> {

    Page<CareInstall> getCareInstallPage(Map<String, Object> params);

    List<CareInstall> getCareInstallList(Map<String, Object> params);

    List<Map<String, Object>> getBasicInfo(String sn);

    boolean saveCareInstall(CareInstall careInstall);

    boolean deleteCareInstall(int id);

    Map<String, Object> saveCareInstallBatch(List<CareInstall> list);

    boolean updateCareInstall(CareInstall careInstall);
}
