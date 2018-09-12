package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.common.dto.BoxInfoDto;
import com.izhuixin.rsps.common.page.Paginator;
import com.izhuixin.rsps.common.vo.web.BoxFlowRecordVO;
import com.izhuixin.rsps.common.vo.web.BoxInfoVO;
import com.izhuixin.rsps.common.vo.web.DrugInfoVO;
import com.izhuixin.rsps.common.vo.web.OrderInfoVO;
import com.izhuixin.rsps.domain.automatic.OrderDO;
import com.izhuixin.rsps.domain.manual.OrderInfo;

import java.util.List;
import java.util.Map;

public interface OrderService extends CrudService<OrderDO> {

    OrderInfo getOrderById(String orderId, String entCode);

    Map<String, OrderInfo> getOrdersByIds(List<String> orderIds, String entCode);

    Integer countOrderById(String orderId, String entCode);

    boolean saveOrder(OrderInfo orderInfo, String entCode);

    String createOrder(OrderInfoVO orderInfoVO);

    boolean handleTakeIn(String orderId, Double longitude, Double latitude);

    OrderInfoVO getOrder(String orderId);

    List<OrderInfo> getTakeOrders(String operatorId, String entCode);

    boolean updateOrder(OrderInfo orderInfo, String orderId, String entCode);

    boolean updateOrder(OrderInfoVO orderInfoVO);

    BoxInfoDto getCustomCurrentBoxes(String customId, Paginator paginator);

    BoxInfoDto getCustomHistoryBoxes(String customId, Paginator paginator);

    List<BoxFlowRecordVO> getBoxFlowInfo(String boxId, String orderId);

    List<DrugInfoVO> getBoxDetail(String boxId, String orderId, Byte orderType);

    List<BoxInfoVO> getCustomCurrentBoxesWithOrderId(String customId, String orderId);

    List<BoxInfoVO> getCustomHistoryBoxesWithOrderId(String customId, String orderId);

//    List<OrderInfo> getUndoOrders(String customId);

    List<BoxInfoVO> getCustomerCurrentBoxes(String customId, String tel, String orderId);

    List<BoxInfoVO> getCustomerHistoryBoxes(String customId, String tel, String orderId);

    List<BoxInfoVO> getMyBoxes(String customId, String tel);

    Integer getMyBoxesCount(String customId, String tel);

    boolean deleteOrder(String orderId);

    byte getOrderState(String orderId);

    void updateOrderState(String orderId, byte orderState);

//    void getOrdersByCustomerId(String linkerId, Paginator paginator, String entCode);
}
