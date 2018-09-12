package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.common.vo.web.BoxFlowRecordVO;
import com.izhuixin.rsps.domain.automatic.OrderDriverDO;
import com.izhuixin.rsps.domain.manual.OrderDriverInfo;

public interface OrderDriverService extends CrudService<OrderDriverDO> {

    OrderDriverInfo queryNextDriver(String driverId, String orderId, String entCode);

    Integer countOrderDriver(String orderId, String driverId, String entCode);

    boolean saveOrderDriver(OrderDriverInfo orderDriverInfo, String entCode);

    boolean updateByOrderIdAndDriverId(OrderDriverInfo orderDriverInfo, String entCode);

    BoxFlowRecordVO getFlowRecord(String orderId);
}
