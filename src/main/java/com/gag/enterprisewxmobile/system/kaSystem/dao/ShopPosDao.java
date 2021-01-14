package com.gag.enterprisewxmobile.system.kaSystem.dao;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemTime;
import com.gag.enterprisewxmobile.system.kaSystem.entity.ShopPos;
import com.gag.enterprisewxmobile.tool.gen.entity.ColumnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 商品销售(ShopPos)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-02 15:59:49
 */
@Mapper
public interface ShopPosDao {

    LinkedList<LinkedHashMap> kaSystem(KaSystemTime kaSystemTime);

    List<ColumnInfo> selectTableColumnsByName();

    List<LinkedHashMap> selectmseg(ShopPos shopPos);

}