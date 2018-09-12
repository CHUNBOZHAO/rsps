package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dao.BoxInfo;
import com.izhuixin.rsps.common.dao.BoxTypeInfo;
import com.izhuixin.rsps.common.page.Paginator;
import com.izhuixin.rsps.common.vo.web.BoxInfoVO;
import com.izhuixin.rsps.common.vo.web.BoxesCustomInfoVO;
import com.izhuixin.rsps.common.vo.web.BoxesOperatorInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "api-service")
@RequestMapping("/v1/boxData/boxInfo")
public interface BoxInfoService {

    /**
     * 通过rfid获取包装箱信息
     * @param rfid
     * @param entCode
     * @return
     */
    @RequestMapping("/get/rfid/{rfid}/{entCode}")
    BoxInfo getBoxInfoByRfid(@PathVariable("rfid") String rfid, @PathVariable("entCode") String entCode);

    /**
     * 通过订单ID获取包装箱信息
     * @param orderId
     * @param entCode
     * @return
     */
    @RequestMapping("/get/orderId/{orderId}/entCode")
    List<BoxInfo> getBoxInfoByOrderid(@PathVariable("orderId") String orderId, @PathVariable("entCode") String entCode);

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
    List<BoxInfo> queryBoxes(@RequestParam("operatorId") String operatorId,
                             @RequestParam("boxStatus") List<Byte> boxStatus,
                             @RequestParam("beginTime") Long beginTime,
                             @RequestParam("endTime") Long endTime,
                             @RequestParam("paginator") Paginator paginator,
                             @RequestParam("entCode") String entCode);

    /**
     * 查询配送员配置线路上的包装箱
     * @param operatorId
     * @param entCode
     * @return
     */
    @RequestMapping("/boxes/line/operatorId/{operatorId}/{entCode}")
    List<BoxInfo> queryLineBoxes(@PathVariable("operatorId") String operatorId,
                                 @PathVariable("entCode") String entCode);

    /**
     * 获取中转站点的待装车包装箱
     * @param operatorId
     * @param entCode
     * @return
     */
    @RequestMapping("/boxes/transfer/operatorId/{operatorId}/{entCode}")
    List<BoxInfo> queryTransferBoxes(@PathVariable("operatorId") String operatorId,
                                     @PathVariable("entCode") String entCode);

    /**
     * 获取指定状态下包装箱对应的客户信息
     * @param listStatus
     * @param entCode
     * @return
     */
    @RequestMapping("/customs/query")
    List<BoxesCustomInfoVO> queryCustomInfos(@RequestParam("listStatus") List<Byte> listStatus,
                                             @RequestParam("entCode") String entCode);

    /**
     * 通过配送员、状态查询包装箱信息
     * @param listStatus
     * @param operatorId
     * @param entCode
     * @return
     */
    @RequestMapping("/boxes/query/operatorId")
    List<BoxInfoVO> queryBoxesByOperatorId(@RequestParam("listStatus") List<Byte> listStatus,
                                           @RequestParam("operatorId") String operatorId,
                                           @RequestParam("entCode") String entCode);

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
    Long countNearbyBoxes(@RequestParam("precision") Double precision,
                          @RequestParam("lat")Double lat,
                          @RequestParam("lon")Double lon,
                          @RequestParam("status")List<Byte> status,
                          @RequestParam("entCode")String entCode);

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
    List<BoxInfo> queryNearbyBoxes(@RequestParam("precision") Double precision,
                                   @RequestParam("lat") Double lat,
                                   @RequestParam("lon") Double lon,
                                   @RequestParam("status") List<Byte> status,
                                   @RequestParam("curPage") Integer curPage,
                                   @RequestParam("pageSize") Integer pageSize,
                                   @RequestParam("entCode") String entCode);

    /**
     * 移除包装箱
     * @param boxId
     * @param entCode
     * @return
     */
    @RequestMapping("/box/remove/{boxId}/{entCode}")
    boolean removeBoxInfo(@PathVariable("boxId") String boxId,
                          @PathVariable("entCode") String entCode);

    /**
     * 通过rfid更新包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    @RequestMapping("/update/rfid")
    boolean updateBoxInfoByRfid(@RequestBody BoxInfo boxInfo,
                                @RequestParam("entCode") String entCode);

    /**
     * 保存包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    @RequestMapping("/save")
    boolean saveBoxInfo(@RequestBody BoxInfo boxInfo,
                        @RequestParam("entCode") String entCode);

    /**
     * 通过订单ID更新包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    @RequestMapping("/update/orderId")
    boolean updateBoxInfoByOrderId(@PathVariable("boxInfo") BoxInfo boxInfo,
                                   @PathVariable("entCode") String entCode);

    /**
     * 获取包装箱型号
     * @param orderId
     * @return
     */
    @RequestMapping("/boxTypes/get/{orderId}")
    List<BoxTypeInfo> getBoxTypes(@PathVariable("orderId") String orderId);

    /**
     * 获取运输状态下包装箱数量
     * @param entCode
     * @return
     */
    @GetMapping("/transporting/count/{entCode}")
    Long countTransportingBoxes(@PathVariable("entCode") String entCode);

    /**
     * 获取滞留状态下包装箱数量
     * @param entCode
     * @return
     */
    @GetMapping("/retention/count/{entCode}")
    Long countRetentionBoxes(@PathVariable("entCode") String entCode);

    /**
     * 获取回收状态下包装箱数量
     * @param entCode
     * @return
     */
    @GetMapping("/recycle/count/{entCode}")
    Long countRecycleBoxes(@PathVariable("entCode") String entCode);

    /**
     * 获取闲置状态下包装箱数量
     * @param entCode
     * @return
     */
    @GetMapping("/leisure/count/{entCode}")
    Long countLeisureBoxes(@PathVariable("entCode") String entCode);

    /**
     * 获取绑定状态下包装箱数量
     * @param entCode
     * @return
     */
    @GetMapping("/binding/count/{entCode}")
    Long countBindingBoxes(@PathVariable("entCode") String entCode);


    @GetMapping("/boxes/all/query/{entCode}")
    List<BoxInfoVO> queryAllBoxes(@PathVariable("entCode") String entCode);

    @GetMapping("/boxes/leisure/query/{entCode}")
    List<BoxInfoVO> queryLeisureBoxes(@PathVariable("entCode") String entCode);

    @GetMapping("/operators/query")
    List<BoxesOperatorInfoVO> queryOperators(@RequestParam("listStatus") List<Byte> listStatus,
                                             @RequestParam("entCode") String entCode);

    @GetMapping("/boxes/query/customId")
    List<BoxInfoVO> queryBoxesByCustomId(@RequestParam("listStatus") List<Byte> listStatus,
                                         @RequestParam("customId") String customId,
                                         @RequestParam("entCode") String entCode);

    @GetMapping("/boxes/query/status")
    List<BoxInfoVO> queryBoxesByStatus(@RequestParam("listStatus") List<Byte> listStatus,
                                       @RequestParam("entCode") String entCode);

    @GetMapping("/box/delete/{rfid}/{entCode}")
    boolean deleteBox(@PathVariable("rfid") String rfid,
                      @PathVariable("entCode") String entCode);
}
