package com.gag.enterprisewxmobile.system.mseg.dao;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemTime;
import com.gag.enterprisewxmobile.system.mseg.entity.Mseg;
import org.apache.ibatis.annotations.Mapper;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * (Mseg)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-19 09:36:30
 */
@Mapper
public interface MsegDao {

    LinkedList<Mseg> msegSystem(KaSystemTime kaSystemTime);

}