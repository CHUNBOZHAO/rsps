package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.page.Paginator;
import com.izhuixin.rsps.common.vo.web.BoxInfoVO;
import com.izhuixin.rsps.common.vo.web.BoxesCustomInfoVO;
import com.izhuixin.rsps.common.vo.web.BoxesOperatorInfoVO;
import com.izhuixin.rsps.domain.manual.BoxInfo;
import com.izhuixin.rsps.domain.manual.BoxTypeInfo;
import com.izhuixin.rsps.service.BoxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("v1/boxData/boxInfo")
public class BoxInfoController {

    @Autowired
    private BoxInfoService boxInfoService;

    /**
     * 通过rfid获取包装箱信息
     * @param rfid
     * @param entCode
     * @return
     */
    @RequestMapping("/get/rfid/{rfid}/{entCode}")
    public BoxInfo getBoxInfoByRfid(@PathVariable String rfid, @PathVariable String entCode) {
        return boxInfoService.getBoxInfoByRfid(rfid, entCode);
    }

    /**
     * 通过订单ID获取包装箱信息
     * @param orderId
     * @param entCode
     * @return
     */
    @RequestMapping("/get/orderId/{orderId}/{entCode}")
    public List<BoxInfo> getBoxInfoByOrderid(@PathVariable String orderId, @PathVariable String entCode) {
        return boxInfoService.getBoxInfoByOrderid(orderId, entCode);
    }

    /**
     * 通过指定条件查询包装箱
     * @param operatorId
     * @param boxStatus
     * @param beginTime
     * @param endTime
     * @param paginator
     * @param entCode
     * @return
     */
    @RequestMapping("/boxes/query")
    public List<BoxInfo> queryBoxes(String operatorId,
                             Byte[] boxStatus,
                             Long beginTime,
                             Long endTime,
                             Paginator paginator,
                             String entCode) {
        return boxInfoService.queryBoxes(operatorId, Arrays.asList(boxStatus), beginTime, endTime, paginator, entCode);
    }

    /**
     * 查询配送员配置线路上的包装箱
     * @param operatorId
     * @param entCode
     * @return
     */
    @RequestMapping("/boxes/line/operatorId/{operatorId}/{entCode}")
    public List<BoxInfo> queryLineBoxes(@PathVariable String operatorId, @PathVariable String entCode) {
        return boxInfoService.queryLineBoxes(operatorId, entCode);
    }

    /**
     * 获取中转站点的待装车包装箱
     * @param operatorId
     * @param entCode
     * @return
     */
    @RequestMapping("/boxes/transfer/operatorId/{operatorId}/{entCode}")
    public List<BoxInfo> queryTransferBoxes(@PathVariable String operatorId, @PathVariable String entCode) {
        return boxInfoService.queryTransferBoxes(operatorId, entCode);
    }

    /**
     * 获取指定状态下包装箱对应的客户信息
     * @param listStatus
     * @param entCode
     * @return
     */
    @RequestMapping("/customs/query")
    public List<BoxesCustomInfoVO> queryCustomInfos(Byte[] listStatus, String entCode) {
        return boxInfoService.queryCustomInfos(Arrays.asList(listStatus), entCode);
    }

    /**
     * 通过配送员、状态查询包装箱信息
     * @param listStatus
     * @param operatorId
     * @param entCode
     * @return
     */
    @RequestMapping("/boxes/query/operatorId")
    public List<BoxInfoVO> queryBoxesByOperatorId(Byte[] listStatus, String operatorId, String entCode) {
        return boxInfoService.queryBoxesByOperatorId(Arrays.asList(listStatus), operatorId, entCode);
    }

    /**
     * 查询附近包装箱的数量
     * @param precision
     * @param lat
     * @param lon
     * @param status
     * @param entCode
     * @return
     */
    @RequestMapping("/boxes/nearby/count")
    public Long countNearbyBoxes(Double precision, Double lat, Double lon, Byte[] status, String entCode) {
        return boxInfoService.countNearbyBoxes(precision, lat, lon, Arrays.asList(status), entCode);
    }

