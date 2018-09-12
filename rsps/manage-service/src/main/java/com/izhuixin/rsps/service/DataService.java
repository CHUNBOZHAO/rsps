package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.vo.web.DataEfficiency;
import com.izhuixin.rsps.common.vo.web.EfficiencyVO;
import com.izhuixin.rsps.common.vo.web.EntRankingVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("data-service")
@RequestMapping("/data")
public interface DataService {

    //  获取总体包装箱当月30天数据
    @RequestMapping("/getEfficiency")
    List<DataEfficiency> getEfficiency(@RequestParam("entId") String entId, @RequestParam("type") Integer type, @RequestParam("beginTime") String beginTime, @RequestParam("endTime") String endTime);

    // 获取最近30天的闲置率，周转率，遗失率，和过期率....
    @RequestMapping("/getEfficiencyMonth")
    EfficiencyVO getEfficiencyVO(@RequestParam("entId") String entId);

    //获取企业闲置率的排名
    @RequestMapping("/getEntRanking")
    List<EntRankingVO> getEntRanking(@RequestParam("effiType") Integer effiType,@RequestParam("entId") String entId,@RequestParam("type") String type);

    //获取柱状图数据
    @RequestMapping("/getChartData")
    String getChartData(@RequestParam("efficType") Integer efficType, @RequestParam("entId") String entId, @RequestParam("type") String type);
}