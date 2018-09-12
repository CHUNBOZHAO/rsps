package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.domain.DataEfficiency;
import com.izhuixin.rsps.domain.EfficiencyVO;
import com.izhuixin.rsps.domain.EntRankingVO;
import com.izhuixin.rsps.service.impl.DataEfficiencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataEfficiencyService dataEfficiencyService;


    //  获取总体包装箱当月30天数据
    @RequestMapping("/getEfficiency")
    public List<DataEfficiency> getEfficiency(String entId,Integer type,String beginTime,String endTime){


        return dataEfficiencyService.getEfficiency(entId,type,beginTime,endTime);
    }

    // 获取最近30天的闲置率，周转率，遗失率，和过期率....
    @RequestMapping("/getEfficiencyMonth")
    public EfficiencyVO getEfficiencyVO(String entId){

        return dataEfficiencyService.getEfficiencyVO(entId);
    }

    //获取企业闲置率的排名
    @RequestMapping("/getEntRanking")
    public List<EntRankingVO> getEntRanking(Integer effiType,String entId,String type){

        return dataEfficiencyService.getEntRanking(effiType,entId,type);
    }


    //获取柱状图数据
    @RequestMapping("/getChartData")
    public String getChartData(Integer efficType,String entId,String type){

        return dataEfficiencyService.getChartData(efficType,entId,type);
    }

    //根据时间获取柱状图信息
    @RequestMapping("/getChartDataByTime")
    public String getChartDataByTime(Integer efficType,String entId,String type,String beginTime,String endTime){

        return dataEfficiencyService.getChartDataByTime(efficType,entId,type,beginTime,endTime);
    }

}