    /**
     * 查询福建的包装箱
     * @param precision
     * @param lat
     * @param lon
     * @param status
     * @param curPage
     * @param pageSize
     * @param entCode
     * @return
     */
    @RequestMapping("/boxes/nearby/query")
    public List<BoxInfo> queryNearbyBoxes(Double precision,
                                   Double lat,
                                   Double lon,
                                   Byte[] status,
                                   Integer curPage,
                                   Integer pageSize,
                                   String entCode) {
        return boxInfoService.queryNearbyBoxes(precision, lat, lon, Arrays.asList(status), curPage, pageSize, entCode);
    }

    /**
     * 移除包装箱
     * @param boxId
     * @param entCode
     * @return
     */
    @RequestMapping("/box/remove/{boxId}/{entCode}")
    public boolean removeBoxInfo(@PathVariable String boxId, @PathVariable String entCode) {
        return boxInfoService.removeBoxInfo(boxId, entCode);
    }

    /**
     * 通过rfid更新包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    @RequestMapping("/update/rfid")
    public boolean updateBoxInfoByRfid(@RequestBody BoxInfo boxInfo, String entCode) {
        return boxInfoService.updateBoxInfoByRfid(boxInfo, entCode);
    }

    /**
     * 保存包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    @RequestMapping("/save")
    public boolean saveBoxInfo(@RequestBody BoxInfo boxInfo, String entCode) {
        return boxInfoService.saveBoxInfo(boxInfo, entCode);
    }

    /**
     * 通过订单ID更新包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    @RequestMapping("/update/orderId")
    public boolean updateBoxInfoByOrderId(@RequestBody BoxInfo boxInfo, String entCode) {
        return boxInfoService.updateBoxInfoByRfid(boxInfo, entCode);
    }

    /**
     * 获取包装箱型号
     * @param orderId
     * @return
     */
    @RequestMapping("/boxTypes/get/{orderId}")
    public List<BoxTypeInfo> getBoxTypes(@PathVariable String orderId) {
        return boxInfoService.getBoxTypes(orderId);
    }

    @RequestMapping("/transporting/count/{entCode}")
    public Long countTransportingBoxes(@PathVariable String entCode) {
        return boxInfoService.countTransportingBoxes(entCode);
    }

    @RequestMapping("/retention/count/{entCode}")
    public Long countRetentionBoxes(@PathVariable String entCode) {
        return boxInfoService.countRetentionBoxes(entCode);
    }

    @RequestMapping("/recycle/count/{entCode}")
    public Long countRecycleBoxes(@PathVariable String entCode) {
        return boxInfoService.countRecycleBoxes(entCode);
    }

    @RequestMapping("/leisure/count/{entCode}")
    public Long countLeisureBoxes(@PathVariable String entCode) {
        return boxInfoService.countLeisureBoxes(entCode);
    }

    @RequestMapping("/binding/count/{entCode}")
    public Long countBindingBoxes(@PathVariable String entCode) {
        return boxInfoService.countBindingBoxes(entCode);
    }

    @RequestMapping("/boxes/all/query/{entCode}")
    public List<BoxInfoVO> queryAllBoxes(@PathVariable String entCode) {
        return boxInfoService.queryAllBoxes(entCode);
    }

    @RequestMapping("/boxes/leisure/query/{entCode}")
    public List<BoxInfoVO> queryLeisureBoxes(@PathVariable String entCode) {
        return boxInfoService.queryLeisureBoxes(entCode);
    }

    @RequestMapping("/operators/query")
    public List<BoxesOperatorInfoVO> queryOperators(Byte[] listStatus, String entCode) {
        return boxInfoService.queryOperators(Arrays.asList(listStatus), entCode);
    }

    @RequestMapping("/boxes/query/customId")
    public List<BoxInfoVO> queryBoxesByCustomId(Byte[] listStatus, String customId, String entCode) {
        return boxInfoService.queryBoxesByCustomId(Arrays.asList(listStatus), customId, entCode);
    }

    @RequestMapping("/boxes/query/status")
    public List<BoxInfoVO> queryBoxesByStatus(Byte[] listStatus, String entCode) {
        return boxInfoService.queryBoxesByStatus(Arrays.asList(listStatus), entCode);
    }

    @RequestMapping("/box/delete/{rfid}/{entCode}")
    public boolean deleteBox(@PathVariable("rfid") String rfid,
                             @PathVariable("entCode") String entCode) {
        return boxInfoService.deleteBox(rfid, entCode);
    }
}
