package com.gag.enterprisewxmobile.system.mseg.service.impl;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemTime;
import com.gag.enterprisewxmobile.system.mseg.dao.MsegDao;
import com.gag.enterprisewxmobile.system.mseg.entity.Mseg;
import com.gag.enterprisewxmobile.system.mseg.service.MsegService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * (Mseg)表服务实现类
 *
 * @author makejava
 * @since 2020-11-19 09:36:30
 */
@Service("msegService")
public class MsegServiceImpl implements MsegService {
    @Resource
    private MsegDao msegDao;


    public LinkedList<Mseg> msegSystem(KaSystemTime kaSystemTime){
        return this.msegDao.msegSystem(kaSystemTime);
    };
}