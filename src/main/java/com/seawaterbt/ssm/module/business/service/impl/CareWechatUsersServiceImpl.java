package com.seawaterbt.ssm.module.business.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.factory.PageFactory;
import com.seawaterbt.ssm.module.business.entity.CareInstall;
import com.seawaterbt.ssm.module.business.entity.CareWechatUsers;
import com.seawaterbt.ssm.module.business.dao.CareWechatUsersMapper;
import com.seawaterbt.ssm.module.business.service.ICareWechatUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author azx
 * @since 2020-03-08
 */
@Service
public class CareWechatUsersServiceImpl extends ServiceImpl<CareWechatUsersMapper, CareWechatUsers> implements ICareWechatUsersService {

    @Resource
    private CareWechatUsersMapper careWechatUsersMapper;

    @Override
    public Page<Map<String,Object>> getWechatUserPage(Map<String, Object> params) {
        Page<Map<String,Object>> page = new PageFactory<Map<String,Object>>().defaultPage(params);
        List<Map<String,Object>> list = careWechatUsersMapper.selectWechatUserList(page, params);
        page.setRecords(list);
        return page;
    }

    @Override
    public List<Map<String, Object>> getWechatUserList(Map<String, Object> params) {
        return careWechatUsersMapper.selectWechatUserList(null, params);
    }
}
