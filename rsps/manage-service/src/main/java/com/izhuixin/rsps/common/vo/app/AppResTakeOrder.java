package com.izhuixin.rsps.common.vo.app;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 揽货订单
 */
public class AppResTakeOrder extends AppResBase {

    private List<AppTakeOrderInfo> orders = Lists.newArrayList();

    public List<AppTakeOrderInfo> getOrders() {
        return orders;
    }

    public void setOrders(List<AppTakeOrderInfo> orders) {
        this.orders = orders;
    }
}
