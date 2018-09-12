package com.izhuixin.rsps.service.impl;

import com.google.common.collect.Lists;
import com.izhuixin.rsps.common.dba.BaseAbstractService;
import com.izhuixin.rsps.dao.manual.BoxRecordDao;
import com.izhuixin.rsps.dao.manual.BoxStatusReportDao;
import com.izhuixin.rsps.domain.manual.BoxStatusReportInfo;
import com.izhuixin.rsps.domain.manual.ShipmentReportInfo;
import com.izhuixin.rsps.service.BoxStatusReportService;
import com.izhuixin.rsps.service.ExtendFileService;
import com.opencsv.CSVWriter;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.List;

@Service
public class BoxStatusReportServiceImpl extends BaseAbstractService implements BoxStatusReportService {

    @Autowired
    private BoxStatusReportDao boxStatusReportDao;

    @Autowired
    private BoxRecordDao boxRecordDao;

    @Autowired
    private ExtendFileService extendFileService;


    /**
     * 获取最近一周包装箱状态数据
     * @return
     */
    @Override
    public List<BoxStatusReportInfo> getLastWeekTransientStatusData(String entCode) {
        DateTime now = new DateTime();
        DateTime old = now.minusDays(7);

        List<BoxStatusReportInfo> hourlyDatas = null;
        try {
            hourlyDatas = boxStatusReportDao.getTransientHourlyInfos(entCode, old.toString("yyyy-MM-dd HH:mm:ss"), now.toString("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            logger.error("获取包装箱最近一个月的日报表数据出现异常", e);
        }

        if (hourlyDatas == null) {
            hourlyDatas = Lists.newArrayList();
        }
        return hourlyDatas;
    }

    /**
     * 获取最近一月包装箱状态数据
     * @return
     */
    @Override
    public List<BoxStatusReportInfo> getLastMonthStatusData(String entCode) {
        DateTime now = new DateTime();
        DateTime old = now.minusDays(30);

        List<BoxStatusReportInfo> dailyDatas = null;
        try {
            dailyDatas = boxStatusReportDao.getDailyInfos(entCode, old.toString("yyyy-MM-dd HH:mm:ss"), now.toString("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            logger.error("获取包装箱最近一个月的日报表数据出现异常", e);
        }

        if (dailyDatas == null) {
            dailyDatas = Lists.newArrayList();
        }
        return dailyDatas;
    }

    /**
     * 获取最近一周的状态流转数据
     * @param entCode
     * @return
     */
    public List<BoxStatusReportInfo> getLastWeekData(String entCode) {
        DateTime now = new DateTime();
        DateTime old = now.minusDays(7);

        List<BoxStatusReportInfo> reportInfos = null;
        try {
//            dailyDOList = boxStatusDailyDao.getDailyInfos(entCode, old.toString("yyyy-MM-dd HH:mm:ss"), now.toString("yyyy-MM-dd HH:mm:ss"));
            reportInfos = boxStatusReportDao.getHourlyInfos(entCode, old.toString("yyyy-MM-dd HH:mm:ss"), now.toString("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            logger.error("获取包装箱最近一个周的日报表数据出现异常", e);
        }

        if (reportInfos == null) {
            reportInfos = Lists.newArrayList();
        }
        return reportInfos;
    }

    /**
     * 保存日报
     * @param boxStatusDailyInfo
     * @param entCode
     * @return
     */
    @Override
    public boolean saveReport(BoxStatusReportInfo boxStatusDailyInfo, String entCode) {
        boolean res = false;
        try {
            boxStatusReportDao.saveReport(boxStatusDailyInfo, entCode);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("保存企业(%s)包装箱报表信息出现异常", entCode), e);
        }
        return res;
    }

    /**
     * 检测重复
     * @param type
     * @param generateTime
     * @return
     */
    @Override
    public boolean checkRepeat(Byte type, String generateTime, String entCode) {
        boolean res = false;
        try {
            Long resCount = boxStatusReportDao.checkRepeat(type, generateTime, entCode);
            if (resCount > 0) {
                res = true;
            }
        } catch (Exception e) {
            logger.error(String.format("检测企业(%s)包装箱报信息(%d,%s)是否重复出现异常", entCode, type, entCode), e);
        }
        return res;
    }

    /**
     * 获取企业当天操作信息
     * @param entCode
     * @return
     */
    @Override
    public BoxStatusReportInfo getCurrentDayData(String entCode) {
        DateTime endTime = DateTime.now();
        DateTime beginTime = DateTime.now().withTimeAtStartOfDay();
        BoxStatusReportInfo boxStatusReportInfo = boxStatusReportDao.getTotalReport(entCode, beginTime.toString("yyyy-MM-dd HH:mm:ss"), endTime.toString("yyyy-MM-dd HH:mm:ss"));
        if (boxStatusReportInfo != null) {
            boxStatusReportInfo.setGenerateDate(endTime.toDate());
        }
        return boxStatusReportInfo;
    }

    /**
     * 获取特定时间
     * @param entCode
     * @param curDate
     * @return
     */
    @Override
    public List<String[]> queryShipment(String entCode, DateTime curDate) {
        String beginTime = curDate.toString("yyyy-MM-dd 00:00:00");
        String endTime = curDate.toString("yyyy-MM-dd 23:59:59");
        List<ShipmentReportInfo> reportInfos = boxRecordDao.getShipmentReport(entCode, beginTime, endTime);

        List<String[]> objectList = Lists.newArrayList();
        String[] objects = null;

        for (ShipmentReportInfo reportInfo : reportInfos) {
            objects = new String[5];
            objects[0] = reportInfo.getBoxId();  // 包装箱ID
            objects[1] = reportInfo.getOrderId();  // 订单号
            objects[2] = reportInfo.getCustomer();  // 客户名称
            objects[3] = reportInfo.getOperator(); // 操作人
            objects[4] = new DateTime(reportInfo.getCreateTime()).toString("yyyy-MM-dd HH:mm"); // 操作时间
            objectList.add(objects);
        }
        return objectList;
    }


    /**
     * 导出出货量日报表
     * @param entCode
     * @return
     */
    @Override
    public ResponseEntity<byte[]> exportCSV(String entCode, String title) {

        // 数据写入csv文件
        String fileName = generateCSVFileName(title);
        String filePath = extendFileService.getReportLocalPath(entCode, title);
        CSVWriter csvWriter = null;
        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath), Charset.forName("utf-8"));
            csvWriter = new CSVWriter(out, ',');
            List<String[]> dataList = generateCSVData(entCode, title);
            csvWriter.writeAll(dataList);
            csvWriter.close();
        } catch (IOException e) {
            logger.error(String.format("生成CSV数据文件出现异常", e));
        }


        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<byte[]> responseEntity = null;
        File file = null;
        try {
            file = new File(filePath);
            String downFileName = new String((fileName).getBytes("UTF-8"),"iso-8859-1");
            headers.setContentDispositionFormData("attachment", downFileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(String.format("CSV(s%)文件处理出现异常", filePath), e);
        }

        if (file != null) {
            file.delete();
        }

        return responseEntity;
    }

    /**
     * 生成CSV需要的数据对象
     * @param entCode
     * @return
     */
    private List<String[]> generateCSVData(String entCode, String title) {

        DateTime endTime = DateTime.now();
        DateTime beginTime = endTime.minusDays(7);
        List<ShipmentReportInfo> reportInfos = boxRecordDao.getShipmentReport(entCode, beginTime.toString("yyyy-MM-dd HH:mm:ss"), endTime.toString("yyyy-MM-dd HH:mm:ss"));

        List<String[]> objectList = Lists.newArrayList();
        String[] objects = null;

        objects = new String[9];
        objects[0] = generateCSVTitle(title);
        objects[1] = "";
        objects[2] = "";
        objects[3] = "";
        objects[4] = "";
        objects[5] = "";
        objects[6] = "";
        objects[7] = "";
        objects[8] = "";
        objectList.add(objects);

        objects = new String[9];
        objects[0] = "包装箱ID";
        objects[1] = "订单号";
        objects[2] = "客户名称";
        objects[3] = "操作人";
        objects[4] = "绑定时间";
        objects[5] = "绑定日期";
        objects[6] = "当前状态";
        objects[7] = "责任人";
        objects[8] = "最近操作时间";
        objectList.add(objects);

        for (ShipmentReportInfo reportInfo : reportInfos) {
            objects = new String[9];
            objects[0] = reportInfo.getBoxId();
            objects[1] = reportInfo.getOrderId();
            objects[2] = reportInfo.getCustomer();
            objects[3] = reportInfo.getOperator();
            objects[4] = new DateTime(reportInfo.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss");
            objects[5] = new DateTime(reportInfo.getCreateTime()).toString("yyyy-MM-dd");
            if (reportInfo.getBoxStatus() != null) {
                if (reportInfo.getBoxStatus().byteValue() == 1) {
                    objects[6] = "配货";
                } else if (reportInfo.getBoxStatus().byteValue() == 2 || reportInfo.getBoxStatus().byteValue() == 5) {
                    objects[6] = "运输";
                } else if (reportInfo.getBoxStatus().byteValue() == 3) {
                    objects[6] = "滞留";
                } else if (reportInfo.getBoxStatus().byteValue() == 4) {
                    objects[6] = "回收";
                }
            } else {
                objects[6] = "已回库";
            }
            objects[7] = reportInfo.getCurrentOperator();
            if (reportInfo.getCurrentOperateTime() != null) {
                objects[8] = new DateTime(reportInfo.getCurrentOperateTime()).toString("yyyy-MM-dd HH:mm:ss");
            } else {
                objects[8] = "";
            }
            objectList.add(objects);
        }
        return objectList;
    }

    /**
     * 生成CSV文件名称
     * @param title
     * @return
     */
    private String generateCSVFileName(String title) {
        StringBuffer sb = new StringBuffer("智能包装箱");
        sb.append("【");
        sb.append(title);
        sb.append("】");
        sb.append("信息报表");
        sb.append(".csv");
        return sb.toString();
    }

    /**
     * 文件标题
     * @param title
     * @return
     */
    private String generateCSVTitle(String title) {
        StringBuffer sb = new StringBuffer("智能包装箱");
        sb.append("【");
        sb.append(title);
        sb.append("】");
        sb.append("信息报表(");
        sb.append(generateTime());
        sb.append(")");
        return sb.toString();
    }

    /**
     * 生成时间
     * @return
     */
    private String generateTime() {
        DateTime dt = new DateTime();
        StringBuffer sb = new StringBuffer();
        sb.append("截止时间：");
        sb.append(dt.toString("yyyy-MM-dd HH:mm:ss"));
        return sb.toString();
    }

}
