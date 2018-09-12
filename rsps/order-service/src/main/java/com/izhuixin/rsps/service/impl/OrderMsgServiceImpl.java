package com.izhuixin.rsps.service.impl;


import com.izhuixin.rsps.OrderService;
import com.izhuixin.rsps.constants.CommonOrderRefuseReason;
import com.izhuixin.rsps.constants.CommonOrderStatus;
import com.izhuixin.rsps.dao.OrderDao;
import com.izhuixin.rsps.domain.OrderSource;
import com.izhuixin.rsps.message.OutputMsg;
import com.izhuixin.rsps.domain.Order;
import com.izhuixin.rsps.domain.SignInfo;
import com.izhuixin.rsps.service.OrderMsgService;
import com.izhuixin.rsps.service.feign.ApiFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;

@Service
@EnableBinding(OutputMsg.class)
public class OrderMsgServiceImpl implements OrderMsgService {


    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private ApiFeignService apiFeignService;

    private Logger logger = LoggerFactory.getLogger(OrderService.class);
    /**
     *创建一个新的消息通道发送消息
     */
    @Autowired
    @Qualifier(OutputMsg.OUTPUT_ORDER)
    MessageChannel output_order;

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> getAllOrderByMotorId(String motorId) {
        return orderDao.getAllOrderByMotorId(motorId);
    }

    @Override
    public boolean saveOrder(Order order) {

        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                Boolean res = false;

                try{
                    if(orderDao.checkOrder(order.getJobno(),order.getEirNO())<1){
                        orderDao.saveOrder(order);
                        res = true;
//                        logger.info("保存成功");
                    }else{
                        res  = false;
                    }
                    //更新包装箱责任人
                    boolean m = apiFeignService.updateBoxOperator(order.getOrderNO(),order.getEXTCOL1(),order.getEntCode().concat("_"));
//                    logger.error(m+"****"+order.getOrderNO()+order.getEXTCOL1()+order.getEntCode());
                }catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    e.printStackTrace();
                }
                return res;
            }
        });
//        if(orderDao.checkOrder(order.getJobno(),order.getOrderNO())<=1){
//            try{
//                orderDao.saveOrder(order);
//            }catch (Exception e){
//                e.printStackTrace();
//                b = false;
//            }
//        }
//        return b;
    }

    @Override
    public boolean updateOrderStatus(String orderId) {

        Integer orderStatus = CommonOrderStatus.SIGN.getIndex();

        return orderDao.updateOrderStatus(orderStatus,orderId);
    }
    @Override
    public boolean deleteOrderByOrderId(String orderId) {
        return orderDao.deleteOrderByOrderId(orderId);
    }

    @Override
    public boolean sendOrder(SignInfo signInfo) throws Exception{

        boolean flag = false;
        boolean b = false;
        if (signInfo != null) {

            //更新订单状态
            Integer orderStatus = CommonOrderStatus.SIGN.getIndex();
            Order order = new Order();
            order.setRefusedCount(signInfo.getEXTCOL2());
            order.setRefusedReason(signInfo.getEXTCOL1());
            if(Integer.parseInt(signInfo.getEXTCOL2())>0){
                orderStatus = CommonOrderStatus.UNSIGN.getIndex();
            }
            order.setOrderNO(signInfo.getCustJobno());
            order.setOrderStatus(orderStatus);
            order.setSignDate(new Date());
            order.setEirNO(signInfo.getEirNo());
            order.setJobno(signInfo.getJobno());
            System.out.println(signInfo.getEirNo()+signInfo.getJobno());
            if(signInfo.getEirNo()!=null&&signInfo.getJobno()!=null){
                b = orderDao.updateOrder(order);
            }else {
                b = false;
            }

            //发送签收信息
            signInfo.setEXTCOL1(CommonOrderRefuseReason.getdesc(Integer.parseInt(signInfo.getEXTCOL1())));
            boolean flag1 = output_order.send(MessageBuilder.withPayload(signInfo).build());
            if(flag1 = true){
                logger.info("发送消息队列成功");
            }

            if (flag1&&b) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public boolean updateOrder(Order order) {
        return orderDao.updateOrder(order);
    }
}
