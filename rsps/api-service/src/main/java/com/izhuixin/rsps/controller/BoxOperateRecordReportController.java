package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.dto.ShipmentInfoDTO;
import com.izhuixin.rsps.common.vo.web.DataTableReqDataVO;
import com.izhuixin.rsps.service.BoxOperateRecordReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/reportData/record/operate")
public class BoxOperateRecordReportController {

    @Autowired
    private BoxOperateRecordReportService boxOperateRecordReportService;


    @RequestMapping("/data/get")
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
        return boxOperateRecordReportService.getBoxOperateRecordInfo(dtReqData, entCode, beginDate, endDate, lineName, operator, forwarder, orderId, customName,customId);
    }

    @RequestMapping("/export")
    public ResponseEntity<byte[]> exportCSV(String entCode,
                                            String beginDate,
                                            String endDate,
                                            String line,
                                            String operator,
                                            String forwarder,
                                            String orderId,
                                            String customName,
                                            String customId) {
        return boxOperateRecordReportService.exportCSV(entCode,
                beginDate,
                endDate,
                line,
                operator,
                forwarder,
                orderId,
                customName,
                customId);
    }
}
