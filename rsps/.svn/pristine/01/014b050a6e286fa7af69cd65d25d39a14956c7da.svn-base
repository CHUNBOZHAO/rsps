package com.izhuixin.rsps.service.impl;

import com.google.common.base.Optional;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.common.vo.web.BoxFlowRecordVO;
import com.izhuixin.rsps.dao.manual.OrderDriverDao;
import com.izhuixin.rsps.domain.automatic.OrderDriverDO;
import com.izhuixin.rsps.domain.manual.OrderDriverInfo;
import com.izhuixin.rsps.service.OrderDriverService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDriverServiceImpl extends AbstractCrudService<OrderDriverDO> implements OrderDriverService {

    @Autowired
    private OrderDriverDao orderDriverDao;


    /***
     * 获取下一个中转信息
     * @param driverId
     * @param orderId
     * @return
     */
    @Override
    public OrderDriverInfo queryNextDriver(String driverId, String orderId, String entCode) {
        OrderDriverInfo nextOrderDriver = null;
        try {
            OrderDriverInfo orderDriverInfo = orderDriverDao.getInfoByDriverAndOrderId(driverId, orderId, entCode);
            if (orderDriverInfo != null && StringUtils.isNotBlank(orderDriverInfo.getNextDriverId())) {
                nextOrderDriver = orderDriverDao.getInfoByDriverAndOrderId(orderDriverInfo.getNextDriverId(), orderId, entCode);
            }
        } catch (Exception e) {
            logger.error(String.format("通过订单（%s）和司机（%s）获取下一个中转信息出现异常", orderId, driverId), e);
        }
        return nextOrderDriver;
    }

    /**
     * 获取订单配送记录数量
     * @param orderId
     * @param driverId
     * @param entCode
     * @return
     */
    @Override
    public Integer countOrderDriver(String orderId, String driverId, String entCode) {
        Integer count = 0;
        try {
            count = orderDriverDao.countOrderDriverInfo(orderId, driverId, entCode);
        } catch (Exception e) {
            logger.error(String.format("获取订单(%s)配送(%s)信息数量", orderId, driverId), e);
        }
        return count;
    }

    /**
     * 保存订单配送信息
     * @param orderDriverInfo
     * @param entCode
     * @return
     */
    @Override
    public boolean saveOrderDriver(OrderDriverInfo orderDriverInfo, String entCode) {
        boolean res = false;
        try {
            orderDriverDao.saveOrderDriverInfo(orderDriverInfo, entCode);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("保存订单配送信息出现异常"), e);
        }
        return res;
    }

    /**
     * 更新订单配送信息
     * @param orderDriverInfo
     * @param entCode
     * @return
     */
    @Override
    public boolean updateByOrderIdAndDriverId(OrderDriverInfo orderDriverInfo, String entCode) {
        boolean res = false;
        try {
            orderDriverDao.updateByOrderIdAndDriverId(orderDriverInfo, entCode);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("保存订单配送信息出现异常"), e);
        }
        return res;
    }

    /**
     * 获取揽货记录
     * @param orderId
     * @return
     */
    @Override
    public BoxFlowRecordVO getFlowRecord(String orderId) {
        BoxFlowRecordVO boxFlowRecordVO = null;
        try {
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("order_id", orderId).andFieldEqualTo("state", 0);
            Optional<OrderDriverDO> orderDriverDOOptional = get(fe);
            if (orderDriverDOOptional.isPresent()) {
                OrderDriverDO orderDriverDO = orderDriverDOOptional.get();
                boxFlowRecordVO = new BoxFlowRecordVO();
                boxFlowRecordVO.setCreateTime(orderDriverDO.getCreateTime());
                boxFlowRecordVO.setOperateType((byte) 20); // 揽货成功
                boxFlowRecordVO.setOperator(orderDriverDO.getDriverName());
                boxFlowRecordVO.setRfid("");
                boxFlowRecordVO.setOrderId(orderId);
                boxFlowRecordVO.setExceptionType((byte) 0);
            }
        } catch (Exception e) {
            logger.error(String.format("获取订单(%s)揽货记录出现异常", orderId), e);
        }
        return boxFlowRecordVO;
    }


//    /***
//     * 获取下一个中转信息
//     * @param driverId
//     * @param orderId
//     * @return
//     */
//    @Override
//    public OrderDriverDO queryNextDriver(String driverId, String orderId) {
//        OrderDriverDO nextOrderDriver = null;
//        FilterExample fe = new FilterExample();
//        fe.createCriteria().andFieldEqualTo("driver_id", driverId).andFieldEqualTo("order_id", orderId);
//
//        try {
//            Optional<OrderDriverDO> optional = get(fe);
//            if (optional.isPresent()) {
//                if (StringUtils.isNotBlank(optional.get().getNextDriverId())) {
//                    fe.clear();
//                    fe.createCriteria().andFieldEqualTo("driver_id", optional.get().getNextDriverId()).andFieldEqualTo("order_id", orderId);
//                    Optional<OrderDriverDO> optional1 = get(fe);
//                    if (optional1.isPresent()) {
//                        nextOrderDriver = optional1.get();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            logger.error(String.format("通过订单（%s）和司机（%s）获取下一个中转信息出现异常", orderId, driverId), e);
//        }
//
//        return nextOrderDriver;
//    }

}
