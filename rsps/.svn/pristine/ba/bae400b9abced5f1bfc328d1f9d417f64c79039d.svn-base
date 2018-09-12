package com.izhuixin.rsps.controller;

import com.google.common.collect.Lists;
import com.izhuixin.rsps.domain.manual.BoxStatusReportInfo;
import com.izhuixin.rsps.service.BoxStatusReportService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/boxData/report")
public class BoxStatusReportController {

    @Autowired
    private BoxStatusReportService boxStatusReportService;

    @RequestMapping("/boxStatus/transient/lastWeek/{entCode}")
    public List<BoxStatusReportInfo> getLastWeekTransientStatusData(@PathVariable String entCode) {
        return boxStatusReportService.getLastWeekTransientStatusData(entCode);
    }

    @RequestMapping("/boxStatus/lastMonth/{entCode}")
    public List<BoxStatusReportInfo> getLastMonthStatusData(@PathVariable String entCode) {
        return boxStatusReportService.getLastMonthStatusData(entCode);
    }

    @RequestMapping("/boxStatus/lastWeek/{entCode}")
    public List<BoxStatusReportInfo> getLastWeekData(@PathVariable String entCode) {
        return boxStatusReportService.getLastWeekData(entCode);
    }

    @RequestMapping("/save")
    public boolean saveReport(@RequestBody BoxStatusReportInfo boxStatusDailyInfo, String entCode) {
        return boxStatusReportService.saveReport(boxStatusDailyInfo, entCode);
    }

    @RequestMapping("/boxStatus/currentDay/{entCode}")
    public BoxStatusReportInfo getCurrentDayData(@PathVariable String entCode) {
        return boxStatusReportService.getCurrentDayData(entCode);
    }

    @RequestMapping("/shipment/query")
    public List<String[]> queryShipment(String entCode, String curDate) {
        DateTime dt = null;
        try {
            DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            dt = DateTime.parse(curDate, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dt == null) {
            return Lists.newArrayList();
        }
        return boxStatusReportService.queryShipment(entCode, dt);
    }

    @RequestMapping("/export")
    public ResponseEntity<byte[]> exportCSV(String entCode, String title) {
        return boxStatusReportService.exportCSV(entCode, title);
    }

}
