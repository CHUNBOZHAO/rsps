package com.izhuixin.rsps.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.izhuixin.rsps.common.constant.BoxStatus;
import com.izhuixin.rsps.common.constant.OperateType;
import com.izhuixin.rsps.common.constant.SignStatus;
import com.izhuixin.rsps.common.dba.BaseAbstractService;
import com.izhuixin.rsps.common.dto.ShipmentInfoDTO;
import com.izhuixin.rsps.common.object.Pair;
import com.izhuixin.rsps.common.vo.web.DataTableReqDataVO;
import com.izhuixin.rsps.dao.manual.BoxRecordDao;
import com.izhuixin.rsps.domain.manual.BoxOperateRecordReportInfo;
import com.izhuixin.rsps.domain.manual.ShipmentReportInfo;
import com.izhuixin.rsps.service.BoxOperateRecordReportService;
import com.izhuixin.rsps.service.ExtendFileService;
import com.izhuixin.rsps.service.LineService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BoxOperateRecordReportServiceImpl extends BaseAbstractService implements BoxOperateRecordReportService {

    @Autowired
    private BoxRecordDao boxRecordDao;

    @Autowired
    private ExtendFileService extendFileService;

    @Autowired
    private LineService lineService;

    /**
     * 导出出货量日报表
     * @param entCode
     * @return
     */
    @Override
    public ResponseEntity<byte[]> exportCSV(String entCode,
                                            String beginDate,
                                            String endDate,
                                            String line,
                                            String operator,
                                            String forwarder,
                                            String orderId,
                                            String customName,
                                            String cutomId) {
        String title = "Operate_Record_" + new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        List<String> forwarders = Lists.newArrayList();
        forwarders.add(forwarder);

        // 数据写入csv文件
        String fileName = generateCSVFileName();
        String filePath = extendFileService.getReportLocalPath(entCode, title);
//        File newFile = new File("D:/demo/2.xls");
//        CSVWriter csvWriter = null;
//        try {
//

//
//            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(filePath), Charset.forName("utf-8"));
//            csvWriter = new CSVWriter(out, ',');
            List<String[]> dataList = generateCSVData(entCode, beginDate, endDate, line, operator, forwarders, orderId, customName,cutomId);
//            csvWriter.writeAll(dataList);
//            csvWriter.close();
//        } catch (IOException e) {
//            logger.error(String.format("生成CSV数据文件出现异常", e));
//        }

        try {

//            if(!newFile.exists()){
//                newFile.createNewFile();
//            }

            OutputStream outputStream = new FileOutputStream(filePath);


            HSSFWorkbook workbook = new HSSFWorkbook();

            //设置excel样式
            HSSFCellStyle style = workbook.createCellStyle();

            //背景色
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);

            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 设置居中
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            style.setBottomBorderColor(HSSFColor.BLACK.index);
            style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);
            style.setLeftBorderColor(HSSFColor.BLACK.index);
            style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            style.setRightBorderColor(HSSFColor.BLACK.index);
            style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
            style.setTopBorderColor(HSSFColor.BLACK.index);

            HSSFSheet sheet = workbook.createSheet("Sheet1");
            //设置单元格高度
            sheet.setColumnWidth(0, 10 * 256);
            sheet.setColumnWidth(1, 35 * 256);
            sheet.setColumnWidth(1, 35 * 256);
            sheet.setColumnWidth(3, 35 * 256);
            sheet.setColumnWidth(4, 10 * 256);
            sheet.setColumnWidth(5, 45 * 256);
            sheet.setColumnWidth(6, 35 * 256);
            sheet.setColumnWidth(7, 35 * 256);
            sheet.setColumnWidth(8, 40 * 256);
            sheet.setColumnWidth(9, 40 * 256);
            sheet.setColumnWidth(10, 10 * 256);
            sheet.setColumnWidth(11, 10 * 256);


            String[] title1 = dataList.get(0);

            HSSFRow row = sheet.createRow(0);
            for (int i=0;i<title1.length;i++){
                HSSFCell cell  = row.createCell(i);
                cell.setCellValue(title1[i]);
                cell.setCellStyle(style);
            }

            row.setHeightInPoints(20); // 设置行的高度
            for(int j=1;j<dataList.size();j++){
                HSSFRow row1 = sheet.createRow(j);
                for(int m=0;m<dataList.get(j).length;m++){
                    HSSFCell cell1  = row1.createCell(m);
                    cell1.setCellValue(dataList.get(j)[m]);
                }

            }

            workbook.setActiveSheet(0);
            workbook.write(outputStream);
            outputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
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
     * 获取包装箱操作记录报表
     * @param dtReqData
     * @param entCode
     * @param beginDate
     * @param endDate
     * @param lineName
     * @param operator
     * @param forwarder
     * @param orderId
     * @param customName
     * @return
     */
    @Override
    public ShipmentInfoDTO getBoxOperateRecordInfo(DataTableReqDataVO dtReqData,
                                                      String entCode,
                                                      String beginDate,
                                                      String endDate,
                                                      String lineName,
                                                      String operator,
                                                      String forwarder,
                                                      String orderId,
                                                      String customName,
                                                      String customId) {

        List<String> forwarders = Lists.newArrayList();
        forwarders.add(forwarder);

        ShipmentInfoDTO shipmentInfoDTO = generateShipmentData(entCode, beginDate, endDate, lineName, operator, forwarders, orderId, customName,customId);
        return shipmentInfoDTO;
    }

    /**
     * 生成CSV需要的数据对象
     * @param entCode
     * @return
     */
    private List<String[]> generateCSVData(String entCode,
                                           String beginDate,
                                           String endDate,
                                           String line,
                                           String operator,
                                           List<String> forwarderIds,
                                           String orderId,
                                           String customName,
                                           String customId) {
        ShipmentInfoDTO shipmentInfoDTO = generateShipmentData(entCode, beginDate, endDate, line, operator, forwarderIds, orderId, customName,customId);
        int fetchCount = shipmentInfoDTO.getFetchCount();
        int signCount = shipmentInfoDTO.getSignCount();
        int recycleCount = shipmentInfoDTO.getRecycleCount();
        int gohomeCount = shipmentInfoDTO.getGohomeCount();
        int totalCount = shipmentInfoDTO.getTotalCount();

        Integer fetchRate = Math.round((fetchCount * 1.0f/ totalCount) * 100);
        Integer signRate = Math.round((signCount * 1.0f/ totalCount) * 100);
        Integer recycleRate = Math.round((recycleCount * 1.0f/ totalCount) * 100);
        Integer gohomeRate = Math.round((gohomeCount * 1.0f/ totalCount) * 100);


        String[] objects = new String[12];
//        objects[0] = "订单ID";
//        objects[1] = "包装箱ID";
//        objects[2] = "客户名称";
//        objects[3] = "配货日期";
//        objects[4] = "线路";
////        objects[5] = "装车（装车率：" + fetchRate.toString() + "%）";
////        objects[6] = "签收（签收率：" + signRate.toString() + "%）";
////        objects[7] = "回收（回收率：" + recycleRate.toString() + "%）";
////        objects[8] = "回库（回库率：" + gohomeRate.toString() + "%）";
//        objects[5] = "装车（" + fetchCount + "/" + totalCount + " | " + fetchRate.toString() + "%）";
//        objects[6] = "送达（" + signCount + "/" + totalCount + " | " + signRate.toString() + "%）";
//        objects[7] = "回收（" + recycleCount + "/" + totalCount + " | " + recycleRate.toString() + "%）";
//        objects[8] = "回库（" + gohomeCount + "/" + totalCount + " | " + gohomeRate.toString() + "%）";
//        objects[9] = "异常";
//        objects[10] = "当前状态";
        objects[0] = "订单ID";
        objects[1] = "包装箱ID";
        objects[2] = "客户ID";
        objects[3] = "客户名称";
        objects[4] = "配货日期";
        objects[5] = "线路";
//        objects[5] = "装车（装车率：" + fetchRate.toString() + "%）";
//        objects[6] = "签收（签收率：" + signRate.toString() + "%）";
//        objects[7] = "回收（回收率：" + recycleRate.toString() + "%）";
//        objects[8] = "回库（回库率：" + gohomeRate.toString() + "%）";
        objects[6] = "装车（" + fetchCount + "/" + totalCount + " | " + fetchRate.toString() + "%）";
        objects[7] = "送达（" + signCount + "/" + totalCount + " | " + signRate.toString() + "%）";
        objects[8] = "回收（" + recycleCount + "/" + totalCount + " | " + recycleRate.toString() + "%）";
        objects[9] = "回库（" + gohomeCount + "/" + totalCount + " | " + gohomeRate.toString() + "%）";
        objects[10] = "异常";
        objects[11] = "当前状态";


        List<String[]> objectList = shipmentInfoDTO.getDatas();
        objectList.add(0, objects);

        return objectList;
    }

    /**
     * 获取出货信息的数据信息
     * @param entCode
     * @param beginDate
     * @param endDate
     * @param line
     * @param operator
     * @param forwarderIds
     * @param orderId
     * @param customName
     * @return
     */
    private ShipmentInfoDTO generateShipmentData(String entCode,
                                                  String beginDate,
                                                  String endDate,
                                                  String line,
                                                  String operator,
                                                  List<String> forwarderIds,
                                                  String orderId,
                                                  String customName,
                                                  String customId) {
        ShipmentInfoDTO shipmentInfoDTO = new ShipmentInfoDTO();

        List<BoxOperateRecordReportInfo> boxOperateRecordReportInfos = generateOperateRecordData(entCode, beginDate, endDate);

        List<String> forwarderLines = Lists.newArrayList();
        for (String itemId : forwarderIds) {
            List<String> lineIds = lineService.queryLineIds(itemId, entCode);
            forwarderLines.addAll(lineIds);
        }
        List<String> newForwarderLines = forwarderLines.stream().distinct().collect(Collectors.toList());


        List<BoxOperateRecordReportInfo> filterReportInfos = Lists.newArrayList();
        Integer fetchCount = 0; // 装车次数
        Integer signCount = 0; // 签收次数
        Integer recycleCount = 0; // 回收次数
        Integer gohomeCount = 0; // 回库次数
        Integer totalCount = 0; // 总数
        Integer wholeOperateCount = 0; // 操作计数
        for (BoxOperateRecordReportInfo item : boxOperateRecordReportInfos) {
            // 过滤订单
            if (StringUtils.isNotBlank(orderId)) {
                if (!item.getOrderId().contains(orderId.trim()) || !item.getOrderId().equals(orderId.trim())) {
                    continue;
                }
            }

            // 过滤承运商
            if (item.getLineIds() == null || !filterForwarder(newForwarderLines, item.getLineIds())) {
                continue;
            }

            // 过滤客户
            if (StringUtils.isNotBlank(customName)) {
                if (!item.getCustomName().contains(customName)) {
                    continue;
                }
            }
            //过滤客户ID
            if(StringUtils.isNotBlank(customId)){
                if(!item.getCustomId().contains(customId)){
                    continue;
                }
            }


            if (StringUtils.isBlank(operator) ||
                    (StringUtils.isNotBlank(item.getFetchTimeStr()) && item.getFetchTimeStr().contains(operator)) ||
                    (StringUtils.isNotBlank(item.getSignTimeStr()) && item.getSignTimeStr().contains(operator)) ||
                    (StringUtils.isNotBlank(item.getRecycleTimeStr()) && item.getRecycleTimeStr().contains(operator))
                    ) {
                if (StringUtils.isBlank(line) || item.getLineName().contains(line)) {
                    filterReportInfos.add(item);

                    wholeOperateCount = 0;
                    if (StringUtils.isNotBlank(item.getFetchTimeStr())) {
                        fetchCount++;
                        wholeOperateCount++;
                    }
                    if (StringUtils.isNotBlank(item.getSignTimeStr())) {
                        signCount++;
                        wholeOperateCount++;
                    }
                    if (StringUtils.isNotBlank(item.getRecycleTimeStr())) {
                        recycleCount++;
                        wholeOperateCount++;
                    }
                    if (StringUtils.isNotBlank(item.getGohomeTimeStr())) {
                        gohomeCount++;
                        wholeOperateCount++;
                    }
                    totalCount++;

                    if (wholeOperateCount == 0) {
                        item.setExceptionDescr("无操作");
                    } else if (wholeOperateCount < 4) {
                        item.setExceptionDescr("漏操作");
                    } else {
                        item.setExceptionDescr("正常");
                        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                        if (StringUtils.isNotBlank(item.getFetchTimeStr()) && StringUtils.isNotBlank(item.getSignTimeStr())) {
                            DateTime signTime = DateTime.parse(item.getSignTimeStr().split("\\(")[0], format);
                            DateTime fetchTime = DateTime.parse(item.getFetchTimeStr().split("\\(")[0], format);
                            if (signTime.getMillis() - fetchTime.getMillis() < 1000 * 60 * 10) {
                                item.setExceptionDescr("不规范操作");
                            }
                        }
                        if (StringUtils.isNotBlank(item.getGohomeTimeStr()) && StringUtils.isNotBlank(item.getRecycleTimeStr())) {
                            DateTime gohomeTime = DateTime.parse(item.getGohomeTimeStr().split("\\(")[0], format);
                            DateTime recycleTime = DateTime.parse(item.getRecycleTimeStr().split("\\(")[0], format);
                            if (gohomeTime.getMillis() - recycleTime.getMillis() < 1000 * 60 * 10) {
                                item.setExceptionDescr("不规范操作");
                            }
                        }
                    }

                }
            }
        }

        shipmentInfoDTO.setFetchCount(fetchCount);
        shipmentInfoDTO.setSignCount(signCount);
        shipmentInfoDTO.setRecycleCount(recycleCount);
        shipmentInfoDTO.setGohomeCount(gohomeCount);
        shipmentInfoDTO.setTotalCount(totalCount);

        // 根据配货时间进行一次排序
        filterReportInfos.sort((o1, o2) -> o1.getBindTime().before(o1.getBindTime()) ? 1 : 0);

        List<String[]> objectList = Lists.newArrayList();
        String[] objects = null;
        for (BoxOperateRecordReportInfo reportInfo : filterReportInfos) {
            objects = new String[12];
            objects[0] = reportInfo.getOrderId();
            objects[1] = reportInfo.getBoxId();
//            objects[2] = reportInfo.getCustomName();
//            objects[3] = reportInfo.getBindTimeStr();
//            objects[4] = reportInfo.getLineName();
//            objects[5] = reportInfo.getFetchTimeStr();
//            objects[6] = reportInfo.getSignTimeStr();
//            objects[7] = reportInfo.getRecycleTimeStr();
//            objects[8] = reportInfo.getGohomeTimeStr();
//            objects[9] = reportInfo.getExceptionDescr();
//            objects[10] = reportInfo.getBoxStatusDescr();
            objects[2] = reportInfo.getCustomId();
            objects[3] = reportInfo.getCustomName();
            objects[4] = reportInfo.getBindTimeStr();
            objects[5] = reportInfo.getLineName();
            objects[6] = reportInfo.getFetchTimeStr();
            objects[7] = reportInfo.getSignTimeStr();
            objects[8] = reportInfo.getRecycleTimeStr();
            objects[9] = reportInfo.getGohomeTimeStr();
            objects[10] = reportInfo.getExceptionDescr();
            objects[11] = reportInfo.getBoxStatusDescr();

            objectList.add(objects);
        }

        shipmentInfoDTO.setDatas(objectList);

        return shipmentInfoDTO;
    }


    /**
     * 生成CSV文件名称
     * @return
     */
    private String generateCSVFileName() {
        StringBuffer sb = new StringBuffer("智能包装箱出货信息.xls");
        return sb.toString();
    }

    /**
     * 生成数据
     * @param entCode
     * @param beginDate
     * @param endDate
     * @return
     */
    private List<BoxOperateRecordReportInfo> generateOperateRecordData(String entCode,
                                                                       String beginDate,
                                                                       String endDate) {
        if (StringUtils.isBlank(beginDate) || StringUtils.isBlank(endDate)) {
            DateTime endTime = DateTime.now();
            DateTime beginTime = endTime.minusDays(3);

            beginDate = beginTime.toString("yyyy-MM-dd HH:mm:ss");
            endDate = endTime.toString("yyyy-MM-dd HH:mm:ss");
        } else {
            beginDate += " 00:00:00";
            endDate += " 23:59:59";
        }

        String boxStatusDescr = ""; // 当前状态描述
        String keyStr = ""; // 唯一标示

        // 获取制定日期段内的出货信息
        List<ShipmentReportInfo> shipmentReportInfos = boxRecordDao.getShipmentReport(entCode, beginDate, endDate);
        List<ShipmentReportInfo> reportInfos = boxRecordDao.getBoxRecords(entCode, beginDate, new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        // 获取有效的客户-线路集合关联
        List<String> invalidCustomIds = shipmentReportInfos.stream().map(info -> info.getCustomerId()).distinct().collect(Collectors.toList());
        Pair<Map, Map> pair = lineService.getCustomAllLines(invalidCustomIds, entCode);
        Map<String, List<String>> lineNamesMap = Maps.newHashMap();
        Map<String, List<String>> lineIdsMap = Maps.newHashMap();
        if (pair != null) {
            lineNamesMap = pair.getFirst();
            lineIdsMap = pair.getSecond();
        }

        List<BoxOperateRecordReportInfo> boxOperateRecordReportInfos = Lists.newArrayList();
        Map<String, BoxOperateRecordReportInfo> boxOperateRecordReportInfoMap = Maps.newTreeMap();

        for (ShipmentReportInfo reportInfo : shipmentReportInfos) {
            if (StringUtils.isBlank(reportInfo.getOrderId())) {
                continue;
            }

            keyStr = reportInfo.getOrderId().concat(reportInfo.getBoxId());

            BoxOperateRecordReportInfo boxOperateRecordReportInfo = new BoxOperateRecordReportInfo();
            boxOperateRecordReportInfo.setBindTime(reportInfo.getCreateTime());
            boxOperateRecordReportInfo.setBindTimeStr(new DateTime(reportInfo.getCreateTime()).toString("yyyy-MM-dd"));
            boxOperateRecordReportInfo.setBoxId(reportInfo.getBoxId());
            //2018-8-29设置客户ID
            boxOperateRecordReportInfo.setCustomId(reportInfo.getCustomerId());
            if (reportInfo.getBoxStatus() != null) {
                boxStatusDescr = BoxStatus.getDesc(reportInfo.getBoxStatus().byteValue());
            } else {
                boxStatusDescr = "--";
            }
            boxOperateRecordReportInfo.setBoxStatusDescr(StringUtils.isBlank(boxStatusDescr) ? "" : boxStatusDescr);
            boxOperateRecordReportInfo.setExceptionDescr("");
            boxOperateRecordReportInfo.setOperator(reportInfo.getOperator());
            boxOperateRecordReportInfo.setOrderId(reportInfo.getOrderId());
            boxOperateRecordReportInfo.setCustomName(reportInfo.getCustomer());
            boxOperateRecordReportInfo.setLineIds(lineIdsMap.getOrDefault(reportInfo.getCustomerId(), Lists.newArrayList()));
            boxOperateRecordReportInfo.setLineName(lineNamesMap.getOrDefault(reportInfo.getCustomerId(), Lists.newArrayList()).stream().collect(Collectors.joining(",")));
            boxOperateRecordReportInfoMap.put(keyStr, boxOperateRecordReportInfo);

        }
        for (ShipmentReportInfo reportInfo : reportInfos) {
            if (StringUtils.isBlank(reportInfo.getOrderId())) {
                continue;
            }
            keyStr = reportInfo.getOrderId().concat(reportInfo.getBoxId());

            if (reportInfo.getOperateType().byteValue() == OperateType.OBTAIN.getIndex().byteValue()) {  // 装车
                if (boxOperateRecordReportInfoMap.containsKey(keyStr)) {
                    BoxOperateRecordReportInfo boxOperateRecordReportInfo = boxOperateRecordReportInfoMap.get(keyStr);
                    if (reportInfo.getBoxStatus() != null) {
                        boxStatusDescr = BoxStatus.getDesc(reportInfo.getBoxStatus().byteValue());
                    } else {
                        boxStatusDescr = "--";
                    }
                    boxOperateRecordReportInfo.setBoxStatusDescr(StringUtils.isBlank(boxStatusDescr) ? "" : boxStatusDescr);
                    boxOperateRecordReportInfo.setFetchTimeStr(new DateTime(reportInfo.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss").concat("(" + reportInfo.getOperator() + ")"));
                }
            } else if (reportInfo.getOperateType().byteValue() == OperateType.SIGN_IN.getIndex().byteValue() ||
                    reportInfo.getOperateType().byteValue() == OperateType.DENIED_SIGN.getIndex().byteValue() ) {  // 签收 & 拒签
                if (boxOperateRecordReportInfoMap.containsKey(keyStr)) {

                    BoxOperateRecordReportInfo boxOperateRecordReportInfo = boxOperateRecordReportInfoMap.get(keyStr);
                    if (reportInfo.getBoxStatus() != null) {
                        boxStatusDescr = BoxStatus.getDesc(reportInfo.getBoxStatus().byteValue());
                    } else {
                        boxStatusDescr = "--";
                    }
                    boxOperateRecordReportInfo.setBoxStatusDescr(StringUtils.isBlank(boxStatusDescr) ? "" : boxStatusDescr);
                    boxOperateRecordReportInfo.setSignTimeStr(new DateTime(reportInfo.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss").concat("(" + reportInfo.getOperator() + ")"));
                    boxOperateRecordReportInfo.setSignType(SignStatus.SIGN_IN.getIndex().byteValue());
                }
            } else if (reportInfo.getOperateType().byteValue() == OperateType.RECYCLE.getIndex().byteValue()) {  // 回收
                if (boxOperateRecordReportInfoMap.containsKey(keyStr)) {

                    BoxOperateRecordReportInfo boxOperateRecordReportInfo = boxOperateRecordReportInfoMap.get(keyStr);
                    if (reportInfo.getBoxStatus() != null) {
                        boxStatusDescr = BoxStatus.getDesc(reportInfo.getBoxStatus().byteValue());
                    } else {
                        boxStatusDescr = "--";
                    }
                    boxOperateRecordReportInfo.setBoxStatusDescr(StringUtils.isBlank(boxStatusDescr) ? "" : boxStatusDescr);
                    boxOperateRecordReportInfo.setRecycleTimeStr(new DateTime(reportInfo.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss").concat("(" + reportInfo.getOperator() + ")"));
                }
            } else if (reportInfo.getOperateType().byteValue() == OperateType.GO_HOME.getIndex().byteValue()) {  // 回库
                if (boxOperateRecordReportInfoMap.containsKey(keyStr)) {

                    BoxOperateRecordReportInfo boxOperateRecordReportInfo = boxOperateRecordReportInfoMap.get(keyStr);
                    if (reportInfo.getBoxStatus() != null) {
                        boxStatusDescr = BoxStatus.getDesc(reportInfo.getBoxStatus().byteValue());
                    } else {
                        boxStatusDescr = "--";
                    }
                    boxOperateRecordReportInfo.setBoxStatusDescr(StringUtils.isBlank(boxStatusDescr) ? "" : boxStatusDescr);
                    boxOperateRecordReportInfo.setGohomeTimeStr(new DateTime(reportInfo.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss").concat("(回收设备)"));
                }
            } else if (reportInfo.getOperateType().byteValue() == OperateType.TRANSIT_SIGN_IN.getIndex().byteValue()) {  // 中转签收
                if (boxOperateRecordReportInfoMap.containsKey(keyStr)) {

                    BoxOperateRecordReportInfo boxOperateRecordReportInfo = boxOperateRecordReportInfoMap.get(keyStr);
                    if (reportInfo.getBoxStatus() != null) {
                        boxStatusDescr = BoxStatus.getDesc(reportInfo.getBoxStatus().byteValue());
                    } else {
                        boxStatusDescr = "--";
                    }
                    boxOperateRecordReportInfo.setBoxStatusDescr(StringUtils.isBlank(boxStatusDescr) ? "" : boxStatusDescr);
                    boxOperateRecordReportInfo.setSignTimeStr(new DateTime(reportInfo.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss").concat("(" + reportInfo.getOperator() + ")"));
                    boxOperateRecordReportInfo.setSignType(SignStatus.TRANSFER_SIGN.getIndex().byteValue());

                    // 中转签收完成，移除该记录
                    boxOperateRecordReportInfos.add(boxOperateRecordReportInfo);
                    boxOperateRecordReportInfoMap.remove(keyStr);

                    // 增加新的流程
                    BoxOperateRecordReportInfo boxOperateRecordReportInfoNew = new BoxOperateRecordReportInfo();
                    boxOperateRecordReportInfoNew.setBindTime(boxOperateRecordReportInfo.getBindTime());
                    boxOperateRecordReportInfoNew.setBindTimeStr(boxOperateRecordReportInfo.getBindTimeStr());
                    boxOperateRecordReportInfoNew.setBoxId(boxOperateRecordReportInfo.getBoxId());
                    if (reportInfo.getBoxStatus() != null) {
                        boxStatusDescr = BoxStatus.getDesc(reportInfo.getBoxStatus().byteValue());
                    } else {
                        boxStatusDescr = "--";
                    }
                    boxOperateRecordReportInfo.setBoxStatusDescr(StringUtils.isBlank(boxStatusDescr) ? "" : boxStatusDescr);
                    boxOperateRecordReportInfoNew.setExceptionDescr("");
                    boxOperateRecordReportInfoNew.setCustomName(boxOperateRecordReportInfo.getCustomName());
                    boxOperateRecordReportInfoNew.setLineName(boxOperateRecordReportInfo.getLineName());
                    boxOperateRecordReportInfoNew.setOperator(reportInfo.getOperator());
                    boxOperateRecordReportInfoNew.setOrderId(reportInfo.getOrderId());
                    boxOperateRecordReportInfoMap.put(keyStr, boxOperateRecordReportInfoNew);
                }
            }
        }

        for (Map.Entry<String, BoxOperateRecordReportInfo> entry : boxOperateRecordReportInfoMap.entrySet()) {
            boxOperateRecordReportInfos.add(entry.getValue());
        }

        return boxOperateRecordReportInfos;

    }

    /**
     * 检测承运商线路
     * @param forwarderIdLines
     * @param filterLineIds
     * @return
     */
    private boolean filterForwarder(List<String> forwarderIdLines, List<String> filterLineIds) {
        boolean res = false;
        for (String filterId : filterLineIds) {
            if (forwarderIdLines.contains(filterId)) {
                res = true;
                break;
            }
        }
        return res;
    }


}
