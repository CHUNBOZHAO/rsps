package com.izhuixin.rsps.service;

public interface OrderPolicyService {

    void obtainOrder(String orderId, Double longitude, Double latitude);

    void deliveryOrder(String orderId, String entCode, String boxId);
}
