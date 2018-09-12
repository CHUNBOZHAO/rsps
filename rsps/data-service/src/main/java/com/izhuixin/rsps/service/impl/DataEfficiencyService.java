package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.domain.BoxStatusInfo;
import com.izhuixin.rsps.domain.DataEfficiency;
import com.izhuixin.rsps.domain.EfficiencyVO;
import com.izhuixin.rsps.domain.EntRankingVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataEfficiencyService {

    //获取总体包装箱当月30天数据
    List<DataEfficiency> getEfficiency(@Param("entId") String entId, @Param("type") Integer type, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
    // 获取最近30天的闲置率，周转率，遗失率，和过期率....
    EfficiencyVO getEfficiencyVO(String entId);
    // 获取企业排名接口
    List<EntRankingVO> getEntRanking(Integer effiType,String entId,String type);
    //获取柱状图数据
    String getChartData(Integer effiType,String entId,String type);


    //根据时间获取柱状图信息
    String getChartDataByTime(Integer effiType,String entId,String type,String beginTime,String endTime);
}
