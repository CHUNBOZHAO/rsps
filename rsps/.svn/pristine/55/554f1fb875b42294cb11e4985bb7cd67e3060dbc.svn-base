package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dao.BoxRecordInfo;
import com.izhuixin.rsps.common.vo.web.BoxFlowRecordVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("api-service")
@RequestMapping("v1/boxData/boxRecord")
public interface BoxRecordService {

    /**
     * 保存包装箱操作记录
     * @param boxRecordInfo
     * @param entCode
     * @return
     */
    @RequestMapping("/save")
    boolean saveBoxRecord(@RequestBody BoxRecordInfo boxRecordInfo,
                          @RequestParam("entCode") String entCode);

    /**
     * 获取指定条件下包装箱记录条数
     * @param rfid
     * @param orderId
     * @param operateType
     * @param entCode
     * @return
     */
    @RequestMapping("/count")
    Integer countBoxRecord(@RequestParam("rfid") String rfid,
                           @RequestParam("orderId") String orderId,
                           @RequestParam("operateType") Byte operateType,
                           @RequestParam("entCode") String entCode);

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
    boolean updateBoxRecord(@RequestBody BoxRecordInfo boxRecordInfo,
                            @RequestParam("rfid") String rfid,
                            @RequestParam("orderId") String orderId,
                            @RequestParam("operateType") Byte operateType,
                            @RequestParam("entCode") String entCode);

    /**
     * 获取包装箱操作记录流程
     * @param boxId
     * @param orderId
     * @param entCode
     * @return
     */
    @RequestMapping("/flowInfo/get")
    List<BoxFlowRecordVO> getBoxFlowInfo(@RequestParam("boxId") String boxId,
                                         @RequestParam("orderId") String orderId,
                                         @RequestParam("entCode") String entCode);


}
