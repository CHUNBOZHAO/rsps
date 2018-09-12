package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dto.ShipmentInfoDTO;
import com.izhuixin.rsps.common.vo.web.DataTableReqDataVO;
import org.springframework.http.ResponseEntity;

public interface BoxOperateRecordReportService {

    ResponseEntity<byte[]> exportCSV(String entCode,
                                     String beginDate,
                                     String endDate,
                                     String line,
                                     String operator,
                                     String forwarder,
                                     String orderId,
                                     String customName,
                                     String customId);

    ShipmentInfoDTO getBoxOperateRecordInfo(DataTableReqDataVO dtReqData,
                                            String entCode,
                                            String beginDate,
                                            String endDate,
                                            String lineName,
                                            String operator,
                                            String forwarder,
                                            String orderId,
                                            String customName,
                                            String customId);
}
