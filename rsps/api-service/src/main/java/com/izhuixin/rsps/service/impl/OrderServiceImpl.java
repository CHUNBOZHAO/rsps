package com.izhuixin.rsps.service.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.izhuixin.rsps.common.constant.*;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.common.dto.BoxInfoDto;
import com.izhuixin.rsps.common.object.Pair;
import com.izhuixin.rsps.common.page.Paginator;
import com.izhuixin.rsps.common.util.FinalPositionUtils;
import com.izhuixin.rsps.common.util.SnowflakeIdWorker;
import com.izhuixin.rsps.common.vo.web.*;
import com.izhuixin.rsps.dao.manual.*;
import com.izhuixin.rsps.domain.automatic.*;
import com.izhuixin.rsps.domain.manual.*;
import com.izhuixin.rsps.domain.manual.OrderInfo;
import com.izhuixin.rsps.service.*;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

/***
 * 订单服务相关接口
 */
@Service
public class OrderServiceImpl extends AbstractCrudService<OrderDO> implements OrderService {

    @Autowired
    private BoxOperateDao boxOperateDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private BoxRecordDao boxRecordDao;

    @Autowired
    private BoxInfoDao boxInfoDao;

    @Autowired
    private OrderBoxDao orderBoxDao;

    @Autowired
    private OperatorInfoDao operatorInfoDao;

    @Autowired
    private BoxTypeDao boxTypeDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private BoxBaseService boxBaseService;

    @Autowired
    private BoxInfoService boxInfoService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private OrderDriverService orderDriverService;

    @Autowired
    private OperatorLocationService operatorLocationService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private CustomInfoService customInfoService;

    @Autowired
    private RecycleApplyInfoService recycleApplyInfoService;

    private static List<String> specialCity = Lists.newArrayList();

    static {
        specialCity.add("北京市");
        specialCity.add("天津市");
        specialCity.add("上海市");
        specialCity.add("重庆市");
    }

    /***
     * 获取订单信息
     * @param orderId
     * @return
     */
    @Override
    public OrderInfo getOrderById(String orderId, String entCode) {
        OrderInfo orderInfo = null;
        try {
            orderInfo = orderDao.getOrderById(orderId, entCode);
        } catch (Exception e) {
            logger.error(String.format("通过订单号（%s）获取订单信息出现异常", orderId), e);
        }
        return orderInfo;
    }

