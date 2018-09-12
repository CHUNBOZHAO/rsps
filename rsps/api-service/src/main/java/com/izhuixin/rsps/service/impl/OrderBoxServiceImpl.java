package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.dao.manual.BoxInfoDao;
import com.izhuixin.rsps.dao.manual.OperatorInfoDao;
import com.izhuixin.rsps.dao.manual.OrderBoxDao;
import com.izhuixin.rsps.domain.automatic.OrderBoxDO;
import com.izhuixin.rsps.domain.manual.BoxInfo;
import com.izhuixin.rsps.domain.manual.OperatorInfo;
import com.izhuixin.rsps.domain.manual.OrderBoxInfo;
import com.izhuixin.rsps.service.OrderBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderBoxServiceImpl extends AbstractCrudService<OrderBoxDO> implements OrderBoxService {

    @Autowired
    private OrderBoxDao orderBoxDao;

    @Autowired
    private OperatorInfoDao operatorInfoDao;

    @Autowired
    private BoxInfoDao boxInfoDao;

    /***
     * 获取箱子基本信息
     * @param orderId
     * @param rfid
     * @return
     */
    @Override
    public OrderBoxInfo getBoxInfo(String orderId, String rfid, String entCode) {
        OrderBoxInfo orderBoxInfo = null;
        try {
            orderBoxInfo = orderBoxDao.getInfoByOrderIdAndRfid(orderId, rfid, entCode);
        } catch (Exception e) {
            logger.error(String.format("通过订单（%s）包装箱rfid（%s）获取包裹信息出现异常", orderId, rfid), e);
        }
        return orderBoxInfo;
    }

    /**
     * 通过条件获取数据数量
     * @param rfid
     * @param barcode
     * @param entCode
     * @return
     */
    @Override
    public Integer countOrderBoxByRfidAndBarCode(String rfid, String barcode, String entCode) {
        Integer count = 0;
        try {
            count = orderBoxDao.countInfoByRfidAndBarcode(rfid, barcode, entCode);
        } catch (Exception e) {
            logger.error(String.format("通过rfid(%s)、barcode(%s)获取订单-包装箱关联信息出现异常", rfid, barcode), e);
        }
        return count;
    }

    /**
     * 更新订单-包装箱关联信息（通过rfid, barcode）
     * @param orderBoxInfo
     * @param entCode
     * @return
     */
    @Override
    public boolean updateOrderBox(OrderBoxInfo orderBoxInfo, String entCode) {
        boolean res = false;
        String orderId = "";
        String rfid = "";
        try {
            orderId = orderBoxInfo.getOrderId();
            rfid = orderBoxInfo.getRfid();
            Integer updateRes = orderBoxDao.updateInfo(orderBoxInfo, entCode);
            if (updateRes < 1) {
                logger.warn(String.format("更新企业(%s)订单(%s)-包装箱(%s)关联记录表失败", entCode, orderId, rfid));
            } else {
                res = true;
            }
        } catch (Exception e) {
            logger.error(String.format("更新企业(%s)订单(%s)-包装箱(%s)关联记录表出现异常", entCode, orderId, rfid));
        }
        return res;
    }

    /**
     * 保存订单包装
     * @param orderBoxInfo
     * @param entCode
     * @return
     */
    @Override
    public boolean saveOrderBox(OrderBoxInfo orderBoxInfo, String entCode) {
        boolean res = false;
        String orderId = "";
        String rfid = "";
        try {
            orderId = orderBoxInfo.getOrderId();
            rfid = orderBoxInfo.getRfid();
            Integer saveRes = orderBoxDao.saveInfo(orderBoxInfo, entCode);
            if (saveRes < 1) {
                logger.warn(String.format("保存企业(%s)订单(%s)-包装箱(%s)关联记录表失败", entCode, orderId, rfid));
            } else {
                res = true;
            }
        } catch (Exception e) {
            logger.error(String.format("保存企业(%s)订单(%s)-包装箱(%s)关联记录表出现异常", entCode, orderId, rfid));
        }
        return res;
    }

    /**
     * 更新包装箱责任人（施必达提供）
     * @param orderId
     * @param operatorNo
     * @return
     */
    @Override
    public boolean updateBoxOperator(String orderId, String operatorNo, String entCode) {
        boolean res = false;

        try {
            OperatorInfo operatorInfo = operatorInfoDao.getOperatorByNo(operatorNo, entCode);
            if (operatorInfo != null) {
                BoxInfo boxInfo = new BoxInfo();
                boxInfo.setOperatorId(operatorInfo.getOperatorId());
                boxInfo.setOperator(operatorInfo.getUserName());
                boxInfo.setOrderId(orderId);
                boxInfo.setNextOperatorId(operatorInfo.getOperatorId());
                boxInfoDao.updateBoxInfoByOrderId(boxInfo, entCode);
                res = true;
            }
        } catch (Exception e) {
            logger.error("更新包装箱配送员信息出现异常", e);
        }
        return res;
    }

}
