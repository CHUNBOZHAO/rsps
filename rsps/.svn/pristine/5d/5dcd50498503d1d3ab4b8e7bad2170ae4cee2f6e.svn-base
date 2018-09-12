package com.izhuixin.rsps.service;

import com.izhuixin.rsps.domain.Order;
import com.izhuixin.rsps.domain.SignInfo;

import java.util.List;

public interface OrderMsgService {
    /**
     * 根据司机编号获取未签收的订单信息
     */
    List<Order> getAllOrderByMotorId(String motorId);

    /**
     * 保存订单信息
     */
    boolean saveOrder(Order order);

    /**
     * 更新订单签收状态
     */
     boolean updateOrderStatus(String orderId);

    /**
     * 根据订单编号删除订单
     */
    boolean deleteOrderByOrderId(String orderId);

    /**
     * 发送订单签收信息
     */
    boolean sendOrder(SignInfo signInfo) throws Exception;

    /**
     * 更新订单
     */
    boolean updateOrder(Order order);
}
