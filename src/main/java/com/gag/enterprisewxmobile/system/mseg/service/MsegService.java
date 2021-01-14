package com.gag.enterprisewxmobile.system.mseg.service;

import com.gag.enterprisewxmobile.system.kaSystem.entity.KaSystemTime;
import com.gag.enterprisewxmobile.system.mseg.entity.Mseg;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * (Mseg)表服务接口
 *
 * @author makejava
 * @since 2020-11-19 09:36:30
 */
public interface MsegService {


    LinkedList<Mseg> msegSystem(KaSystemTime kaSystemTime);

}