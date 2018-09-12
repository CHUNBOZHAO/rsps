package com.izhuixin.rsps.common.vo.app;

import java.util.List;

/***
 * 返回的包装箱信息
 */
public class AppResOrders extends AppResBase {

    private List<AppResBox> orders;

    public List<AppResBox> getOrders() {
        return orders;
    }

    public void setOrders(List<AppResBox> orders) {
        this.orders = orders;
    }
}
