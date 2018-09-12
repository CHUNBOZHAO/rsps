package com.izhuixin.rsps.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.ManagerUserDetail;
import com.izhuixin.rsps.common.constant.BoxStatus;
import com.izhuixin.rsps.common.dao.BoxStatusReportInfo;
import com.izhuixin.rsps.service.BoxInfoService;
import com.izhuixin.rsps.service.BoxStatusReportService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("chart")
public class ManagerChartController {

    @Autowired
    private BoxInfoService boxInfoService;

    @Autowired
    private BoxStatusReportService boxStatusReportService;

    /**
     * 获取包装箱状态饼图数据
     * @return
     */
    @RequestMapping("/getBoxStatusPieData")
    @ResponseBody
    public String getBoxStatusPieData(HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        Long countTransporting = boxInfoService.countTransportingBoxes(userDetail.getEntCode().concat("_"));
        Long countBinding = boxInfoService.countBindingBoxes(userDetail.getEntCode().concat("_"));
        Long countRetention = boxInfoService.countRetentionBoxes(userDetail.getEntCode().concat("_"));
        Long countRecycle = boxInfoService.countRecycleBoxes(userDetail.getEntCode().concat("_"));
        Long countLeisure = boxInfoService.countLeisureBoxes(userDetail.getEntCode().concat("_"));

        JsonArray ja = new JsonArray();

        ja.add(countBinding);
        ja.add(countTransporting);
        ja.add(countRetention);
        ja.add(countRecycle);
        ja.add(countLeisure);

        return ja.toString();
    }

    /**
     * 获取包装箱状态折线图数据（状态日报表数据）
     * @return
     */
    @RequestMapping("/getBoxStatusLineData")
    @ResponseBody
    public String getBoxStatusLineData(HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<BoxStatusReportInfo> statusReportInfos = boxStatusReportService.getLastWeekTransientStatusData(userDetail.getEntCode().concat("_"));

        JsonArray xAxisDataJa = new JsonArray();
        JsonArray seriesDataJa = new JsonArray();

        JsonArray bindNumJa = new JsonArray(); // 绑定
        JsonArray transportingNumJa = new JsonArray(); // 运输
        JsonArray retentionNumJa = new JsonArray();  // 滞留
        JsonArray recycleNumJa = new JsonArray();  // 回收
        JsonArray leisureNumJa = new JsonArray();  // 闲置
        for (BoxStatusReportInfo boxStatusReportInfo : statusReportInfos) {
            xAxisDataJa.add(new DateTime(boxStatusReportInfo.getGenerateDate()).toString("dd日HH时"));

            bindNumJa.add(boxStatusReportInfo.getBindingNum());
            transportingNumJa.add(boxStatusReportInfo.getTransportingNum());
            retentionNumJa.add(boxStatusReportInfo.getRetentionNum());
            recycleNumJa.add(boxStatusReportInfo.getRecycleNum());
            leisureNumJa.add(boxStatusReportInfo.getLeisureNum());
        }

        // 配货
        JsonObject seriesBindingNumDataJo = new JsonObject();
        seriesBindingNumDataJo.addProperty("name", BoxStatus.BINDING.getDescr());
        seriesBindingNumDataJo.addProperty("type", "line");
        seriesBindingNumDataJo.addProperty("smooth", "true");
        seriesBindingNumDataJo.add("data", bindNumJa);

        // 运输中
        JsonObject seriesTransportingNumDataJo = new JsonObject();
        seriesTransportingNumDataJo.addProperty("name", BoxStatus.TRANSPORTING.getDescr());
        seriesTransportingNumDataJo.addProperty("type", "line");
        seriesTransportingNumDataJo.addProperty("smooth", "true");
        seriesTransportingNumDataJo.add("data", transportingNumJa);

        // 滞留
        JsonObject seriesRetentionNumDataJo = new JsonObject();
        seriesRetentionNumDataJo.addProperty("name", BoxStatus.RETENTION.getDescr());
        seriesRetentionNumDataJo.addProperty("type", "line");
        seriesRetentionNumDataJo.addProperty("smooth", "true");
        seriesRetentionNumDataJo.add("data", retentionNumJa);

        // 回收
        JsonObject seriesRecycleNumDataJo = new JsonObject();
        seriesRecycleNumDataJo.addProperty("name", BoxStatus.RECYCLE.getDescr());
        seriesRecycleNumDataJo.addProperty("type", "line");
        seriesRecycleNumDataJo.addProperty("smooth", "true");
        seriesRecycleNumDataJo.add("data", recycleNumJa);

        seriesDataJa.add(seriesBindingNumDataJo);
        seriesDataJa.add(seriesTransportingNumDataJo);
        seriesDataJa.add(seriesRetentionNumDataJo);
        seriesDataJa.add(seriesRecycleNumDataJo);

        JsonObject jo = new JsonObject();
        jo.add("xAxisData", xAxisDataJa);
        jo.add("seriesData", seriesDataJa);

        return jo.toString();
    }

    /**
     * 获取包装箱状态数据（状态日报表数据）
     * @return
     */
    @RequestMapping("/box/bind/month")
    @ResponseBody
    public String getBoxBindMonthly(HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<BoxStatusReportInfo> dailyInfos = boxStatusReportService.getLastMonthStatusData(userDetail.getEntCode().concat("_"));
        BoxStatusReportInfo curDayInfo = boxStatusReportService.getCurrentDayData(userDetail.getEntCode().concat("_"));
        dailyInfos.add(curDayInfo);

        JsonArray xAxisDataJa = new JsonArray();
        JsonArray seriesDataJa = new JsonArray();

        JsonArray bindNumJa = new JsonArray();

        for (BoxStatusReportInfo boxStatusReportInfo : dailyInfos) {
            if (boxStatusReportInfo == null || boxStatusReportInfo.getGenerateDate() == null) {
                continue;
            }
            xAxisDataJa.add(new DateTime(boxStatusReportInfo.getGenerateDate()).toString("MM月dd日"));
            bindNumJa.add(boxStatusReportInfo.getBindingNum());
        }

        // 平均值对象
        JsonObject averageDataJo = new JsonObject();
        JsonArray averageJaItem = new JsonArray();
        JsonObject averageJoItem = new JsonObject();
        averageJoItem.addProperty("type", "average");
        averageJoItem.addProperty("name", "平均值");
        averageJaItem.add(averageJoItem);
        averageDataJo.add("data", averageJaItem);

        // 配货
        JsonObject seriesBindingNumDataJo = new JsonObject();
        seriesBindingNumDataJo.addProperty("name", "出货量");
        seriesBindingNumDataJo.addProperty("type", "bar");
        seriesBindingNumDataJo.add("data", bindNumJa);
        seriesBindingNumDataJo.add("markLine", averageDataJo);

        seriesDataJa.add(seriesBindingNumDataJo);

        JsonObject jo = new JsonObject();
        jo.add("xAxisData", xAxisDataJa);
        jo.add("seriesData", seriesDataJa);

        return jo.toString();
    }


}
