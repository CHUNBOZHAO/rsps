package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.common.*;
import com.izhuixin.rsps.domain.BoxInfoDto;
import com.izhuixin.rsps.domain.OrderInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "api-service")
@RequestMapping("v1/orderData/order")
public interface OrderService {

    @RequestMapping("/get/id/{orderId}/{entCode}")
    OrderInfo getOrderById(@PathVariable(value="orderId") String orderId, @PathVariable(value="entCode") String entCode);

    @RequestMapping("/orders/ids")
    Map<String, OrderInfo> getOrdersByIds(@RequestParam("orderIds") List<String> orderIds, @RequestParam("entCode") String entCode);

    @RequestMapping("/order/count/id/{orderId}/{entCode}")
    Integer countOrderById(@PathVariable("orderId") String orderId, @PathVariable("entCode") String entCode);

    @RequestMapping("/save")
    boolean saveOrder(@RequestBody OrderInfo orderInfo, @RequestParam("entCode") String entCode);

    @RequestMapping("/create")
    String createOrder(@RequestBody OrderInfoVO orderInfoVO);

    @RequestMapping("/takeIn/handle")
    boolean handleTakeIn(@RequestParam("orderId") String orderId, @RequestParam("longitude") Double longitude, @RequestParam("latitude") Double latitude);

    @RequestMapping("/get/{orderId}")
    OrderInfoVO getOrder(@PathVariable("orderId") String orderId);

    @RequestMapping("/takeOrders/get")
    List<OrderInfo> getTakeOrders(@RequestParam("operatorId") String operatorId, @RequestParam("entCode") String entCode);

    @RequestMapping("/update/orderId")
    boolean updateOrder(@RequestBody OrderInfo orderInfo, @RequestParam("orderId") String orderId, @RequestParam("entCode") String entCode);

    @RequestMapping("/update")
    boolean updateOrder(@RequestBody OrderInfoVO orderInfoVO);

    @RequestMapping("/currentBoxes/get")
    BoxInfoDto getCustomCurrentBoxes(@RequestParam("customId") String customId, @RequestBody Paginator paginator);

    @RequestMapping("/historyBoxes/get")
    BoxInfoDto getCustomHistoryBoxes(@RequestParam("customId") String customId, @RequestBody Paginator paginator);

    @RequestMapping("/boxFlowInfo/get")
    List<BoxFlowRecordVO> getBoxFlowInfo(@RequestParam("boxId") String boxId, @RequestParam("orderId") String orderId);

    @RequestMapping("/boxDetail/get")
    List<DrugInfoVO> getBoxDetail(@RequestParam("boxId") String boxId, @RequestParam("orderId") String orderId, @RequestParam("orderType") Byte orderType);

    @RequestMapping("/currentBoxes/get/orderId/{orderId}/{customId}")
    List<BoxInfoVO> getCustomCurrentBoxesWithOrderId(@PathVariable("customId") String customId, @PathVariable("orderId") String orderId);

    @RequestMapping("/historyBoxes/get/orderId/{orderId}/{customId}")
    List<BoxInfoVO> getCustomHistoryBoxesWithOrderId(@PathVariable("customId") String customId, @PathVariable("orderId") String orderId);

//    List<OrderInfo> getUndoOrders(String customId);

    @RequestMapping("/currentBoxes/get/other")
    List<BoxInfoVO> getCustomerCurrentBoxes(@RequestParam("customId") String customId, @RequestParam(value = "tel", defaultValue = "") String tel, @RequestParam(value = "orderId", defaultValue = "") String orderId);

    @RequestMapping("/historyBoxes/get/other")
    List<BoxInfoVO> getCustomerHistoryBoxes(@RequestParam("customId") String customId, @RequestParam(value = "tel", defaultValue = "") String tel, @RequestParam(value = "orderId", defaultValue = "") String orderId);

    @RequestMapping("/myBoxes/get")
    List<BoxInfoVO> getMyBoxes(@RequestParam("customId") String customId, @RequestParam(value = "tel", defaultValue = "") String tel);

    @RequestMapping("/myBoxes/count")
    Integer getMyBoxesCount(@RequestParam("customId") String customId, @RequestParam(value = "tel", defaultValue = "") String tel);

    @RequestMapping("/delete/{orderId}")
    boolean deleteOrder(@PathVariable("orderId") String orderId);

    @RequestMapping("/orderState/get/{orderId}")
    byte getOrderState(@PathVariable("orderId") String orderId);

    @RequestMapping("/orderState/update/{orderId}/{orderState}")
    void updateOrderState(@PathVariable("orderId") String orderId, @PathVariable("orderState") byte orderState);
}
