package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.common.constant.OrderState;
import com.izhuixin.rsps.common.dba.BaseAbstractService;
import com.izhuixin.rsps.domain.manual.OperatorInfo;
import com.izhuixin.rsps.service.BoxBindService;
import com.izhuixin.rsps.service.OperatorLocationService;
import com.izhuixin.rsps.service.OrderPolicyService;
import com.izhuixin.rsps.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class OrderPolicyServiceImpl extends BaseAbstractService implements OrderPolicyService {

    /** 创建订单调度线程池 */
    private ExecutorService orderPolicyExecutor = Executors.newFixedThreadPool(3);

    /** 处理结果任务 */
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Autowired
    private OrderService orderService;

    @Autowired
    private BoxBindService boxBindService;

    @Autowired
    private OperatorLocationService operatorLocationService;

    /**
     * 订单分配揽件人员
     */
    @Override
    public void obtainOrder(final String orderId, final Double longitude, final Double latitude) {
        try {
            if (longitude != null && latitude != null) {
                logger.info(String.format("The Order(%s) Position lng:%f,lat:%f", orderId, longitude, latitude));
            } else {
                logger.info(String.format("The Order(%s) longitude is null or latitude is null", orderId));
            }

            OrderTask orderTask = new OrderTask(orderId, longitude, latitude);
            Future<Boolean> future = orderPolicyExecutor.submit(orderTask);

            FutureTask futureTask = new FutureTask(1, future, orderTask, 1);
            scheduler.schedule(futureTask, 30, TimeUnit.SECONDS);  // 60秒后检查结果

        } catch (Exception e) {
            logger.error(String.format("订单(%s)分配揽件人员出现异常", orderId), e);
        }
    }

    /**
     * 订单分配派货人员
     * @param orderId
     * @param entCode
     * @param boxId
     */
    @Override
    public void deliveryOrder(String orderId, String entCode, String boxId) {
        try {
            OperatorInfo operatorInfo = operatorLocationService.getNearbyOperator(orderId, entCode, (byte) 2); // 匹配收件人
            DeliveryOrderTask deliveryOrderTask = new DeliveryOrderTask(orderId, entCode, boxId, operatorInfo);
            Future<Boolean> future = orderPolicyExecutor.submit(deliveryOrderTask);
            DeliveryFutureTask futureTask = new DeliveryFutureTask(1, future, deliveryOrderTask, 2);
            scheduler.schedule(futureTask, 30, TimeUnit.SECONDS);  // 30秒后检查结果
        } catch (Exception e) {
            logger.error(String.format("订单(%s)分配派送人员出现异常", orderId), e);
        }
    }


    /**
     * 揽订单任务
     */
    private class OrderTask implements Callable<Boolean> {

        /** 订单ID */
        private String orderId;

        /** 经度 */
        private Double longitude;

        /** 纬度 */
        private Double latitude;

        public OrderTask(String orderId, Double longitude, Double latitude) {
            this.orderId = orderId;
            this.longitude = longitude;
            this.latitude = latitude;
        }

        @Override
        public Boolean call() throws Exception {
            return orderService.handleTakeIn(orderId,longitude,latitude);
        }
    }



    /**
     * 执行结果任务
     */
    private class FutureTask implements Runnable {

        /** 执行次数 */
        private int executeCount;

        /** 执行结果 */
        private Future<Boolean> future;

        /** 订单任务 */
        private OrderTask orderTask;

        /** 任务类型 */
        private int taskType;

        public FutureTask(int executeCount, Future future, OrderTask orderTask, int taskType) {
            this.executeCount = executeCount;
            this.future = future;
            this.orderTask = orderTask;
            this.taskType = taskType;
        }

        @Override
        public void run() {
            try {
                if (!future.get(1000, TimeUnit.SECONDS)) {  // 任务执行失败，尝试
                    if (executeCount > 3) {  // 执行次数大于10次，不继续尝试
                        logger.error(String.format("订单(%s)分配揽货失败，超过最大尝试次数", orderTask.orderId));
                        if (taskType == 1) {
                            orderService.updateOrderState(orderTask.orderId, OrderState.WAITING_ASSIGN_FAILED.getIndex().byteValue());
                        }
                    } else {
                        logger.info(String.format("订单(%s)，第%d次尝试分配揽货中...", orderTask.orderId, executeCount));
                        Future<Boolean> newFuture = orderPolicyExecutor.submit(orderTask);
                        int newExecuteCount = executeCount + 1;
                        FutureTask futureTask = new FutureTask(newExecuteCount, newFuture, orderTask, taskType);
                        scheduler.schedule(futureTask, 30, TimeUnit.SECONDS);  // 60秒后检查结果
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 派货订单任务
     */
    private class DeliveryOrderTask implements Callable<Boolean> {

        /** 订单 */
        private String orderId;

        /** 企业编码 */
        private String entCode;

        /** 包装箱ID */
        private String boxId;

        /** 操作人员 */
        private OperatorInfo operatorInfo;

        public DeliveryOrderTask(String orderId, String entCode, String boxId, OperatorInfo operatorInfo) {
            this.orderId = orderId;
            this.entCode = entCode;
            this.boxId = boxId;
            this.operatorInfo = operatorInfo;
        }

        @Override
        public Boolean call() throws Exception {
            return boxBindService.handleCbindBusi(orderId, entCode, boxId, operatorInfo);
        }

        public void setOperatorInfo(OperatorInfo operatorInfo) {
            this.operatorInfo = operatorInfo;
        }
    }

    /**
     * 派货执行结果任务
     */
    private class DeliveryFutureTask implements Runnable {

        /** 执行次数 */
        private int executeCount;

        /** 执行结果 */
        private Future<Boolean> future;

        /** 订单任务 */
        private DeliveryOrderTask deliveryOrderTask;

        /** 任务类型 */
        private int taskType;

        public DeliveryFutureTask(int executeCount, Future future, DeliveryOrderTask deliveryOrderTask, int taskType) {
            this.executeCount = executeCount;
            this.future = future;
            this.deliveryOrderTask = deliveryOrderTask;
            this.taskType = taskType;
        }

        @Override
        public void run() {
            try {
                if (!future.get(1000, TimeUnit.SECONDS)) {  // 任务执行失败，尝试
                    if (executeCount > 5) { // 执行次数大于n次，不继续尝试
                        logger.error(String.format("订单(%s)分配派货失败，超过最大尝试次数", deliveryOrderTask.orderId));
                        return;
                    } else if (executeCount > 4) {  // 默认
                        logger.info(String.format("订单(%s)，第%d次尝试分配（默认配送人员）派货中...", deliveryOrderTask.orderId, executeCount));
                        OperatorInfo operatorInfo = operatorLocationService.getNearbyOperator(deliveryOrderTask.orderId, deliveryOrderTask.entCode, (byte) 0); // 默认企业首个地址匹配位置
                        deliveryOrderTask.setOperatorInfo(operatorInfo);
                    } else if (executeCount > 3) {  // 寄件人地址匹配
                        logger.info(String.format("订单(%s)，第%d次尝试分配（寄件人地址匹配）派货中...", deliveryOrderTask.orderId, executeCount));
                        OperatorInfo operatorInfo = operatorLocationService.getNearbyOperator(deliveryOrderTask.orderId, deliveryOrderTask.entCode, (byte) 1); // 失败后匹配寄件人位置
                        deliveryOrderTask.setOperatorInfo(operatorInfo);
                    } else {  // 收件人地址匹配
                        logger.info(String.format("订单(%s)，第%d次尝试分配派货中...", deliveryOrderTask.orderId, executeCount));
                        OperatorInfo operatorInfo = operatorLocationService.getNearbyOperator(deliveryOrderTask.orderId, deliveryOrderTask.entCode, (byte) 2); // 匹配收件人位置
                        deliveryOrderTask.setOperatorInfo(operatorInfo);
                    }
                    Future<Boolean> newFuture = orderPolicyExecutor.submit(deliveryOrderTask);
                    int newExecuteCount = executeCount + 1;
                    DeliveryFutureTask deliveryFutureTask = new DeliveryFutureTask(newExecuteCount, newFuture, deliveryOrderTask, taskType);
                    scheduler.schedule(deliveryFutureTask, 30, TimeUnit.SECONDS);  // 30秒后检查结果
                } else {
                    logger.info(String.format("订单(%s)分配派货成功", deliveryOrderTask.orderId));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

    }

}





