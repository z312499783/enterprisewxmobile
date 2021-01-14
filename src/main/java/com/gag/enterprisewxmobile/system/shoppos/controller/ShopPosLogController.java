package com.gag.enterprisewxmobile.system.shoppos.controller;

import com.gag.enterprisewxmobile.system.shoppos.entity.ShopPosLog;
import com.gag.enterprisewxmobile.system.shoppos.service.ShopPosLogService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import static com.gag.enterprisewxmobile.tool.common.JSONResult.success;

/**
 * 日志(ShopPosLog)表控制层
 *
 * @author makejava
 * @since 2020-10-15 10:01:55
 */
@Api(value = "操作日志")
@RestController
@RequestMapping("shopPosLog")
public class ShopPosLogController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private ShopPosLogService shopPosLogService;


    /**
     * 通过实体作为筛选条件查询
     *
     * @param shopPosLog 实例对象
     * @return 对象列表
     */
    @GetMapping("select")
    @RequiresPermissions("shopPosLog:select")
    @ApiOperation(value = "查询操作日志",notes = "根据日志类对象查询日志",httpMethod = "GET",response = JSONResult.class)
    public JSONResult queryAll(ShopPosLog shopPosLog){
        startPage();
        List<ShopPosLog> list = this.shopPosLogService.queryAll(shopPosLog);
        return success(list);
    };

}