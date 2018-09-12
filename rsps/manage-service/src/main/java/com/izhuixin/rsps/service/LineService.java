package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dao.LineInfo;
import com.izhuixin.rsps.common.vo.web.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 线路服务类接口
 */
@FeignClient("api-service")
@RequestMapping("v1/lineData/")
public interface LineService {

    @GetMapping("/childLines/query/{userId}/{entCode}")
    List<LineInfoVO> queryChildLines(@PathVariable("userId") String userId,
                                     @PathVariable("entCode") String entCode);

    @GetMapping("/line/query/{lineId}/{entCode}")
    LineInfoVO queryLine(@PathVariable("lineId") String lineId,
                         @PathVariable("entCode") String entCode);

    @GetMapping("/lineName/check")
    boolean checkLineName(@RequestParam("lineId") String lineId,
                          @RequestParam("lineName") String lineName,
                          @RequestParam("userId") String userId,
                          @RequestParam("entCode") String entCode);

    @GetMapping("/line/save")
    boolean saveLine(@RequestBody LineInfoVO lineInfo,
                     @RequestParam("entCode") String entCode);

    @GetMapping("/line/remove/{lineId}/{entCode}")
    boolean removeLine(@PathVariable("lineId") String lineId,
                       @PathVariable("entCode") String entCode);

    @GetMapping("/line/update")
    boolean updateLine(@RequestBody LineInfoVO lineInfo,
                       @RequestParam("entCode") String entCode);

    @GetMapping("/lineAndCustom/save")
    boolean saveLineAndCustom(@RequestParam("lineId") String lineId,
                              @RequestParam("customIds") String[] customIds,
                              @RequestParam("entCode") String entCode);

    @GetMapping("/lineAndTransfer/save")
    boolean saveLineAndTransfer(@RequestParam("lineId") String lineId,
                                @RequestParam("userIds") String[] userIds,
                                @RequestParam("entCode") String entCode);

    @GetMapping("/lineAndOperator/save")
    boolean saveLineAndOperator(@RequestParam("lineId") String lineId,
                                @RequestParam("operatorIds") String[] operatorIds,
                                @RequestParam("entCode") String entCode);

    @GetMapping("/lineAndCustom/remove")
    boolean removeLineAndCustom(@RequestParam("lineId") String lineId,
                                @RequestParam("customIds")  String[] customIds,
                                @RequestParam("entCode") String entCode);

    @GetMapping("/lineAndTransfer/remove")
    boolean removeLineAndTransfer(@RequestParam("lineId") String lineId,
                                  @RequestParam("userIds") String[] userIds,
                                  @RequestParam("entCode") String entCode);

    @GetMapping("/lines/operatorId/{operatorId}/{entCode}")
    List<LineInfo> getLinesWithOperatorId(@PathVariable("operatorId") String operatorId,
                                          @PathVariable("entCode") String entCode);

    @GetMapping("/lines/setting")
    boolean settingLines(@RequestParam("operatorId") String operatorId,
                         @RequestParam("lineIds") List<String> lineIds,
                         @RequestParam("entCode") String entCode);

    @GetMapping("/associatedCustom/dtData/query")
    DataTableResDataVO queryDtDataAssociatedCustom(@RequestParam("lineId") String lineId,
                                                   @RequestBody DataTableReqDataVO dtReqData,
                                                   @RequestParam("entId") String entId,
                                                   @RequestParam("entCode") String entCode);

    @GetMapping("/associatedCustom/query")
    List<CustomerInfoVO> queryAssociatedCustom(@RequestParam("lineId") String lineId,
                                               @RequestParam("entId") String entId,
                                               @RequestParam("entCode") String entCode);

    @GetMapping("/associatedTransfer/query")
    List<RelatedSysUserVO> queryAssociatedTransfer(@RequestParam("lineId") String lineId,
                                                   @RequestParam("userId") String userId,
                                                   @RequestParam("entCode") String entCode);

    @GetMapping("/associatedOperator/query")
    List<RelatedOperatorVO> queryAssociatedOperator(@RequestParam("lineId") String lineId,
                                                    @RequestParam("userId") String userId,
                                                    @RequestParam("entCode") String entCode);

    @GetMapping("/lineAndOperator/remove")
    boolean removeLineAndOperator(@RequestParam("lineId") String lineId,
                                  @RequestParam("operatorIds") String[] operatorIds,
                                  @RequestParam("entCode") String entCode);

    @GetMapping("/lineNames/get/customId/{customId}/{entCode}")
    String getLineNamesByCustomId(@PathVariable("customId") String customId,
                                  @PathVariable("entCode") String entCode);


    @GetMapping("/custom/list/dtData/query")
    DataTableResDataVO queryDtDataCustoms(@RequestBody DataTableReqDataVO dtReqData,
                                          @RequestParam("entId") String entId,
                                          @RequestParam("entCode") String entCode);


    /**
     * 客户关联线路数据查询
     * @param customId
     * @param entCode
     * @return
     */
    @GetMapping("/associatedLine/query")
    List<Object[]> queryAssociatedLine(@RequestParam("customId") String customId,
                                       @RequestParam("userId") String userId,
                                       @RequestParam("entCode") String entCode);


    /**
     * 保存客户关联线路信息
     * @param customId
     * @param lineIds
     * @param entCode
     * @return
     */
    @GetMapping("/customAndLine/save")
    boolean saveCustomAndLine(@RequestParam("customId") String customId,
                              @RequestParam("lineIds") String[] lineIds,
                              @RequestParam("entCode") String entCode);

    /**
     * 删除客户关联线路信息
     * @param customId
     * @param lineIds
     * @param entCode
     * @return
     */
    @GetMapping("/customAndLine/remove")
    boolean removeCustomAndLine(@RequestParam("customId")  String customId,
                                @RequestParam("lineIds") String[] lineIds,
                                @RequestParam("entCode") String entCode);

}
