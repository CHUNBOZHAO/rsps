package com.izhuixin.rsps.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.ManagerUserDetail;
import com.izhuixin.rsps.common.dao.BoxStatusReportInfo;
import com.izhuixin.rsps.common.vo.web.DataEfficiency;
import com.izhuixin.rsps.common.vo.web.EfficiencyVO;
import com.izhuixin.rsps.common.vo.web.EntRankingVO;
import com.izhuixin.rsps.common.vo.web.EnterpriseAndUserInfo;
import com.izhuixin.rsps.service.DataService;
import com.izhuixin.rsps.service.EnterpriseAndUserService;
import org.HdrHistogram.DoubleLinearIterator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/operation")
public class ManagerOperationController {

    @Autowired
    private DataService dataService;

    @Autowired
    private EnterpriseAndUserService enterpriseAndUserService;

    @RequestMapping("/show")
    public String showOperation(ModelMap map,HttpServletRequest request){

        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        map.put("userType",userDetail.getEntId());
        System.out.println(userDetail.getEntId());

        String defaultEndDate = new DateTime().toString("yyyy-MM-dd");
        String defalutBeginDate = new DateTime().minusDays(7).toString("yyyy-MM-dd");

        //效率
        EfficiencyVO efficiencyVO = dataService.getEfficiencyVO(userDetail.getEntId());


        map.put("defaultBeginDate",defalutBeginDate);
        map.put("defaultEndDate",defaultEndDate);
        map.put("idle_rate",efficiencyVO.getIdleRate());
        map.put("idle_rate_compareCurrent",efficiencyVO.getIdleCompareYear());
        map.put("idle_rate_compareLast",efficiencyVO.getIdleCompareMonth());
        map.put("idle_count",efficiencyVO.getIdleCount());

        map.put("turnover_rate",efficiencyVO.getTurnRate());
        map.put("turnover_rate_compareCurrent",efficiencyVO.getTurnCompareYear());
        map.put("turnover_rate_compareLast",efficiencyVO.getTurnCompareMonth());
        map.put("turnover_count",efficiencyVO.getIdleCount());

        map.put("lose_rate",efficiencyVO.getLoseRate());
        map.put("lose_rate_compareCurrent",efficiencyVO.getLoseCompareYear());
        map.put("lose_rate_compareLast",efficiencyVO.getLoseCompareMonth());
        map.put("lose_count",efficiencyVO.getLoseCount());

        map.put("overdue_rate",efficiencyVO.getOverdueRate());
        System.out.println(efficiencyVO.getOverdueRate()+"123");
        map.put("overdue_rate_compareCurrent",efficiencyVO.getOverdueCompareYear());
        map.put("overdue_rate_compareLast",efficiencyVO.getOverdueCompareMonth());
        map.put("overdue_count",efficiencyVO.getOverdueCount());

        //排序
        List<EntRankingVO> rankingVOS = dataService.getEntRanking(3,"10001","3");

        map.put("rankingVOS",rankingVOS);


        return "manager/operation";
    }
    /**
     * 获取最近30天的月闲置率，周转率，遗失率，和过期率
     * @return
     */
    @RequestMapping("/getMonthChartData")
    @ResponseBody
    public String getChartData(HttpServletRequest request){
        String chartData = null;
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        //柱状图数据
        String entId = request.getParameter("entId");
        System.out.println(entId);

        if(entId!=null){
            chartData = dataService.getChartData(1, entId, "3");
        }else {
            chartData = dataService.getChartData(1, userDetail.getEntId(), "3");
        }

        return chartData;
    }

}
