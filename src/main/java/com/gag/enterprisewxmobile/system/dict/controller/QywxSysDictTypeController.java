package com.gag.enterprisewxmobile.system.dict.controller;

import com.gag.enterprisewxmobile.system.dict.entity.QywxSysDictType;
import com.gag.enterprisewxmobile.system.dict.service.QywxSysDictTypeService;
import com.gag.enterprisewxmobile.tool.common.BaseController;
import com.gag.enterprisewxmobile.tool.common.BusinessType;
import com.gag.enterprisewxmobile.tool.common.JSONResult;
import com.gag.enterprisewxmobile.tool.common.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典类型表(QywxSysDictType)表控制层
 *
 * @author makejava
 * @since 2021-01-11 17:06:10
 */
@Api(value = "字典数据表")
@Controller
@RequestMapping("qywxSysDictType")
public class QywxSysDictTypeController extends BaseController {

    /**
     * 服务对象
     */
    @Resource
    private QywxSysDictTypeService qywxSysDictTypeService;

    @PostMapping("select")
    @RequiresPermissions("dict:select")
    @ResponseBody
    @ApiOperation(value = "字典数据表",notes = "查询字典数据表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult list(QywxSysDictType dictType)
    {
        startPage();
        List<QywxSysDictType> list = qywxSysDictTypeService.selectDictTypeList(dictType);
        return JSONResult.tablePageLayui(list);
    }

    /**
     * 新增保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("dict:insert")
    @PostMapping("insert")
    @ResponseBody
    @ApiOperation(value = "字典数据表",notes = "新增字典数据表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult insert(QywxSysDictType dict)
    {
        return JSONResult.customint(qywxSysDictTypeService.insertDictType(dict));
    }

    /**
     * 修改保存字典类型
     */
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @RequiresPermissions("dict:update")
    @PostMapping("update")
    @ResponseBody
    @ApiOperation(value = "字典数据表",notes = "修改字典数据表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult update(QywxSysDictType dict) {
        return JSONResult.customint(qywxSysDictTypeService.updateDictType(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @RequiresPermissions("dict:delete")
    @PostMapping("delete")
    @ResponseBody
    @ApiOperation(value = "字典数据表",notes = "删除字典数据表",response = JSONResult.class,httpMethod = "POST")
    public JSONResult delete(String ids) {
        try
        {
            return JSONResult.customint(qywxSysDictTypeService.deleteDictTypeByIds(ids));
        }
        catch (Exception e)
        {
            return JSONResult.failMsg(e.getMessage());
        }
    }

    /**
     * 查询字典详细
     */
    @RequiresPermissions("dict:select")
    @GetMapping("/select/{dictId}")
    @ResponseBody
    @ApiOperation(value = "字典数据表",notes = "查询字典详细",response = JSONResult.class,httpMethod = "GET")
    public JSONResult detail(@PathVariable("dictId") int dictId) {
        ModelMap mmap = new ModelMap();
        mmap.put("dict",qywxSysDictTypeService.selectDictTypeById(dictId));
        mmap.put("dictList",qywxSysDictTypeService.selectDictTypeAll());
        return JSONResult.custom(mmap);
    }

    /**
     * 校验字典类型
     */
    @PostMapping("/checkDictTypeUnique")
    @ResponseBody
    @ApiOperation(value = "字典数据表",notes = "校验字典类型",response = JSONResult.class,httpMethod = "POST")
    public JSONResult checkDictTypeUnique(QywxSysDictType dictType) {
        return JSONResult.customboo(qywxSysDictTypeService.checkDictTypeUnique(dictType));
    }


}