package com.izhuixin.rsps.service;

import com.izhuixin.rsps.domain.OrderSource;

public interface OrderSourceService {

    /**
     * 保存图片
     */
    boolean saveOrderSource(OrderSource orderSource);

    /**
     * 根据订单id获取图片
     * @param orderId
     * @return
     */
    OrderSource getOrderSourceByOrderId(String orderId);

}
