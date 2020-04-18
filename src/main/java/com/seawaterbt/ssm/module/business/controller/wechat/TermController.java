package com.seawaterbt.ssm.module.business.controller.wechat;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seawaterbt.ssm.core.common.http.BaseController;
import com.seawaterbt.ssm.core.common.http.ResultModel;
import com.seawaterbt.ssm.module.business.entity.CareCar;
import com.seawaterbt.ssm.module.business.entity.CareCost;
import com.seawaterbt.ssm.module.business.entity.CareWasherlastprepayinfo;
import com.seawaterbt.ssm.module.business.entity.CareWasherusermonthcard;
import com.seawaterbt.ssm.module.business.service.ICareAppUsersWasherbindService;
import com.seawaterbt.ssm.module.business.service.ICareCarService;
import com.seawaterbt.ssm.module.business.service.ICareWasherusermonthcardService;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("term")
public class TermController extends BaseController {

    @Resource
    private ICareCarService careCarService;

    @Resource
    private ICareWasherusermonthcardService careWasherusermonthcardService;

    @Resource
    private ICareAppUsersWasherbindService careAppUsersWasherbindService;

    @GetMapping("bindTerm")
    public ResultModel bindTerm() {

        return successModel();
    }

    @GetMapping("getTermInfo")
    public ResultModel getTermInfo(String washerSN, String wechatId) {
        Map<String, Object> termInfo = new HashMap<>();
        CareCar car = careCarService.getOne(new QueryWrapper<>(
                new CareCar().setSn(washerSN)
        ));
        termInfo.put("termInfo", car);


        Map<String, Object> modelInfo = new HashMap<>();
        CareWasherusermonthcard careWasherusermonthcard = careWasherusermonthcardService.getOne(
                new QueryWrapper<>(new CareWasherusermonthcard()
                        .setWechatID(wechatId))
        );
        CareCost careCost = new CareCost();

        if (careWasherusermonthcard != null) {
            if (careWasherusermonthcard.getEndDate().isAfter(LocalDateTime.now())) {
                // 月卡用户未过期
            } else {
                // 月卡用户已经过期，续包
            }
        } else {
            // 按次洗
        }


        Map<String, Object> costInfo = new HashMap<>();

        // 包月用户
        if (careWasherusermonthcard != null) {
            if (careWasherusermonthcard.getEndDate().isAfter(LocalDateTime.now())) {
                // 不过期
            } else {
                // 续包
            }
        } else {
            // 查找设备是否有包月套餐，有则显示
        }
        return successModel();
    }

    /**
     * 提交洗衣订单
     *
     * @return
     */
    public ResultModel createWasherOrder() {
        CareWasherusermonthcard careWasherusermonthcard = new CareWasherusermonthcard();
        // 月卡未过期
        if (careWasherusermonthcard != null && careWasherusermonthcard.getEndDate().isAfter(LocalDateTime.now())) {
            CareWasherlastprepayinfo careWasherlastprepayinfo = new CareWasherlastprepayinfo();
            if (careWasherlastprepayinfo != null) {
                // 请点击结束洗衣后再试
                return errorModel();
            }
        }
        return successModel();
    }
}
