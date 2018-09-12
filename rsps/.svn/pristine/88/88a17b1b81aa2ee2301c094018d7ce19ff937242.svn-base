package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dao.BoxStatusReportInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "api-service")
@RequestMapping("v1/boxData/report")
public interface BoxStatusReportService {

    @RequestMapping("/boxStatus/transient/lastWeek/{entCode}")
    List<BoxStatusReportInfo> getLastWeekTransientStatusData(@PathVariable("entCode") String entCode);

    @RequestMapping("/boxStatus/lastMonth/{entCode}")
    List<BoxStatusReportInfo> getLastMonthStatusData(@PathVariable("entCode") String entCode);

    @RequestMapping("/boxStatus/lastWeek/{entCode}")
    List<BoxStatusReportInfo> getLastWeekData(@PathVariable("entCode") String entCode);

    @RequestMapping("/save")
    boolean saveReport(@RequestBody BoxStatusReportInfo boxStatusDailyInfo,
                       @RequestParam("entCode") String entCode);

    @RequestMapping("/boxStatus/currentDay/{entCode}")
    BoxStatusReportInfo getCurrentDayData(@PathVariable("entCode") String entCode);

    @GetMapping("/shipment/query")
    List<String[]> queryShipment(@RequestParam("entCode") String entCode,
                                 @RequestParam("curDate") String curDate);

    @RequestMapping("/export")
    ResponseEntity<byte[]> exportCSV(@RequestParam("entCode") String entCode,
                                     @RequestParam("title") String title);

}
