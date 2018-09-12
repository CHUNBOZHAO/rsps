package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.domain.automatic.OrderBoxDO;
import com.izhuixin.rsps.domain.manual.OrderBoxInfo;

public interface OrderBoxService extends CrudService<OrderBoxDO> {

    OrderBoxInfo getBoxInfo(String orderId, String rfid, String entCode);

    Integer countOrderBoxByRfidAndBarCode(String rfid, String barcode, String entCode);

    boolean updateOrderBox(OrderBoxInfo orderBoxInfo, String entCode);

    boolean saveOrderBox(OrderBoxInfo orderBoxInfo, String entCode);

    boolean updateBoxOperator(String orderId, String operatorNo, String entCode);
}
