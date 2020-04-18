package com.seawaterbt.ssm.core.common.factory;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seawaterbt.ssm.core.common.support.HttpKit;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author fengshuonan
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        String pageSizeStr = request.getParameter("pageSize");
        String pageNumStr = request.getParameter("pageNo");
        return this.handlePage(pageSizeStr, pageNumStr, request);
    }

    public Page<T> defaultPage(Map<String, Object> queryMap) {
        HttpServletRequest request = HttpKit.getRequest();
        if (queryMap.get("pageSize") == null || queryMap.get("pageNo") == null) return new Page<T>();
        String pageSizeStr = queryMap.get("pageSize").toString();
        String pageNumStr = queryMap.get("pageNo").toString();
        return this.handlePage(pageSizeStr, pageNumStr, request);
    }

    private Page<T> handlePage(String pageSizeStr, String pageNumStr, HttpServletRequest request) {
        if (StringUtils.isEmpty(pageSizeStr) || StringUtils.isEmpty(pageNumStr)) return new Page<T>();
        int pageSize = Integer.parseInt(pageSizeStr);
        int pageNum = Integer.parseInt(pageNumStr);
        Page<T> page = new Page<T>(pageNum, pageSize);
        page.setOptimizeCountSql(true);
        return page;
    }
}
