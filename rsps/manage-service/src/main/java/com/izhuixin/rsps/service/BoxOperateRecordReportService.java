package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dto.ShipmentInfoDTO;
import com.izhuixin.rsps.common.vo.web.DataTableReqDataVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("api-service")
@RequestMapping("v1/reportData/record/operate")
public interface BoxOperateRecordReportService {

    @RequestMapping("/export")
    ResponseEntity<byte[]> exportCSV(@RequestParam("entCode") String entCode,
                                     @RequestParam("beginDate") String beginDate,
                                     @RequestParam("endDate") String endDate,
                                     @RequestParam("line") String line,
                                     @RequestParam("operator") String operator,
                                     @RequestParam("forwarder") String forwarder,
                                     @RequestParam("orderId") String orderId,
                                     @RequestParam("customName") String customName,
                                     @RequestParam("customId") String customId);


    @RequestMapping("/data/get")
    ShipmentInfoDTO getBoxOperateRecordInfo(@RequestParam("dtReqData") DataTableReqDataVO dtReqData,
                                            @RequestParam("entCode") String entCode,
                                            @RequestParam("beginDate") String beginDate,
                                            @RequestParam("endDate") String endDate,
                                            @RequestParam("lineName") String lineName,
                                            @RequestParam("operator") String operator,
                                            @RequestParam("forwarder") String forwarder,
                                            @RequestParam("orderId") String orderId,
                                            @RequestParam("customName") String customName,
                                            @RequestParam("customId") String customId);
}
