package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.dto.BoxInfoDto;
import com.izhuixin.rsps.common.page.Paginator;
import com.izhuixin.rsps.common.vo.web.BoxFlowRecordVO;
import com.izhuixin.rsps.common.vo.web.BoxInfoVO;
import com.izhuixin.rsps.common.vo.web.DrugInfoVO;
import com.izhuixin.rsps.common.vo.web.OrderInfoVO;
import com.izhuixin.rsps.domain.manual.OrderInfo;
import com.izhuixin.rsps.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/orderData/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/get/id/{orderId}/{entCode}")
    public OrderInfo getOrderById(@PathVariable String orderId, @PathVariable String entCode) {
        return orderService.getOrderById(orderId, entCode);
    }

    @RequestMapping("/orders/ids")
    public Map<String, OrderInfo> getOrdersByIds(String[] orderIds, String entCode) {
        return orderService.getOrdersByIds(Arrays.asList(orderIds), entCode);
    }

    @RequestMapping("/order/count/id/{orderId}/{entCode}")
    public Integer countOrderById(@PathVariable String orderId, @PathVariable String entCode) {
        return orderService.countOrderById(orderId, entCode);
    }

    @RequestMapping("/save")
    public boolean saveOrder(@RequestBody OrderInfo orderInfo, String entCode) {
        return orderService.saveOrder(orderInfo, entCode);
    }

    @RequestMapping("/create")
    public String createOrder(@RequestBody OrderInfoVO orderInfoVO) {
        return orderService.createOrder(orderInfoVO);
    }

    @RequestMapping("/takeIn/handle")
    public boolean handleTakeIn(String orderId, Double longitude, Double latitude) {
        return orderService.handleTakeIn(orderId, longitude, latitude);
    }

    @RequestMapping("/get/{orderId}")
    public OrderInfoVO getOrder(@PathVariable String orderId) {
        return orderService.getOrder(orderId);
    }

    @RequestMapping("/takeOrders/get/{operatorId}/{entCode}")
    public List<OrderInfo> getTakeOrders(@PathVariable String operatorId, @PathVariable String entCode) {
        return orderService.getTakeOrders(operatorId, entCode);
    }

    @RequestMapping("/update/orderId")
    public boolean updateOrder(@RequestBody OrderInfo orderInfo, String orderId, String entCode) {
        return orderService.updateOrder(orderInfo, orderId, entCode);
    }

    @RequestMapping("/update")
    public boolean updateOrder(@RequestBody OrderInfoVO orderInfoVO) {
        return orderService.updateOrder(orderInfoVO);
    }

    @RequestMapping("/currentBoxes/get")
    public BoxInfoDto getCustomCurrentBoxes(String customId, Paginator paginator) {
        return orderService.getCustomCurrentBoxes(customId, paginator);
    }

    @RequestMapping("/historyBoxes/get")
    public BoxInfoDto getCustomHistoryBoxes(String customId, Paginator paginator) {
        return orderService.getCustomHistoryBoxes(customId, paginator);
    }

    @RequestMapping("/boxFlowInfo/get")
    public List<BoxFlowRecordVO> getBoxFlowInfo(String boxId, String orderId) {
        return orderService.getBoxFlowInfo(boxId, orderId);
    }

    @RequestMapping("/boxDetail/get")
    public List<DrugInfoVO> getBoxDetail(String boxId, String orderId, Byte orderType) {
        return orderService.getBoxDetail(boxId, orderId, orderType);
    }

    @RequestMapping("/currentBoxes/get/orderId/{orderId}/{customId}")
    public List<BoxInfoVO> getCustomCurrentBoxesWithOrderId(@PathVariable String customId, @PathVariable String orderId) {
        return orderService.getCustomCurrentBoxesWithOrderId(customId, orderId);
    }

    @RequestMapping("/historyBoxes/get/orderId/{orderId}/{customId}")
    public List<BoxInfoVO> getCustomHistoryBoxesWithOrderId(@PathVariable String customId, @PathVariable String orderId) {
        return orderService.getCustomHistoryBoxesWithOrderId(customId, orderId);
    }

    @RequestMapping("/currentBoxes/get/other")
    public List<BoxInfoVO> getCustomerCurrentBoxes(@RequestParam("customId") String customId,
                                                   @RequestParam("tel") String tel,
                                                   @RequestParam("orderId") String orderId) {
        return orderService.getCustomerCurrentBoxes(customId, tel, orderId);
    }

    @RequestMapping("/historyBoxes/get/other")
    public List<BoxInfoVO> getCustomerHistoryBoxes(@RequestParam("customId") String customId,
                                                   @RequestParam("tel") String tel,
                                                   @RequestParam("orderId") String orderId) {
        return orderService.getCustomerHistoryBoxes(customId, tel, orderId);
    }

    @RequestMapping("/myBoxes/get")
    public List<BoxInfoVO> getMyBoxes(@RequestParam("customId") String customId, @RequestParam("tel") String tel) {
        return orderService.getMyBoxes(customId, tel);
    }

    @RequestMapping("/myBoxes/count")
    public Integer getMyBoxesCount(@RequestParam("customId") String customId, @RequestParam("tel") String tel) {
        return orderService.getMyBoxesCount(customId, tel);
    }

    @RequestMapping("/delete/{orderId}")
    public boolean deleteOrder(@PathVariable String orderId) {
        return orderService.deleteOrder(orderId);
    }

    @RequestMapping("/orderState/get/{orderId}")
    public byte getOrderState(@PathVariable String orderId) {
        return orderService.getOrderState(orderId);
    }

    @RequestMapping("/orderState/update/{orderId}/{orderState}")
    public void updateOrderState(@PathVariable String orderId, @PathVariable byte orderState) {
        orderService.updateOrderState(orderId, orderState);
    }

}
