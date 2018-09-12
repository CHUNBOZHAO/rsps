package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.web.BoxFlowRecordVO;
import com.izhuixin.rsps.domain.manual.BoxRecordInfo;
import com.izhuixin.rsps.service.BoxRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/boxData/boxRecord")
public class BoxRecordController {

    @Autowired
    private BoxRecordService boxRecordService;

    /**
     * 保存包装箱操作记录
     * @param boxRecordInfo
     * @param entCode
     * @return
     */
    @RequestMapping("/save")
    public boolean saveBoxRecord(@RequestBody BoxRecordInfo boxRecordInfo, String entCode) {
        return boxRecordService.saveBoxRecord(boxRecordInfo, entCode);
    }

    /**
     * 获取指定条件下包装箱记录条数
     * @param rfid
     * @param orderId
     * @param operateType
     * @param entCode
     * @return
     */
    @RequestMapping("/count")
    public Integer countBoxRecord(String rfid,
                           String orderId,
                           Byte operateType,
                           String entCode) {
        return boxRecordService.countBoxRecord(rfid, orderId, operateType, entCode);
    }

    /**
     * 更新指定条件下的包装箱记录
     * @param boxRecordInfo
     * @param rfid
     * @param orderId
     * @param operateType
     * @param entCode
     * @return
     */
    @RequestMapping("/update")
    public boolean updateBoxRecord(@RequestBody BoxRecordInfo boxRecordInfo,
                            String rfid,
                            String orderId,
                            Byte operateType,
                            String entCode) {
        return boxRecordService.updateBoxRecord(boxRecordInfo, rfid, orderId, operateType, entCode);
    }

    /**
     * 获取包装箱操作记录流程
     * @param boxId
     * @param orderId
     * @param entCode
     * @return
     */
    @RequestMapping("/flowInfo/get")
    public List<BoxFlowRecordVO> getBoxFlowInfo(String boxId, String orderId, String entCode) {
        return boxRecordService.getBoxFlowInfo(boxId, orderId, entCode);
    }


}
