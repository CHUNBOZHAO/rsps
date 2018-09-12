package com.izhuixin.rsps.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.ManagerUserDetail;
import com.izhuixin.rsps.common.vo.web.EfficiencyVO;
import com.izhuixin.rsps.common.vo.web.EntRankingVO;
import com.izhuixin.rsps.service.DataService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("statistics")
public class ManagerStatisticsController {

    @Autowired
    private DataService dataService;

    /**
     * 统计页面首页
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        return "manager/statistics/index";
    }

    /**
     * 显示统计参数显示卡片
     * @param request
     * @return
     */
    @RequestMapping("/parameter/card/show")
    public String showParameterCard(HttpServletRequest request,ModelMap map) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        EfficiencyVO efficiencyVO = dataService.getEfficiencyVO(userDetail.getEntId());

        map.put("efficiencyVO",efficiencyVO);

        return "manager/statistics/parameter_card";
    }

    /**
     * 显示个参数图表等信息卡片
     * @param request
     * @return
     */
    @RequestMapping("/tab/card/show")
    public String showTabCard(HttpServletRequest request) {
        return "manager/statistics/tab_card";
    }

    /**
     * 显示图表数据
     * @param request
     * @return
     */
    @RequestMapping("/chart/data/get")
    @ResponseBody
    public String showChartArea(HttpServletRequest request) {

        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        String entId = request.getParameter("entId");
        if(entId.equals("undefined")){
            entId = "0";
        }
        String tabType = request.getParameter("tabType");
        String dateType = request.getParameter("dateType");


        String chartData = dataService.getChartData(Integer.parseInt(tabType), entId, String.valueOf(dateType));

        JsonArray xAxisDataJa = new JsonArray();
        JsonArray seriesDataJa = new JsonArray();

        JsonArray rateDataJa = new JsonArray();

        // 配货
        JsonObject seriesDataJo = new JsonObject();
        seriesDataJo.addProperty("name", "");
        seriesDataJo.addProperty("type", "bar");
        seriesDataJo.add("data", rateDataJa);

        seriesDataJa.add(seriesDataJo);

        JsonObject jo = new JsonObject();
        jo.add("xAxisData", xAxisDataJa);
        jo.add("seriesData", seriesDataJa);

        return chartData;
    }

    /**
     * 显示企业排名区域
     * @param modelMap
     * @return
     */
    @RequestMapping("/ranking/data/show")
    public String showRankingArea(ModelMap modelMap, HttpServletRequest request) {

        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        String entId = request.getParameter("entId");
        if(entId.equals("undefined")){
            entId = "0";
        }
        String tabType = request.getParameter("tabType");
        String dateType = request.getParameter("dateType");

        // todo 获取排名的数据
        System.out.println("*****************************"+userDetail.getEntId());
        List<EntRankingVO> rankingVOS = dataService.getEntRanking(Integer.parseInt(tabType), userDetail.getEntId(), dateType);

        //获取总体的效率
        EfficiencyVO efficiencyVO = dataService.getEfficiencyVO("0");
        if("1".equals(tabType)){
            modelMap.put("rateAll",efficiencyVO.getIdleRate());
        }else if("2".equals(tabType)){
            modelMap.put("rateAll",efficiencyVO.getTurnRate());
        }else if("3".equals(tabType)){
            modelMap.put("rateAll",efficiencyVO.getLoseRate());
        }else if("4".equals(tabType)){
            modelMap.put("rateAll",efficiencyVO.getOverdueCount());
        }



        System.out.println(rankingVOS.get(0).getEntId());
        modelMap.put("rankingVOS",rankingVOS);

        return "manager/statistics/ranking_area";
    }

}
