package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.web.*;
import com.izhuixin.rsps.domain.manual.LineInfo;
import com.izhuixin.rsps.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 线路服务类接口
 */
@RestController
@RequestMapping("v1/lineData/")
public class LineController {

    @Autowired
    private LineService lineService;

    @RequestMapping("/childLines/query/{userId}/{entCode}")
    public List<LineInfoVO> queryChildLines(@PathVariable String userId, @PathVariable String entCode) {
        return lineService.queryChildLines(userId, entCode);
    }

    @RequestMapping("/line/query/{lineId}/{entCode}")
    public LineInfoVO queryLine(@PathVariable String lineId, @PathVariable String entCode) {
        return lineService.queryLine(lineId, entCode);
    }

    @RequestMapping("/lineName/check")
    public boolean checkLineName(String lineId, String lineName, String userId, String entCode) {
        return lineService.checkLineName(lineId, lineName, userId, entCode);
    }

    @RequestMapping("/line/save")
    public boolean saveLine(@RequestBody LineInfoVO lineInfo, String entCode) {
        return lineService.saveLine(lineInfo, entCode);
    }

    @RequestMapping("/line/remove/{lineId}/{entCode}")
    public boolean removeLine(@PathVariable String lineId, @PathVariable String entCode) {
        return lineService.removeLine(lineId, entCode);
    }

    @RequestMapping("/line/update")
    public boolean updateLine(@RequestBody LineInfoVO lineInfo, String entCode) {
        return lineService.updateLine(lineInfo, entCode);
    }

    @RequestMapping("/lineAndCustom/save")
    public boolean saveLineAndCustom(String lineId, String[] customIds, String entCode) {
        return lineService.saveLineAndCustom(lineId, customIds, entCode);
    }

    @RequestMapping("/lineAndTransfer/save")
    public boolean saveLineAndTransfer(String lineId, String[] userIds, String entCode) {
        return lineService.saveLineAndTransfer(lineId, userIds, entCode);
    }

    @RequestMapping("/lineAndOperator/save")
    public boolean saveLineAndOperator(String lineId, String[] operatorIds, String entCode) {
        return lineService.saveLineAndOperator(lineId, operatorIds, entCode);
    }

    @RequestMapping("/lineAndCustom/remove")
    public boolean removeLineAndCustom(String lineId, String[] customIds, String entCode) {
        return lineService.removeLineAndCustom(lineId, customIds, entCode);
    }

    @RequestMapping("/lineAndTransfer/remove")
    public boolean removeLineAndTransfer(String lineId, String[] userIds, String entCode) {
        return lineService.removeLineAndTransfer(lineId, userIds, entCode);
    }

    @RequestMapping("/lines/operatorId/{operatorId}/{entCode}")
    public List<LineInfo> getLinesWithOperatorId(String operatorId, String entCode) {
        return lineService.getLinesWithOperatorId(operatorId, entCode);
    }

    @RequestMapping("/lines/setting")
    public boolean settingLines(String operatorId, List<String> lineIds, String entCode) {
        return lineService.settingLines(operatorId, lineIds, entCode);
    }

    @RequestMapping("/custom/list/dtData/query")
    public DataTableResDataVO queryDtDataCustoms(@RequestBody DataTableReqDataVO dtReqData, String entId, String entCode) {
        return lineService.queryDtDataCustoms(dtReqData, entId, entCode);
    }

    @RequestMapping("/associatedCustom/dtData/query")
    public DataTableResDataVO queryDtDataAssociatedCustom(String lineId,
                                                          @RequestBody DataTableReqDataVO dtReqData, String entId, String entCode) {
        return lineService.queryDtDataAssociatedCustom(lineId, dtReqData, entId, entCode);
    }

    @RequestMapping("/associatedCustom/query")
    public List<CustomerInfoVO> queryAssociatedCustom(String lineId, String entId, String entCode) {
        return lineService.queryAssociatedCustom(lineId, entId, entCode);
    }

    @RequestMapping("/associatedTransfer/query")
    public List<RelatedSysUserVO> queryAssociatedTransfer(String lineId, String userId, String entCode) {
        return lineService.queryAssociatedTransfer(lineId, userId, entCode);
    }

    @RequestMapping("/associatedOperator/query")
    public List<RelatedOperatorVO> queryAssociatedOperator(String lineId, String userId, String entCode) {
        return lineService.queryAssociatedOperator(lineId, userId, entCode);
    }

    @RequestMapping("/lineAndOperator/remove")
    public boolean removeLineAndOperator(String lineId, String[] operatorIds, String entCode) {
        return lineService.removeLineAndOperator(lineId, operatorIds, entCode);
    }

    @RequestMapping("/lineNames/get/customId/{customId}/{entCode}")
    public String getLineNamesByCustomId(@PathVariable String customId, @PathVariable String entCode) {
        return lineService.getLineNamesByCustomId(customId, entCode);
    }


    /**
     * 客户关联线路数据查询
     * @param customId
     * @param entCode
     * @return
     */
    @RequestMapping("/associatedLine/query")
    public List<Object[]> queryAssociatedLine(String customId, String userId, String entCode) {
        return lineService.queryAssociatedLine(customId, userId,  entCode);
    }


    /**
     * 保存客户关联线路信息
     * @param customId
     * @param lineIds
     * @param entCode
     * @return
     */
    @RequestMapping("/customAndLine/save")
    public boolean saveCustomAndLine(String customId, String[] lineIds, String entCode) {
        return lineService.saveCustomAndLine(customId, lineIds, entCode);
    }

    /**
     * 删除客户关联线路信息
     * @param customId
     * @param lineIds
     * @param entCode
     * @return
     */
    @GetMapping("/customAndLine/remove")
    public boolean removeCustomAndLine(String customId, String[] lineIds, String entCode) {
        return lineService.removeCustomAndLine(customId, lineIds, entCode);
    }

}
