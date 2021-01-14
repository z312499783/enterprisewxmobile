package com.gag.enterprisewxmobile.tool.common;

import com.gag.enterprisewxmobile.framework.page.PageDomain;
import com.gag.enterprisewxmobile.framework.page.TableSupport;
import com.gag.enterprisewxmobile.system.user.entity.QywxSysUser;
import com.gag.enterprisewxmobile.tool.common.utils.StringUtils;
import com.github.pagehelper.PageHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController {

    private int code = 0;
    private String msg = "查询成功";
    private int count;
    private List data;

    public Map returnLayuiMap(List<?> list){
        Map map = new HashMap();
        map.put("code",this.code);
        map.put("msg",this.msg);
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = pageDomain.getOrderBy();
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    public QywxSysUser getSysUser()
    {
        return ShiroUtils.getSysUser();
    }


}
