package com.izhuixin.rsps.service;

import com.izhuixin.rsps.domain.manual.BoxStatusReportInfo;
import org.joda.time.DateTime;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoxStatusReportService {

    List<BoxStatusReportInfo> getLastWeekTransientStatusData(String entCode);

    List<BoxStatusReportInfo> getLastMonthStatusData(String entCode);

    List<BoxStatusReportInfo> getLastWeekData(String entCode);

    boolean saveReport(BoxStatusReportInfo boxStatusDailyInfo, String entCode);

    boolean checkRepeat(Byte type, String generateTime, String entCode);

    BoxStatusReportInfo getCurrentDayData(String entCode);

    List<String[]> queryShipment(String entCode, DateTime curDate);

    ResponseEntity<byte[]> exportCSV(String entCode, String title);
}
