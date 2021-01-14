package com.gag.enterprisewxmobile.system.dict.controller;

import com.gag.enterprisewxmobile.system.dict.entity.QywxSysDictData;
import com.gag.enterprisewxmobile.system.dict.service.QywxSysDictDataService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.BusinessType;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.gag.enterprisewxmobile.tool.common.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典数据表(QywxSysDictData)表控制层
 *
 * @author makejava
 * @since 2021-01-11 17:05:45
 */
@Api(value = "字典数据表")
@Controller
@Component("qywxSysDictData")
@RequestMapping("dict/data")
public class QywxSysDictDataController extends BaseController {
    /**
     * 服务对象
     */
    @Resource
    private QywxSysDictDataService qywxSysDictDataService;


    @PostMapping("select")
    @RequiresPermissions("dict:select")
    @ResponseBody
    @ApiOperation(value = "字典数据表",notes = "查询字典数据表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult list(QywxSysDictData dictData)
    {
        startPage();
        List<QywxSysDictData> list = qywxSysDictDataService.selectDictDataList(dictData);
        return JSONResult.tablePageLayui(list);
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @RequiresPermissions("dict:insert")
    @PostMapping("insert")
    @ResponseBody
    @ApiOperation(value = "字典数据表",notes = "新增字典数据表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult add(QywxSysDictData dict)
    {
        return JSONResult.customint(qywxSysDictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("dict:udpate")
    @PostMapping("udpate")
    @ResponseBody
    @ApiOperation(value = "字典数据表",notes = "修改字典数据表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult editSave(QywxSysDictData dict)
    {
        return JSONResult.customint(qywxSysDictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     * @return
     */
    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    @RequiresPermissions("dict:delete")
    @PostMapping("delete")
    @ResponseBody
    @ApiOperation(value = "字典数据表",notes = "删除字典数据表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult delete(String ids)
    {
        return JSONResult.customint(qywxSysDictDataService.deleteDictDataByIds(ids));
    }


}