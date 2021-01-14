package com.gag.enterprisewxmobile.tool.gen.service.impl;

import com.gag.enterprisewxmobile.tool.gen.dao.GenDao;
import com.gag.enterprisewxmobile.tool.gen.entity.ColumnInfo;
import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemTime;
import com.gag.enterprisewxmobile.tool.gen.entity.TableInfo;
import com.gag.enterprisewxmobile.tool.gen.service.GenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("genService")
public class GenServiceImpl implements GenService {

    @Resource
    private GenDao genDao;


    @Override
    public LinkedList<LinkedHashMap> selectmseg(String tableName){
        return this.genDao.selectmseg(tableName);
    };

    @Override
    public int insertmsg(Map map){
        return this.genDao.insertmsg(map);
    };

    /**
     * 根据表的名称查询表的注释
     * @param tableName 表名称
     * @return
     */
    @Override
    public TableInfo selectTableByName(String tableName) {
        return this.genDao.selectTableByName(tableName);
    }

    @Override
    public TableInfo selectTableBySelectName(String tableName){return this.genDao.selectTableBySelectName(tableName);};

    /**
     * 根据表的名称查询表的字段和类型
     * @param tableName 表名称
     * @return
     */
    @Override
    public List<ColumnInfo> selectTableColumnsByName(String tableName) {
        return this.genDao.selectTableColumnsByName(tableName);
    }

    @Override
    public List<ColumnInfo> selectTableCoulumnsBySelectName(String tableName){
        return this.genDao.selectTableCoulumnsBySelectName(tableName);
    };

    /**
     * 根据表名称查询表名，拥有着，数据行数，是否拥有主键(1是拥有,0是没有)，表描述，表的主键名称，表的主键类型
     * @param tableName
     * @return
     */
    @Override
    public Map selectTablestructure(String tableName){
        return this.genDao.selectTablestructure(tableName);
    }

}