    @Override
    public Map<String, OrderInfo> getOrdersByIds(List<String> orderIds, String entCode) {
        Map<String, OrderInfo> orders = Maps.newHashMap();
        try {
            if (!orderIds.isEmpty()) {
                List<OrderInfo> orderInfos = orderDao.getOrdersByIds(orderIds, entCode);
                if (orderInfos != null) {
                    for (OrderInfo orderInfo : orderInfos) {
                        orders.put(orderInfo.getOrderId(), orderInfo);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(String.format("获取订单信息出现异常"), e);
        }
        return orders;
    }

    /**
     * 通过订单ID获取记录数
     * @param orderId
     * @param entCode
     * @return
     */
    @Override
    public Integer countOrderById(String orderId, String entCode) {
        Integer count = 0;
        try {
            count = orderDao.countOrderById(orderId, entCode);
        } catch (Exception e) {
            logger.error(String.format("获取订单(%s)的记录数", orderId), e);
        }
        return count;
    }

    /**
     * 保存订单信息
     * @param orderInfo
     * @param entCode
     * @return
     */
    @Override
    public boolean saveOrder(OrderInfo orderInfo, String entCode) {
        boolean res = false;
        String orderId = "";
        try {
            orderId = orderInfo.getOrderId();
            orderDao.saveOrder(orderInfo, entCode);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("保存企业(%s)订单(%s)出现异常", entCode, orderId), e);
        }
        return res;
    }

    /**
     * 创建订单
     * @param orderInfoVO
     * @return
     */
    @Override
    public String createOrder(final OrderInfoVO orderInfoVO) {

        return transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                String orderId = "";
                try {
                    // 创建订单
                    OrderDO orderInfo = new OrderDO();
                    orderInfo.setCustomerId(orderInfoVO.getCustomerId());
                    orderInfo.setCustomer(orderInfoVO.getCustomer());
                    orderInfo.setOrderId(String.valueOf(SnowflakeIdWorker.getInstance().nextId()));
                    orderInfo.setOrderType((byte) 1); // 个人订单
                    orderInfo.setSenderId(orderInfoVO.getSenderId());
                    orderInfo.setSenderName(orderInfoVO.getSenderName());
                    orderInfo.setSenderAreaCode(orderInfoVO.getSenderAreaCode());

                    StringBuilder sbSenderAddress = new StringBuilder();
                    sbSenderAddress.append(orderInfoVO.getSenderArea1());
                    sbSenderAddress.append(" ");
                    sbSenderAddress.append(orderInfoVO.getSenderArea2());
                    sbSenderAddress.append(" ");
                    sbSenderAddress.append(orderInfoVO.getSenderArea3());
                    sbSenderAddress.append(" ");
                    sbSenderAddress.append(orderInfoVO.getSenderAddress());
                    orderInfo.setSenderAddress(sbSenderAddress.toString());
                    orderInfo.setSenderTel(orderInfoVO.getSenderTel());
                    orderInfo.setReceiverId(orderInfoVO.getReceiverId());
                    orderInfo.setReceiverName(orderInfoVO.getReceiverName());
                    orderInfo.setReceiverTel(orderInfoVO.getReceiverTel());
                    orderInfo.setReceiverAreaCode(orderInfoVO.getReceiverAreaCode());

                    StringBuilder sbReceiverAddress = new StringBuilder();
                    sbReceiverAddress.append(orderInfoVO.getReceiverArea1());
                    sbReceiverAddress.append(" ");
                    sbReceiverAddress.append(orderInfoVO.getReceiverArea2());
                    sbReceiverAddress.append(" ");
                    sbReceiverAddress.append(orderInfoVO.getReceiverArea3());
                    sbReceiverAddress.append(" ");
                    sbReceiverAddress.append(orderInfoVO.getReceiverAddress());
                    orderInfo.setReceiverAddress(sbReceiverAddress.toString());
                    orderInfo.setModifyTime(new Date());
                    orderInfo.setCreateTime(new Date());
                    orderInfo.setStatus((byte)1);
                    orderInfo.setState(OrderState.WAITING_ASSIGN.getIndex().byteValue());
                    orderInfo.setDetail(orderInfoVO.getDetail());
                    save(orderInfo);

                    orderId = orderInfo.getOrderId();

                    // 保存订单需求包装箱信息
                    List<BoxTypeVO> boxTypeVOS = orderInfoVO.getBoxes();
                    if (boxTypeVOS != null) {
                        for (BoxTypeVO boxTypeVO : boxTypeVOS) {
                            boxTypeDao.saveBoxType(orderId,
                                    boxTypeVO.getTypeId(),
                                    boxTypeVO.getColor(),
                                    boxTypeVO.getCount());
                        }
                    }

                } catch (Exception e) {
                    logger.error(String.format("用户(%s)创建订单出现异常", orderInfoVO.getCustomerId()), e);
                }

                return orderId;
            }
        });
    }


    /**
     * 分配揽货人员
     */
    @Override
    public boolean handleTakeIn(final String orderId, final Double longitude, final Double latitude) {
        boolean res = false;

        try {
            if (latitude == null || longitude == null) {
                return res;
            }
            double[] pos = FinalPositionUtils.transBMapPosition(latitude, longitude);
            Pair<String, Double> pair = operatorLocationService.assignOrder(pos[1], pos[0], null);
            if (StringUtils.isNotBlank(pair.getFirst())) {
                String[] opArray = pair.getFirst().split("_");
                if (opArray.length > 0) {
                    final String entCode = opArray[0];
                    boolean checkRes = enterpriseService.checkEntCode(entCode);
                    if (checkRes) {  // 检查揽货人员信息正确
                        String operatorName = pair.getFirst();
                        final OperatorInfo operatorInfo = operatorInfoDao.getOperatorInfoByUserName(operatorName, OperatorType.DRIVER.getIndex().byteValue(), entCode.concat("_"));
                        if (operatorInfo != null) {
                            res = transactionTemplate.execute(new TransactionCallback<Boolean>(){

                                @Override
                                public Boolean doInTransaction(TransactionStatus transactionStatus) {
                                    boolean tsRes = false;
                                    try {
                                        // 自动分配揽货人员
                                        OrderDriverDO orderDriverInfo = new OrderDriverDO();
                                        orderDriverInfo.setOrderId(orderId);
                                        orderDriverInfo.setCreateTime(new Date());
                                        orderDriverInfo.setDriverId(operatorInfo.getOperatorId());
                                        orderDriverInfo.setDriverName(operatorInfo.getUserName());
                                        orderDriverInfo.setDriverPhone(operatorInfo.getTel());
                                        orderDriverInfo.setDriverOrder((byte)1);
                                        orderDriverInfo.setState((byte) 0);
                                        orderDriverService.save(orderDriverInfo);

                                        // 分配揽货成功，改变订单状态
                                        OrderDO orderInfo = new OrderDO();
                                        orderInfo.setState(OrderState.WAITING_TAKE_IN.getIndex().byteValue());
                                        orderInfo.setModifyTime(new Date());
                                        FilterExample fe = new FilterExample();
                                        fe.createCriteria().andFieldEqualTo("order_id", orderId);
                                        long updateRes = update(orderInfo, fe);
                                        if (updateRes > 0) {
                                            tsRes = true;
                                        } else {
                                            transactionStatus.setRollbackOnly();
                                        }
                                    } catch (Exception e) {
                                        transactionStatus.setRollbackOnly();
                                        logger.error(String.format("分配订单(%s)揽货人员出现异常", orderId), e);
                                    }
                                    return tsRes;
                                }
                            });

                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(String.format("分配订单(%s)揽货人员信息解析出现异常", orderId), e);
        }

        return res;
    }

//    /**
//     * 分配揽货人员
//     */
//    @Override
//    public void handleTakeIn(final String orderId, final Double longitude, final Double latitude) {
//
//        orderCreateExecutor.execute(new Runnable() {
//
//            public DateTime intDateTime = DateTime.now();
//
//            @Override
//            public void run() {
//                boolean loop = true;
//                try {
//                    while (loop) {
//                        if (longitude != null && latitude != null) {
//                            Pair<String, Double> pair = assignOrder(longitude, latitude);
//                            if (StringUtils.isNotBlank(pair.getFirst())) {
//                                String[] opArray = pair.getFirst().split("_");
//                                if (opArray.length > 0) {
//                                    final String entCode = opArray[0];
//                                    boolean checkRes = enterpriseService.checkEntCode(entCode);
//                                    if (checkRes) {  // 检查揽货人员信息正确
//                                        String operatorName = pair.getFirst();
//                                        final OperatorInfo operatorInfo = operatorInfoDao.getOperatorInfoByUserName(operatorName, OperatorType.DRIVER.getIndex().byteValue(), entCode.concat("_"));
//                                        if (operatorInfo != null) {
//                                            boolean res = transactionTemplate.execute(new TransactionCallback<Boolean>(){
//
//                                                @Override
//                                                public Boolean doInTransaction(TransactionStatus transactionStatus) {
//                                                    boolean tsRes = false;
//                                                    try {
//                                                        // 自动分配揽货人员
//                                                        OrderDriverDO orderDriverInfo = new OrderDriverDO();
//                                                        orderDriverInfo.setOrderId(orderId);
//                                                        orderDriverInfo.setCreateTime(new Date());
//                                                        orderDriverInfo.setDriverId(operatorInfo.getOperatorId());
//                                                        orderDriverInfo.setDriverName(operatorInfo.getUserName());
//                                                        orderDriverInfo.setDriverPhone(operatorInfo.getTel());
//                                                        orderDriverInfo.setDriverOrder((byte)1);
//                                                        orderDriverInfo.setState((byte) 0);
//                                                        orderDriverService.save(orderDriverInfo);
//
//                                                        // 分配揽货成功，改变订单状态
//                                                        OrderDO orderInfo = new OrderDO();
//                                                        orderInfo.setState(OrderState.WAITING_TAKE_IN.getIndex().byteValue());
//                                                        orderInfo.setModifyTime(new Date());
//                                                        FilterExample fe = new FilterExample();
//                                                        fe.createCriteria().andFieldEqualTo("order_id", orderId);
//                                                        long updateRes = update(orderInfo, fe);
//                                                        if (updateRes > 0) {
//                                                            tsRes = true;
//                                                        } else {
//                                                            transactionStatus.setRollbackOnly();
//                                                        }
//                                                    } catch (Exception e) {
//                                                        transactionStatus.setRollbackOnly();
//                                                        logger.error("分配揽货人员出现异常", e);
//                                                    }
//                                                    return tsRes;
//                                                }
//                                            });
//
//                                            if (res) { // 分配成功，则退出分配线程
//                                                loop = false;
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//
//                            Thread.sleep(5000);
//                            int executeTime = 5;  // 大于5分钟则下单失败
////                        int executeTime = 1;  // todo 测试数据
//                            if (DateTime.now().getMillis() - intDateTime.getMillis() > 1000 * 60 * executeTime) {
//                                updateOrderState(orderId, OrderState.WAITING_ASSIGN_FAILED.getIndex().byteValue());
//                                loop = false;
//                            }
//
//                        } else {  // 坐标有异常直接退出
//                            updateOrderState(orderId, OrderState.WAITING_ASSIGN_FAILED.getIndex().byteValue());
//                            loop = false;
//                            logger.warn("坐标异常");
//                        }
//
//                    }
//                } catch (Exception e) {
//                    logger.error(String.format("分配订单(%d)出现异常", orderId), e);
//                }
//                logger.warn("自动分配揽货人流程结束");
//            }
//        });
//
//    }

    /**
     * 获取C端用户订单
     * @param orderId
     * @return
     */
    @Override
    public OrderInfoVO getOrder(String orderId) {
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        try {
            OrderInfo orderInfo = orderDao.getOrder(orderId);
            if (orderInfo != null) {
                orderInfoVO.setId(orderInfo.getId());
                orderInfoVO.setCustomer(orderInfo.getCustomer());
                orderInfoVO.setCustomerId(orderInfo.getCustomerId());
                orderInfoVO.setOrderId(orderInfo.getOrderId());
                orderInfoVO.setOrderType(orderInfo.getOrderType());
                orderInfoVO.setCreateTime(orderInfo.getCreateTime());
                orderInfoVO.setModifyTime(orderInfo.getModifyTime());
                orderInfoVO.setDetail(orderInfo.getDetail());
                orderInfoVO.setState(orderInfo.getState());
                orderInfoVO.setSenderName(orderInfo.getSenderName());
                orderInfoVO.setSenderTel(orderInfo.getSenderTel());
                orderInfoVO.setSenderArea1("");
                orderInfoVO.setSenderArea2("");
                orderInfoVO.setSenderArea3("");
                orderInfoVO.setSenderAddress("");
                if (StringUtils.isNotEmpty(orderInfo.getSenderAddress())) {
                    String[] senderAddrArry = orderInfo.getSenderAddress().split(" ");
                    if (senderAddrArry.length >= 4) {
                        orderInfoVO.setSenderArea1(senderAddrArry[0]);
                        orderInfoVO.setSenderArea2(senderAddrArry[1]);
                        orderInfoVO.setSenderArea3(senderAddrArry[2]);
                        orderInfoVO.setSenderAddress(senderAddrArry[3]);
                    }
                }
                orderInfoVO.setReceiverName(orderInfo.getReceiverName());
                orderInfoVO.setReceiverTel(orderInfo.getReceiverTel());
                orderInfoVO.setReceiverArea1("");
                orderInfoVO.setReceiverArea2("");
                orderInfoVO.setReceiverArea3("");
                orderInfoVO.setReceiverAddress("");
                if (StringUtils.isNotEmpty(orderInfo.getReceiverAddress())) {
                    String[] receiverAddrArry = orderInfo.getReceiverAddress().split(" ");
                    if (receiverAddrArry.length >= 4) {
                        orderInfoVO.setReceiverArea1(receiverAddrArry[0]);
                        orderInfoVO.setReceiverArea2(receiverAddrArry[1]);
                        orderInfoVO.setReceiverArea3(receiverAddrArry[2]);
                        orderInfoVO.setReceiverAddress(receiverAddrArry[3]);
                    }
                }

                List<BoxTypeInfo> boxTypeInfos = boxInfoService.getBoxTypes(orderId);
                List<BoxTypeVO> boxTypeVOS = Lists.newArrayList();
                if (boxTypeInfos != null) {
                    BoxTypeVO boxTypeVO = null;
                    for (BoxTypeInfo boxTypeInfo : boxTypeInfos) {
                        boxTypeVO = new BoxTypeVO();
                        boxTypeVO.setTypeId(boxTypeInfo.getTypeId());
                        boxTypeVO.setSize(boxTypeInfo.getSize());
                        boxTypeVO.setName(boxTypeInfo.getName());
                        boxTypeVO.setCount(boxTypeInfo.getCount());
                        boxTypeVO.setColor(boxTypeInfo.getColor());
                        boxTypeVOS.add(boxTypeVO);
                    }
                }
                orderInfoVO.setBoxes(boxTypeVOS);
            }
        } catch (Exception e) {
            logger.error(String.format("获取订单(%d)信息出现异常", orderId), e);
        }
        return orderInfoVO;
    }

    /**
     * 获取C端操作人员的揽货订单
     * @param operatorId
     * @param entCode
     * @return
     */
    @Override
    public List<OrderInfo> getTakeOrders(String operatorId, String entCode) {
        List<OrderInfo> orders = null;
        try {
            orders = orderDao.getTakeOrders(operatorId);
        } catch (Exception e) {
            logger.error(String.format("获取操作人员(%s)的揽货订单出现异常", operatorId), e);
        }
        if (orders == null) {
            orders = Lists.newArrayList();
        }
        return orders;
    }

    /**
     * 更新订单信息
     * @param orderInfo
     * @param orderId
     * @param entCode
     * @return
     */
    @Override
    public boolean updateOrder(OrderInfo orderInfo, String orderId, String entCode) {
        boolean res = false;
        try {
            orderId = orderInfo.getOrderId();
            orderDao.updateOrder(orderInfo, orderId, entCode);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("更新企业(%s)订单(%s)出现异常", entCode, orderId), e);
        }
        return res;
    }

    /**
     * 更新C端用户订单
     * @param orderInfoVO
     * @return
     */
    @Override
    public boolean updateOrder(OrderInfoVO orderInfoVO) {
        boolean res = false;
        try {
            if (orderInfoVO != null && StringUtils.isNotEmpty(orderInfoVO.getOrderId())) {
                FilterExample fe = new FilterExample();
                fe.createCriteria().andFieldEqualTo("order_id", orderInfoVO.getOrderId());

                OrderDO orderInfo = new OrderDO();
                orderInfo.setCustomerId(orderInfoVO.getCustomerId());
                orderInfo.setCustomer(orderInfoVO.getCustomer());
                orderInfo.setSenderId(orderInfoVO.getSenderId());
                orderInfo.setSenderName(orderInfoVO.getSenderName());
                orderInfo.setSenderAreaCode(orderInfoVO.getSenderAreaCode());

                StringBuilder sbSenderAddress = new StringBuilder();
                sbSenderAddress.append(orderInfoVO.getSenderArea1());
                sbSenderAddress.append(" ");
                sbSenderAddress.append(orderInfoVO.getSenderArea2());
                sbSenderAddress.append(" ");
                sbSenderAddress.append(orderInfoVO.getSenderArea3());
                sbSenderAddress.append(" ");
                sbSenderAddress.append(orderInfoVO.getSenderAddress());
                orderInfo.setSenderAddress(sbSenderAddress.toString());
                orderInfo.setSenderTel(orderInfoVO.getSenderTel());
                orderInfo.setReceiverId(orderInfoVO.getReceiverId());
                orderInfo.setReceiverName(orderInfoVO.getReceiverName());
                orderInfo.setReceiverTel(orderInfoVO.getReceiverTel());
                orderInfo.setReceiverAreaCode(orderInfoVO.getReceiverAreaCode());

                StringBuilder sbReceiverAddress = new StringBuilder();
                sbReceiverAddress.append(orderInfoVO.getReceiverArea1());
                sbReceiverAddress.append(" ");
                sbReceiverAddress.append(orderInfoVO.getReceiverArea2());
                sbReceiverAddress.append(" ");
                sbReceiverAddress.append(orderInfoVO.getReceiverArea3());
                sbReceiverAddress.append(" ");
                sbReceiverAddress.append(orderInfoVO.getReceiverAddress());
                orderInfo.setReceiverAddress(sbReceiverAddress.toString());
                orderInfo.setModifyTime(new Date());
                long resUpdate = update(orderInfo, fe);
                if (resUpdate > 0) {

                    // 先删除
                    boxTypeDao.deleteByOrderId(orderInfoVO.getOrderId());

                    // 保存订单需求包装箱信息
                    List<BoxTypeVO> boxTypeVOS = orderInfoVO.getBoxes();
                    if (boxTypeVOS != null) {
                        for (BoxTypeVO boxTypeVO : boxTypeVOS) {
                            boxTypeDao.saveBoxType(orderInfoVO.getOrderId(),
                                    boxTypeVO.getTypeId(),
                                    boxTypeVO.getColor(),
                                    boxTypeVO.getCount());
                        }
                    }

                    res = true;
                } else {
                    logger.warn(String.format("更新订单(%s)数量不正确", orderInfoVO.getOrderId()));
                }
            } else {
                logger.error(String.format("订单信息不正确"));
            }
        } catch (Exception e) {
            logger.error(String.format("更新订单出现异常"), e);
        }
        return res;
    }

    /**
     * 通过客户ID获取客户当前订单包装箱信息
     * @return
     */
    @Override
    public BoxInfoDto getCustomCurrentBoxes(String customId, Paginator paginator) {
        BoxInfoDto boxInfoDto = new BoxInfoDto();
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();
        try {
            BoxInfoVO boxInfoVO = null;

            List<String> entCodes = enterpriseService.getEntCodes();
            paginator.setItems(boxOperateDao.countCustomCurrentBoxes(customId, entCodes));
            List<BoxInfo> boxInfos = boxOperateDao.getCustomCurrentBoxes(customId, entCodes, paginator.rowBounds());
            for (BoxInfo boxInfo : boxInfos) {
                boxInfoVO = new BoxInfoVO();
                boxInfoVO.setRfid(boxInfo.getRfid());
                boxInfoVO.setBarcode(boxInfo.getBarcode());
                boxInfoVO.setBoxName(boxInfo.getBoxName());
                boxInfoVO.setStatus(boxInfo.getStatus());
                if (boxInfo.getStatus().byteValue() == BoxStatus.BINDING.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("出库");
                } else if (boxInfo.getStatus().byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("运输中");
                } else if (boxInfo.getStatus().byteValue() == BoxStatus.TRANSIT.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("运输中");
                } else {
                    boxInfoVO.setStatusDescr("");
                }
                boxInfoVO.setOperator(boxInfo.getOperator());
                boxInfoVO.setOperatorId(boxInfo.getOperatorId());
                boxInfoVO.setOperateTime(boxInfo.getOperateTime());
                boxInfoVO.setOrderId(boxInfo.getOrderId());
                boxInfoVO.setReceiver(boxInfo.getCustomName());
                boxInfoVO.setCreateTime(boxInfo.getCreateTime());

                // 查询企业名称
                String entName = "";
                try {
                    FilterExample fe = new FilterExample();
                    fe.createCriteria().andFieldEqualTo("rfid", boxInfo.getRfid());
                    Optional<BoxBaseDO> optional = boxBaseService.get(fe);
                    if (optional.isPresent()) {
                        fe.clear();
                        fe.createCriteria().andFieldEqualTo("ent_id", optional.get().getEntId());
                        Optional<Enterprise> optional1 = enterpriseService.get(fe);
                        if (optional1.isPresent()) {
                            entName = optional1.get().getEntName();
                        }
                    }
                } catch (Exception e) {
                    logger.error("获取企业名称出现异常", e);
                }
                boxInfoVO.setSender(entName);
                boxInfoVO.setDirection((byte) 2); // 企业订单全部为"收"

                boxInfoVO.setOrderSource(OrderType.BUSINESS.getIndex().byteValue());

                boxInfoVOS.add(boxInfoVO);
            }
        } catch (Exception e) {
            logger.error(String.format("查询客户(%s)当前订单信息出现异常", customId), e);
        }

        boxInfoDto.setBoxInfos(boxInfoVOS);
        boxInfoDto.setPaginator(paginator);
        return boxInfoDto;
    }

    /***
     * 通过客户ID获取客户历史订单包装箱信息
     * @param customId
     * @param paginator
     * @return
     */
    @Override
    public BoxInfoDto getCustomHistoryBoxes(String customId, Paginator paginator) {
        BoxInfoDto boxInfoDto = new BoxInfoDto();
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();
        try {
            List<String> entCodes = enterpriseService.getEntCodes();

            paginator.setItems(boxOperateDao.countCustomHistoryBoxes(customId, entCodes));
            List<BoxInfo> boxInfos = boxOperateDao.getCustomHistoryBoxes(customId, entCodes, paginator.rowBounds());
            BoxInfoVO boxInfoVO = null;
            for (BoxInfo boxInfo : boxInfos) {
                boxInfoVO = new BoxInfoVO();
                boxInfoVO.setRfid(boxInfo.getRfid());
                boxInfoVO.setBarcode(boxInfo.getBarcode());
                boxInfoVO.setBoxName(boxInfo.getBoxName());
                boxInfoVO.setStatus(boxInfo.getStatus());
                boxInfoVO.setSignStatus(boxInfo.getSignStatus());
                if (boxInfo.getSignStatus().byteValue() == SignStatus.SIGN_IN.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("已签收");
                } else if (boxInfo.getSignStatus().byteValue() == SignStatus.DENIED_SIGN.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("拒绝签收");
                }
                boxInfoVO.setOperator(boxInfo.getOperator());
                boxInfoVO.setOperatorId(boxInfo.getOperatorId());
                boxInfoVO.setOperateTime(boxInfo.getOperateTime());
                boxInfoVO.setOrderId(boxInfo.getOrderId());

                String customName = "";
                try {
                    FilterExample fe = new FilterExample();
                    fe.createCriteria().andFieldEqualTo("custom_id", customId);
                    Optional<CustomInfoDO> optional = customInfoService.get(fe);
                    if (optional.isPresent()) {
                        customName = optional.get().getCustomName();
                    }
                } catch (Exception e) {
                    logger.error(String.format("获取客户(%s)名称出现异常", customId), e);
                }
                boxInfoVO.setReceiver(customName);

                // 查询企业名称
                String entName = "";
                try {
                    FilterExample fe = new FilterExample();
                    fe.createCriteria().andFieldEqualTo("rfid", boxInfo.getRfid());
                    Optional<BoxBaseDO> optional = boxBaseService.get(fe);
                    if (optional.isPresent()) {
                        fe.clear();
                        fe.createCriteria().andFieldEqualTo("ent_id", optional.get().getEntId());
                        Optional<Enterprise> optional1 = enterpriseService.get(fe);
                        if (optional1.isPresent()) {
                            entName = optional1.get().getEntName();
                        }
                    }
                } catch (Exception e) {
                    logger.error("获取企业名称出现异常", e);
                }
                boxInfoVO.setSender(entName);
                boxInfoVO.setDirection((byte) 2); // 企业订单全部为"收"

                boxInfoVOS.add(boxInfoVO);
            }
        } catch (Exception e) {
            logger.error(String.format("查询客户(%s)历史订单信息出现异常", customId), e);
        }

        boxInfoDto.setBoxInfos(boxInfoVOS);
        boxInfoDto.setPaginator(paginator);

        return boxInfoDto;
    }

    /**
     * 获取包装箱的流转状态
     * @param boxId
     * @param orderId
     * @return
     */
    @Override
    public List<BoxFlowRecordVO> getBoxFlowInfo(String boxId, String orderId) {

        List<BoxFlowRecordVO> flowRecords = Lists.newArrayList();
        String entCode = enterpriseService.getEntCodeByBoxId(boxId);
        if (StringUtils.isBlank(entCode)) {
            return flowRecords;
        }

        List<BoxRecordInfo> boxRecordInfos = boxRecordDao.getInfoByRfidAndOrderId(boxId, orderId, entCode.concat("_"));

        BoxFlowRecordVO flowRecordVO = null;
        BoxFlowRecordVO flowRecordVO1 = null;
        for (BoxRecordInfo recordInfo : boxRecordInfos) {
            flowRecordVO = new BoxFlowRecordVO();
            flowRecordVO.setCreateTime(recordInfo.getCreateTime());
            flowRecordVO.setOperateType(recordInfo.getOperateType());
            flowRecordVO.setOperator(recordInfo.getOperator());
            flowRecordVO.setRfid(recordInfo.getRfid());
            flowRecordVO.setOrderId(recordInfo.getOrderId());

            // 中转处理
            if (recordInfo.getOperateType().byteValue() == OperateType.TRANSIT_SIGN_IN.getIndex().byteValue()) {
                SysUserInfo sysUserInfo = sysUserDao.getUserInfoByUserId(recordInfo.getExtraValue());
                if (sysUserInfo != null) {
                    flowRecordVO.setExtraValue(sysUserInfo.getNickname());
                }
            }

            flowRecords.add(flowRecordVO);

            if (recordInfo.getExceptionType() != null && recordInfo.getExceptionType().byteValue() == 1) {   // 如有有异常，则增加一条异常信息
                flowRecordVO1 = new BoxFlowRecordVO();
                flowRecordVO1.setCreateTime(recordInfo.getCreateTime());
                flowRecordVO1.setOperateType(recordInfo.getOperateType());
                flowRecordVO1.setOperator(recordInfo.getOperator());
                flowRecordVO1.setRfid(recordInfo.getRfid());
                flowRecordVO1.setOrderId(recordInfo.getOrderId());
                flowRecordVO1.setExceptionType(recordInfo.getExceptionType());
                flowRecords.add(flowRecordVO1);
            }
            flowRecordVO.setExceptionType((byte)0);
        }
        return flowRecords;
    }

    /**
     * 获取包裹药品信息
     * @param boxId
     * @param orderId
     * @param orderType  -- 订单类别 1：当前订单   2：历史订单
     * @return
     */
    @Override
    public List<DrugInfoVO> getBoxDetail(String boxId, String orderId, Byte orderType) {
        List<DrugInfoVO> drugs = null;

        String entCode = enterpriseService.getEntCodeByBoxId(boxId);
        if (StringUtils.isNotBlank(entCode)) {
            if (orderType == 1) {
                try {
                    BoxInfo boxInfo = boxInfoDao.getBoxInfoByRfidAndOrderId(boxId, orderId, entCode.concat("_"));

                    if (boxInfo != null) {
                        String detail = boxInfo.getDetail();
                        if (StringUtils.isNotBlank(detail)) {
                            Gson gson = new Gson();
                            drugs = gson.fromJson(detail, new TypeToken<List<DrugInfoVO>>(){}.getType());
                        }
                    }
                } catch (Exception e) {
                    logger.error(String.format("获取当前订单(%s)包装箱(%s)的药品信息出现异常", orderId, boxId), e);
                }
            } else if (orderType == 2) {
                try {
                    OrderBoxInfo orderBoxInfo = orderBoxDao.getInfoByOrderIdAndRfid(orderId, boxId, entCode.concat("_"));
                    if (orderBoxInfo != null) {
                        String detail = orderBoxInfo.getDetail();
                        if (StringUtils.isNotBlank(detail)) {
                            Gson gson = new Gson();
                            drugs = gson.fromJson(detail, new TypeToken<List<DrugInfoVO>>(){}.getType());
                        }
                    }

                } catch (Exception e) {
                    logger.error(String.format("获取历史订单(%s)包装箱(%s)的药品信息出现异常", orderId, boxId), e);
                }
            }
        }

        if (drugs == null) {
            drugs = Lists.newArrayList();
        }
        return drugs;
    }


    /**
     * 通过客户ID获取客户当前订单包装箱信息
     * @return
     */
    @Override
    public List<BoxInfoVO> getCustomCurrentBoxesWithOrderId(String customId, String orderId) {
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();

        List<String> entCodes = enterpriseService.getEntCodes();

        if (!entCodes.isEmpty()) {

            List<BoxInfo> boxInfos = boxOperateDao.getCustomCurrentBoxesWithOrderId(customId, orderId, entCodes);
            BoxInfoVO boxInfoVO = null;
            for (BoxInfo boxInfo : boxInfos) {
                boxInfoVO = new BoxInfoVO();
                boxInfoVO.setRfid(boxInfo.getRfid());
                boxInfoVO.setBarcode(boxInfo.getBarcode());
                boxInfoVO.setBoxName(boxInfo.getBoxName());
                boxInfoVO.setStatus(boxInfo.getStatus());
                if (boxInfo.getStatus().byteValue() == BoxStatus.BINDING.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("出库");
                } else if (boxInfo.getStatus().byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue() ||
                        boxInfo.getStatus().byteValue() == BoxStatus.TRANSIT.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("运输中");
                } else {
                    boxInfoVO.setStatusDescr("");
                }
                boxInfoVO.setOperator(boxInfo.getOperator());
                boxInfoVO.setOperatorId(boxInfo.getOperatorId());
                boxInfoVO.setOperateTime(boxInfo.getOperateTime());
                boxInfoVO.setOrderId(boxInfo.getOrderId());

                boxInfoVO.setReceiver(boxInfo.getCustomName());
                boxInfoVO.setCreateTime(boxInfo.getCreateTime());

                // 查询企业名称
                String entName = "";
                try {
                    FilterExample fe = new FilterExample();
                    fe.createCriteria().andFieldEqualTo("rfid", boxInfo.getRfid());
                    Optional<BoxBaseDO> optional = boxBaseService.get(fe);
                    if (optional.isPresent()) {
                        fe.clear();
                        fe.createCriteria().andFieldEqualTo("ent_id", optional.get().getEntId());
                        Optional<Enterprise> optional1 = enterpriseService.get(fe);
                        if (optional1.isPresent()) {
                            entName = optional1.get().getEntName();
                        }
                    }
                } catch (Exception e) {
                    logger.error("获取企业名称出现异常", e);
                }
                boxInfoVO.setSender(entName);
                boxInfoVO.setDirection((byte) 2); // 企业订单全部为"收"

                boxInfoVOS.add(boxInfoVO);
            }
        }

        return boxInfoVOS;
    }

    /***
     * 通过客户ID获取客户历史订单包装箱信息
     * @param customId
     * @return
     */
    @Override
    public List<BoxInfoVO> getCustomHistoryBoxesWithOrderId(String customId, String orderId) {
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();

        List<String> entCodes = enterpriseService.getEntCodes();
        if (!entCodes.isEmpty()) {
            List<BoxInfo> boxInfos = boxOperateDao.getCustomHistoryBoxesWithOrderId(customId,  orderId, entCodes);
            BoxInfoVO boxInfoVO = null;
            for (BoxInfo boxInfo : boxInfos) {
                boxInfoVO = new BoxInfoVO();
                boxInfoVO.setRfid(boxInfo.getRfid());
                boxInfoVO.setBarcode(boxInfo.getBarcode());
                boxInfoVO.setBoxName(boxInfo.getBoxName());
                boxInfoVO.setStatus(boxInfo.getStatus());
                boxInfoVO.setSignStatus(boxInfo.getSignStatus());
                if (boxInfo.getSignStatus().byteValue() == SignStatus.SIGN_IN.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("已签收");
                } else if (boxInfo.getSignStatus().byteValue() == SignStatus.DENIED_SIGN.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("拒绝签收");
                }
                boxInfoVO.setOperator(boxInfo.getOperator());
                boxInfoVO.setOperatorId(boxInfo.getOperatorId());
                boxInfoVO.setOperateTime(boxInfo.getOperateTime());
                boxInfoVO.setOrderId(boxInfo.getOrderId());

                String customName = "";
                try {
                    FilterExample fe = new FilterExample();
                    fe.createCriteria().andFieldEqualTo("custom_id", customId);
                    Optional<CustomInfoDO> optional = customInfoService.get(fe);
                    if (optional.isPresent()) {
                        customName = optional.get().getCustomName();
                    }
                } catch (Exception e) {
                    logger.error(String.format("获取客户(%s)名称出现异常", customId), e);
                }
                boxInfoVO.setReceiver(customName);

                // 查询企业名称
                String entName = "";
                try {
                    FilterExample fe = new FilterExample();
                    fe.createCriteria().andFieldEqualTo("rfid", boxInfo.getRfid());
                    Optional<BoxBaseDO> optional = boxBaseService.get(fe);
                    if (optional.isPresent()) {
                        fe.clear();
                        fe.createCriteria().andFieldEqualTo("ent_id", optional.get().getEntId());
                        Optional<Enterprise> optional1 = enterpriseService.get(fe);
                        if (optional1.isPresent()) {
                            entName = optional1.get().getEntName();
                        }
                    }
                } catch (Exception e) {
                    logger.error("获取企业名称出现异常", e);
                }
                boxInfoVO.setSender(entName);
                boxInfoVO.setDirection((byte) 2); // 企业订单全部为"收"

                boxInfoVOS.add(boxInfoVO);
            }
        }

        return boxInfoVOS;
    }


    /**
     * 获取C端用户当前订单包装箱信息
     * @param customId
     * @return
     */
    @Override
    public List<BoxInfoVO> getCustomerCurrentBoxes(String customId, String tel, String orderId) {

        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();

        List<String> entCodes = enterpriseService.getEntCodes();
        if (!entCodes.isEmpty()) {
//            List<OrderInfo> orderInfos = orderDao.getCustomerCurrentBoxes(customId, tel, entCodes, orderId);

            FilterExample fe = new FilterExample();
            FilterExample.Criteria criteria = fe.createCriteria();
            criteria.andFieldEqualTo("customer_id", customId);
            if (tel != null) {
                fe.or().andFieldEqualTo("receiver_tel", tel);
            }
            if (orderId != null) {
                criteria.andFieldLike("order_id", "%" + orderId + "%");
            }
            fe.setOrderByClause("create_time desc");
            List<OrderDO> orderInfos =  getList(fe);

            BoxInfoVO boxInfoVO = null;
            String statusDescr = "";
            for (OrderDO orderInfo : orderInfos) {
                if (orderInfo.getState() == OrderState.DONE.getIndex().byteValue() || orderInfo.getState() == OrderState.WAITING_ALLOT_COURIER.getIndex().byteValue()) { // 已揽货处理订单
                    List<BoxInfo> boxInfos = boxInfoDao.queryAllBoxesByOrderId(orderInfo.getOrderId(), entCodes);

                    for (BoxInfo boxInfo : boxInfos) {
                        boxInfoVO = new BoxInfoVO();
                        boxInfoVO.setRfid(boxInfo.getRfid());
                        boxInfoVO.setStatus(orderInfo.getState().byteValue());

                        if (boxInfo.getStatus().byteValue() == BoxStatus.BINDING.getIndex().byteValue()) {
                            statusDescr = "运输中";
                        } else if (boxInfo.getStatus().byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue() || boxInfo.getStatus().byteValue() == BoxStatus.TRANSIT.getIndex().byteValue()) {
                            statusDescr = "运输中";
                            if (boxInfo.getSignStatus().byteValue() == SignStatus.DENIED_SIGN.getIndex().byteValue()) {
                                statusDescr = "拒绝签收";
                            }
                        } else {
                            continue;
                        }

                        boxInfoVO.setStatusDescr(statusDescr);
                        boxInfoVO.setCreateTime(orderInfo.getCreateTime());
                        boxInfoVO.setOrderId(orderInfo.getOrderId());
                        boxInfoVO.setReceiver(orderInfo.getReceiverName());
                        String receiverAddress = orderInfo.getReceiverAddress();
                        String[] receiverAddressArry = receiverAddress.split(" ");
                        if (receiverAddressArry.length == 4) {
                            if (receiverAddressArry[0] != null && specialCity.contains(receiverAddressArry[0])) {
                                boxInfoVO.setReceiverArea(receiverAddressArry[0]);
                            } else {
                                boxInfoVO.setReceiverArea(receiverAddressArry[1]);
                            }
                        }

                        boxInfoVO.setSender(orderInfo.getSenderName());
                        String senderAddress = orderInfo.getSenderAddress();
                        String[] senderAddressArry = senderAddress.split(" ");
                        if (senderAddressArry.length == 4) {
                            if (senderAddressArry[0] != null && specialCity.contains(senderAddressArry[0])) {
                                boxInfoVO.setSenderArea(senderAddressArry[0]);
                            } else {
                                boxInfoVO.setSenderArea(senderAddressArry[1]);
                            }
                        }

                        if (orderInfo.getCustomerId().equals(customId)) {
                            boxInfoVO.setDirection((byte) 1); // 企业订单全部为"寄"
                        } else if (orderInfo.getReceiverTel().equals(tel)){
                            boxInfoVO.setDirection((byte) 2); // 企业订单全部为"收"
                        }

                        boxInfoVO.setOrderSource(OrderType.CONSUMER.getIndex().byteValue());

                        boxInfoVOS.add(boxInfoVO);
                    }
                } else {  // 未揽货订单
                    boxInfoVO = new BoxInfoVO();
                    boxInfoVO.setRfid("");
                    boxInfoVO.setStatus(orderInfo.getState().byteValue());

                    statusDescr = "";
                    if (orderInfo.getState().byteValue() == OrderState.WAITING_ASSIGN.getIndex().byteValue()) {
                        statusDescr = OrderState.WAITING_ASSIGN.getDescr();
                    } else if (orderInfo.getState().byteValue() == OrderState.WAITING_TAKE_IN.getIndex().byteValue()) {
                        statusDescr = OrderState.WAITING_TAKE_IN.getDescr();
                    } else if (orderInfo.getState().byteValue() == OrderState.WAITING_ASSIGN_FAILED.getIndex().byteValue()) {
                        statusDescr = OrderState.WAITING_ASSIGN_FAILED.getDescr();
                    }

                    boxInfoVO.setStatusDescr(statusDescr);
                    boxInfoVO.setCreateTime(orderInfo.getCreateTime());
                    boxInfoVO.setOrderId(orderInfo.getOrderId());
                    boxInfoVO.setReceiver(orderInfo.getReceiverName());
                    String receiverAddress = orderInfo.getReceiverAddress();
                    String[] receiverAddressArry = receiverAddress.split(" ");
                    if (receiverAddressArry.length == 4) {
                        if (receiverAddressArry[0] != null && specialCity.contains(receiverAddressArry[0])) {
                            boxInfoVO.setReceiverArea(receiverAddressArry[0]);
                        } else {
                            boxInfoVO.setReceiverArea(receiverAddressArry[1]);
                        }
                    }

                    boxInfoVO.setSender(orderInfo.getSenderName());
                    String senderAddress = orderInfo.getSenderAddress();
                    String[] senderAddressArry = senderAddress.split(" ");
                    if (senderAddressArry.length == 4) {
                        if (senderAddressArry[0] != null && specialCity.contains(senderAddressArry[0])) {
                            boxInfoVO.setSenderArea(senderAddressArry[0]);
                        } else {
                            boxInfoVO.setSenderArea(senderAddressArry[1]);
                        }
                    }

                    if (orderInfo.getCustomerId().equals(customId)) {
                        boxInfoVO.setDirection((byte) 1); // 企业订单全部为"寄"
                    } else if (orderInfo.getReceiverTel().equals(tel)){
                        boxInfoVO.setDirection((byte) 2); // 企业订单全部为"收"
                    }

                    boxInfoVO.setOrderSource(OrderType.CONSUMER.getIndex().byteValue());
                    boxInfoVOS.add(boxInfoVO);
                }
            }
        }

        return boxInfoVOS;
    }

    /**
     * 获取C端用户已签收订单包装箱信息
     * @param tel
     * @param customId
     * @param orderId
     * @return
     */
    @Override
    public List<BoxInfoVO> getCustomerHistoryBoxes(String customId, String tel, String orderId) {
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();

        List<String> entCodes = enterpriseService.getEntCodes();
        if (!entCodes.isEmpty()) {
//            List<OrderInfo> orderInfos = orderDao.getCustomerHistoryBoxes(customId, tel, entCodes, orderId);
            List<BoxInfo> boxInfos = boxOperateDao.getCustomerHistoryBoxes(customId, tel, orderId, entCodes);
            BoxInfoVO boxInfoVO = null;
            OrderDO orderDO = null;
            FilterExample fe = new FilterExample();
            for (BoxInfo boxInfo : boxInfos) {
                orderDO = null;
                fe.clear();
                fe.createCriteria().andFieldEqualTo("order_id", boxInfo.getOrderId());
                try {
                    Optional<OrderDO> optional = get(fe);
                    if (optional.isPresent()) {
                        orderDO = optional.get();
                    }
                } catch (Exception e) {
                    logger.error("获取订单信息失败", e);
                }
                if (orderDO == null) {
                    continue;
                }

                boxInfoVO = new BoxInfoVO();
                boxInfoVO.setRfid(boxInfo.getRfid());
                boxInfoVO.setStatus(boxInfo.getStatus());
                boxInfoVO.setSignStatus(boxInfo.getSignStatus());
                if (boxInfo.getSignStatus().byteValue() == SignStatus.SIGN_IN.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("已签收");
                } else if (boxInfo.getSignStatus().byteValue() == SignStatus.DENIED_SIGN.getIndex().byteValue()) {
                    boxInfoVO.setStatusDescr("拒绝签收");
                }
                boxInfoVO.setCreateTime(orderDO.getCreateTime());
                boxInfoVO.setOperateTime(boxInfo.getOperateTime());
                boxInfoVO.setOrderId(boxInfo.getOrderId());
                boxInfoVO.setReceiver(orderDO.getReceiverName());
                String receiverAddress = orderDO.getReceiverAddress();
                String[] receiverAddressArry = receiverAddress.split(" ");
                if (receiverAddressArry.length == 4) {
                    if (receiverAddressArry[0] != null && specialCity.contains(receiverAddressArry[0])) {
                        boxInfoVO.setReceiverArea(receiverAddressArry[0]);
                    } else {
                        boxInfoVO.setReceiverArea(receiverAddressArry[1]);
                    }
                }

                boxInfoVO.setSender(orderDO.getSenderName());
                String senderAddress = orderDO.getSenderAddress();
                String[] senderAddressArry = senderAddress.split(" ");
                if (senderAddressArry.length == 4) {
                    if (senderAddressArry[0] != null && specialCity.contains(senderAddressArry[0])) {
                        boxInfoVO.setSenderArea(senderAddressArry[0]);
                    } else {
                        boxInfoVO.setSenderArea(senderAddressArry[1]);
                    }
                }

                if (orderDO.getCustomerId().equals(customId)) {
                    boxInfoVO.setDirection((byte) 1); // 企业订单全部为"寄"
                } else if (orderDO.getReceiverTel().equals(tel)){
                    boxInfoVO.setDirection((byte) 2); // 企业订单全部为"收"
                }

                boxInfoVOS.add(boxInfoVO);
            }
        }

        return boxInfoVOS;
    }

//    /**
//     * 获取客户待揽货订单信息
//     * @param customId
//     * @return
//     */
//    @Override
//    public List<OrderInfo> getUndoOrders(String customId) {
//        List<OrderInfo> orderInfos = null;
//        try {
//            List<String> entCodes = enterpriseService.getEntCodes();
//            orderInfos = orderDao.getUndoOrders(customId, entCodes);
//        } catch (Exception e) {
//            logger.error(String.format("获取客户(%s)待揽货订单出现异常", customId));
//        }
//        if (orderInfos == null) {
//            orderInfos = Lists.newArrayList();
//        }
//        return orderInfos;
//    }

//    public List<OrderInfo> getCustomerCurrentOrder

    /**
     * 获取我的包装箱
     * @param customId
     * @param tel
     * @return
     */
    @Override
    public List<BoxInfoVO> getMyBoxes(String customId, String tel) {
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();

        try {
            List<String> entCodes = enterpriseService.getEntCodes();
            if (!entCodes.isEmpty()) {
                BoxInfoVO boxInfoVO = null;
                BoxTypeInfo boxTypeInfo = null;
                BoxTypeVO boxTypeVO = null;
                List<BoxInfo> boxInfosC = boxOperateDao.getMyBoxesForC(tel, entCodes);
                List<BoxInfo> boxInfosB = boxOperateDao.getMyBoxesForB(customId, entCodes);

                boxInfosC.addAll(boxInfosB);
                for (BoxInfo boxInfo : boxInfosC) {
                    boxInfoVO = new BoxInfoVO();
                    boxInfoVO.setRfid(boxInfo.getRfid());
                    boxInfoVO.setOrderId(boxInfo.getOrderId());
//                    Long duration = (DateTime.now().getMillis() - boxInfo.getCreateTime().getTime()) / 1000 / 60 / 60;
                    Period p = new Period(new DateTime(boxInfo.getOperateTime()), DateTime.now(), PeriodType.minutes());
                    int hour = p.getMinutes() / 60;
                    int minute = p.getMinutes() % 60;
                    String duration = hour + "小时" + minute + "分钟";

                    boxInfoVO.setDuration(duration);

                    boxTypeInfo = boxOperateDao.getBoxTypeByBoxId(boxInfo.getRfid());
                    boxTypeVO = new BoxTypeVO();
                    if (boxTypeInfo != null) {
                        boxTypeVO.setTypeId(boxTypeInfo.getTypeId());
                        boxTypeVO.setName(boxTypeInfo.getName());
                        boxTypeVO.setSize(boxTypeInfo.getSize());
                    }
                    boxInfoVO.setBoxTypeVO(boxTypeVO);

                    // 回收状态获取
                    long infoCount = recycleApplyInfoService.countInfo(customId, boxInfo.getRfid());
                    if (infoCount > 0) {
                        boxInfoVO.setRecycleType(RecycleState.UN_DO.getIndex().byteValue());
                    } else {
                        boxInfoVO.setRecycleType((byte) 10000);
                    }

                    boxInfoVOS.add(boxInfoVO);
                }
            }
        } catch (Exception e) {
            logger.error(String.format("获取我的(%s,%s)包装箱信息出现异常", customId, tel), e);
        }
        return boxInfoVOS;
    }

    /**
     * 获取我的包装箱数量
     * @param customId
     * @param tel
     * @return
     */
    @Override
    public Integer getMyBoxesCount(String customId, String tel) {
        int totalCount = 0;
        try {
            List<String> entCodes = enterpriseService.getEntCodes();
            if (!entCodes.isEmpty()) {
                int countForC = boxOperateDao.getMyBoxesCountForC(tel, entCodes);
                int countForB = boxOperateDao.getMyBoxesCountForB(customId, entCodes);
                totalCount = countForB + countForC;
            }
        } catch (Exception e) {
            logger.error(String.format("获取我的(%s,%s)包装箱数量出现异常", customId, tel), e);
        }
        return totalCount;
    }

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @Override
    public boolean deleteOrder(String orderId) {
        boolean res = false;
        try {
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("order_id", orderId);
            long resRemove = remove(fe);
            if (resRemove > 0) {
                res = true;
            } else {
                logger.error(String.format("删除订单(%s)失败", orderId));
            }
        } catch (Exception e) {
            logger.error(String.format("删除订单(%s)出现异常", orderId));
        }
        return res;
    }

    /**
     * 获取订单状态
     * @param orderId
     * @return
     */
    @Override
    public byte getOrderState(String orderId) {
        byte orderState = -1;
        try {
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("order_id", orderId);
            Optional<OrderDO> orderDOOptional = get(fe);
            if (orderDOOptional.isPresent()) {
                orderState = orderDOOptional.get().getState();
            }
        } catch (Exception e) {
            logger.error(String.format("获取订单(%s)状态出现异常", orderId));
        }
        return orderState;
    }

    /**
     * 更新订单状态
     * @param orderId
     * @param orderState
     */
    @Override
    public void updateOrderState(String orderId, byte orderState) {
        OrderDO orderInfo = new OrderDO();
        orderInfo.setState(orderState);
        orderInfo.setModifyTime(new Date());
        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("order_id", orderId);
        long updateRes = update(orderInfo, fe);
        if (updateRes < 1) {
            logger.warn(String.format("更新订单(%s)状态为状态(%d)出现错误", orderId, orderState));
        }
    }

//    /**
//     * 获取状态描述
//     * @param orderInfo
//     * @return
//     */
//    private String getStatusDescr(OrderInfo orderInfo) {
//        String statusDescr = "";
//        if (orderInfo.getState().byteValue() == OrderState.WAITING_ASSIGN.getIndex().byteValue()) {
//            statusDescr = OrderState.WAITING_ASSIGN.getDescr();
//        } else if (orderInfo.getState().byteValue() == OrderState.WAITING_TAKE_IN.getIndex().byteValue()) {
//            statusDescr = OrderState.WAITING_TAKE_IN.getDescr();
//        } else if (orderInfo.getState().byteValue() == OrderState.WAITING_ASSIGN_FAILED.getIndex().byteValue()) {
//            statusDescr = OrderState.WAITING_ASSIGN_FAILED.getDescr();
//        } else if (orderInfo.getState().byteValue() == OrderState.DONE.getIndex().byteValue()) {
//            if (orderInfo.getBoxStatus().equals(String.valueOf(BoxStatus.BINDING.getIndex()))) {
//                statusDescr = "运输中";
//            } else if (orderInfo.getBoxStatus().equals(String.valueOf(BoxStatus.TRANSPORTING.getIndex())) || orderInfo.getBoxStatus().equals(String.valueOf(BoxStatus.TRANSIT.getIndex()))) {
//                statusDescr = "运输中";
//            } else if (orderInfo.getBoxStatus().equals(String.valueOf(BoxStatus.RETENTION.getIndex()))) {
//                statusDescr = "已签收";
//            }
//        }
//        return statusDescr;
//    }

}
