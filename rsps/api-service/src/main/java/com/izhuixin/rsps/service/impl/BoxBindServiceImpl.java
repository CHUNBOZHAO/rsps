package com.izhuixin.rsps.service.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.constant.*;
import com.izhuixin.rsps.common.dba.BaseAbstractService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.common.util.CustomIdBuilder;
import com.izhuixin.rsps.common.util.PasswordUtils;
import com.izhuixin.rsps.common.vo.web.OperatorInfoVO;
import com.izhuixin.rsps.common.vo.wms.Assignment;
import com.izhuixin.rsps.common.vo.wms.BoxBindingInfo;
import com.izhuixin.rsps.common.vo.wms.DriverInfo;
import com.izhuixin.rsps.common.vo.wms.DrugInfo;
import com.izhuixin.rsps.domain.automatic.CustomInfoDO;
import com.izhuixin.rsps.domain.automatic.OrderDO;
import com.izhuixin.rsps.domain.manual.*;
import com.izhuixin.rsps.service.*;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;

@Service
public class BoxBindServiceImpl extends BaseAbstractService implements BoxBindService {

    @Autowired
    private BoxBaseService boxBaseService;

    @Autowired
    private BoxInfoService boxInfoService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomInfoService customInfoService;

    @Autowired
    private OperatorInfoService operatorInfoService;

    @Autowired
    private OrderDriverService orderDriverService;

    @Autowired
    private BoxRecordService boxRecordService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 绑定包装箱
     *
     * @param boxBindingInfo
     * @return
     */
    @Override
    public String bindBox(final BoxBindingInfo boxBindingInfo) {

        if (logger.isDebugEnabled()) {
            logger.debug("Bind Box --->>" + new Gson().toJson(boxBindingInfo));
        }

        final String entCode = boxBindingInfo.getEntCode();
//        final byte accessWay = enterpriseService.getAccessWayByEntCode(entCode.replace("_",""));

        return transactionTemplate.execute(transactionStatus -> {
            JsonObject resJo = new JsonObject();
            resJo.addProperty("returnCode", ErrorCode.ERROR.getIndex());
            resJo.addProperty("returnMsg", ErrorCode.ERROR.getDescr());

            byte accessWay = 0;
            if (boxBindingInfo.getAccessType() == null) {
                accessWay = AccessType.TWO.getIndex().byteValue();
            } else {
                accessWay = boxBindingInfo.getAccessType().byteValue();
            }

            String rfid = "";
            try {
                boolean handleRes = handleBindInfoValid(boxBindingInfo);
                if (!handleRes) {
                    // 判断需要绑定的箱子是否入库
                    FilterExample fe = new FilterExample();
                    if (StringUtils.isNotEmpty(boxBindingInfo.getBoxId())) {
                        rfid = boxBaseService.getBoxId(boxBindingInfo.getBoxId());
                    } else if (StringUtils.isNotEmpty(boxBindingInfo.getBleMac())) {
                        rfid = boxBaseService.getBoxIdByBle(boxBindingInfo.getBleMac());
                        boxBindingInfo.setBoxId(rfid);
                    }

                    // 检测包装箱是否属于该企业
                    BoxInfo checkedBoxInfo = boxInfoService.getBoxInfoByRfid(rfid, entCode);
                    if (checkedBoxInfo == null) {
                        rfid = "";
                    }

                    if (StringUtils.isNotBlank(rfid)) {

                        BoxInfo boxInfo = new BoxInfo();
                        boxInfo.setBarcode(boxBindingInfo.getBarcodeId());
                        boxInfo.setOrderId(boxBindingInfo.getOrderId());
                        boxInfo.setStatus(BoxStatus.BINDING.getIndex().byteValue()); // 设置包装箱状态为"配货"

                        // C端揽货
                        if (boxBindingInfo.getOrderType() != null && boxBindingInfo.getOrderType().equals(String.valueOf(OrderType.CONSUMER.getIndex()))) {
                            boxInfo.setOperatorId("");
                            boxInfo.setOperator(boxBindingInfo.getRecheckerName());
                        } else {
                            boxInfo.setOperatorId(boxBindingInfo.getRecheckerId());
                            boxInfo.setOperator(boxBindingInfo.getRecheckerName());
                        }

                        boxInfo.setOperateTime(new Date());
                        boxInfo.setOperateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                        boxInfo.setUpdateTime(new Date());
                        boxInfo.setUpdateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                        boxInfo.setBoxName(boxBindingInfo.getBoxName());
                        boxInfo.setBindTime(new Date());
                        boxInfo.setBindTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                        boxInfo.setDetail(handleDrugs(boxBindingInfo.getDrugs()));
                        boxInfo.setSignStatus((byte)0); // 设置签收状态为未签收
                        boxInfo.setRfid(rfid);
                        boxInfo.setBeginTransferId("0");

                        boolean updateBoxRes = boxInfoService.updateBoxInfoByRfid(boxInfo, entCode);

                        if (updateBoxRes) {
                            // C端订单不需要在对应企业生成记录
                            if (boxBindingInfo.getOrderType() != null && boxBindingInfo.getOrderType().equals(String.valueOf(OrderType.CONSUMER.getIndex()))) {

                                // 绑定订单，订单状态修改为等待分配派送...
                                fe.clear();
                                fe.createCriteria().andFieldEqualTo("order_id", boxBindingInfo.getOrderId());
                                OrderDO orderDO = new OrderDO();
                                orderDO.setState(OrderState.WAITING_ALLOT_COURIER.getIndex().byteValue());
                                orderDO.setModifyTime(new Date());
                                orderService.update(orderDO, fe);

                                // 记录包装箱变更状态 -- 揽货成功
                                BoxRecordInfo boxRecord = new BoxRecordInfo();
                                boxRecord.setRfid(rfid);
                                boxRecord.setOrderId(boxBindingInfo.getOrderId());
                                boxRecord.setOperatorId(boxBindingInfo.getRecheckerId());
                                boxRecord.setOperator(boxBindingInfo.getRecheckerName());
                                boxRecord.setOperateType(OperateType.TASK_IN.getIndex().byteValue());
                                boxRecord.setCreateTime(new Date());
                                boxRecord.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                                boxRecordService.saveBoxRecord(boxRecord, entCode);

                                // 直接返回
                                resJo.addProperty("returnCode", "0");
                                resJo.addProperty("returnMsg", "ok");
                            } else {

                                // 记录包装箱变更状态
                                BoxRecordInfo boxRecord = new BoxRecordInfo();
                                boxRecord.setRfid(rfid);
                                boxRecord.setOrderId(boxBindingInfo.getOrderId());
                                boxRecord.setOperatorId(boxBindingInfo.getRecheckerId());
                                boxRecord.setOperator(boxBindingInfo.getRecheckerName());
                                boxRecord.setOperateType(BoxStatus.BINDING.getIndex().byteValue());
                                boxRecord.setCreateTime(new Date());
                                boxRecord.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

                                long recordCount = boxRecordService.countBoxRecord(rfid, boxBindingInfo.getOrderId(), BoxStatus.BINDING.getIndex().byteValue(), entCode);
                                if (recordCount > 0) {
                                    boxRecordService.updateBoxRecord(boxRecord, rfid, boxBindingInfo.getOrderId(), BoxStatus.BINDING.getIndex().byteValue(), entCode);
                                } else {
                                    boxRecordService.saveBoxRecord(boxRecord, entCode);
                                }

                                // 订单
                                long orderCount = orderService.countOrderById(boxBindingInfo.getOrderId(), entCode);


                                OrderInfo orderInfo = new OrderInfo();
                                orderInfo.setOrderId(boxBindingInfo.getOrderId());

                                // 客户联系人信息
                                if (boxBindingInfo.getCustomer() != null) {

                                    if (StringUtils.isNotBlank(boxBindingInfo.getCustomer().getId())) {

                                        // 获取企业ID
                                        String entId = enterpriseService.getEntIdByEntCode(entCode.replaceAll("_",""));

                                        // 客户信息
                                        fe.clear();
                                        fe.createCriteria().andFieldEqualTo("custom_id", boxBindingInfo.getCustomer().getId())
                                                .andFieldEqualTo("status", ValidStatus.VALID.getIndex());
                                        long customCount = customInfoService.count(fe);

                                        CustomInfoDO customInfoDO = new CustomInfoDO();
                                        customInfoDO.setCustomId(boxBindingInfo.getCustomer().getId());
                                        customInfoDO.setCustomName(boxBindingInfo.getCustomer().getName());
                                        customInfoDO.setCustomAddress(boxBindingInfo.getCustomer().getAddress());
                                        customInfoDO.setEntCustomId(boxBindingInfo.getCustomer().getId());
                                        customInfoDO.setCustomType(CustomType.BUSINESS.getIndex().byteValue());
                                        customInfoDO.setEntId(entId);
                                        if (customCount > 0) {  // 更新
                                            customInfoDO.setModifyTime(new Date());

                                            fe.clear();
                                            fe.createCriteria().andFieldEqualTo("custom_id", boxBindingInfo.getCustomer().getId());
                                            customInfoService.update(customInfoDO, fe);
                                        } else {  // 新增
                                            customInfoDO.setModifyTime(new Date());
                                            customInfoDO.setCreateTime(new Date());
                                            customInfoDO.setStatus(ValidStatus.VALID.getIndex().byteValue());
                                            customInfoDO.setCustomPwd(PasswordUtils.md5(Constants.CUSTOMER_DEFAULT_PASSWORD));
                                            customInfoService.save(customInfoDO);
                                        }
                                    } else {
                                        fe.clear();
                                        fe.createCriteria().andFieldEqualTo("tel", boxBindingInfo.getCustomer().getPhone()).andFieldEqualTo("status", ValidStatus.VALID.getIndex());
                                        Optional<CustomInfoDO> optional = customInfoService.get(fe);
                                        if (!optional.isPresent()) {  // 如果不存在,则新增记录
                                            CustomInfoDO customInfoDO = new CustomInfoDO();
                                            customInfoDO.setCustomId(CustomIdBuilder.geneate());
                                            customInfoDO.setCustomName(boxBindingInfo.getCustomer().getName());
                                            customInfoDO.setCustomAddress(boxBindingInfo.getCustomer().getAddress());
                                            customInfoDO.setModifyTime(new Date());
                                            customInfoDO.setCreateTime(new Date());
                                            customInfoDO.setStatus(ValidStatus.VALID.getIndex().byteValue());
                                            customInfoDO.setCustomPwd(PasswordUtils.md5(Constants.CUSTOMER_DEFAULT_PASSWORD));
                                            customInfoDO.setTel(boxBindingInfo.getCustomer().getPhone());
                                            customInfoService.save(customInfoDO);
                                        }
                                    }

                                    // 订单信息
                                    orderInfo.setCustomerId(boxBindingInfo.getCustomer().getId());
                                    orderInfo.setCustomer(boxBindingInfo.getCustomer().getName());
                                }

                                boolean orderOperateRes = false;
                                if (orderCount > 0) { // 更新
                                    orderInfo.setState(OrderState.DONE.getIndex().byteValue());
                                    orderInfo.setModifyTime(new Date());
                                    orderInfo.setModifyTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                                    orderOperateRes = orderService.updateOrder(orderInfo, orderInfo.getOrderId(), entCode);
                                } else {  // 新增
                                    orderInfo.setState(OrderState.DONE.getIndex().byteValue());
                                    orderInfo.setStatus(ValidStatus.VALID.getIndex().byteValue());
                                    orderInfo.setCreateTime(new Date());
                                    orderInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                                    orderInfo.setModifyTime(new Date());
                                    orderInfo.setModifyTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                                    orderOperateRes = orderService.saveOrder(orderInfo, entCode);
                                }

                                if (orderOperateRes) {  // 订单保存，更新成功继续
                                    if (accessWay == AccessType.ONE.getIndex().byteValue()) {  // 如果接入方式为1，这直接调用派货接口
                                        Assignment assignment = new Assignment();
                                        assignment.setOrderId(boxBindingInfo.getOrderId());

                                        List<DriverInfo> driverInfos = Lists.newArrayList();
                                        OperatorInfo operatorInfo = operatorInfoService.getOperatorInfoByCustomId(boxBindingInfo.getCustomer().getId(), entCode);  // 通过客户配置线路获取配送员
                                        if (operatorInfo != null) {
                                            DriverInfo driverInfo = new DriverInfo();
                                            driverInfo.setId(operatorInfo.getOperatorId());
                                            driverInfo.setPhone(operatorInfo.getTel());
                                            driverInfo.setOrder(1);
                                            driverInfo.setSex(operatorInfo.getSex().intValue());
                                            driverInfo.setName(operatorInfo.getName());
                                            if (operatorInfo.getAge() != null) {
                                                driverInfo.setAge(operatorInfo.getAge().intValue());
                                            } else {
                                                driverInfo.setAge(0);
                                            }
                                            driverInfo.setLastModify(String.valueOf(DateTime.now().getMillis()));
                                            driverInfos.add(driverInfo);
                                        } else {
                                            OperatorInfoVO operatorInfoVO = operatorInfoService.getInfoById(1l, entCode);
                                            DriverInfo driverInfo = new DriverInfo();
                                            driverInfo.setId(operatorInfoVO.getOperatorId());
                                            driverInfo.setPhone(operatorInfoVO.getTel());
                                            driverInfo.setOrder(1);
                                            driverInfo.setSex(operatorInfoVO.getSex().intValue());
                                            driverInfo.setName(operatorInfoVO.getUserName());
                                            if (operatorInfoVO.getAge() != null) {
                                                driverInfo.setAge(operatorInfoVO.getAge().intValue());
                                            } else {
                                                driverInfo.setAge(0);
                                            }
                                            driverInfo.setLastModify(String.valueOf(DateTime.now().getMillis()));
                                            driverInfos.add(driverInfo);
                                        }

                                        assignment.setDrivers(driverInfos);

                                        JsonObject jsonObject = handleAssignBox(assignment, entCode, null); // 调用派货接口
                                        if (!jsonObject.get("returnCode").getAsString().equals("0")) {  // 有异常了
                                            transactionStatus.setRollbackOnly();
                                        }
                                    } else {
                                        logger.info(String.format("包装箱(%s)绑定订单(%s)不进行派货", boxBindingInfo.getBoxId(), boxBindingInfo.getOrderId()));
                                    }

                                    resJo.addProperty("returnCode", "0");
                                    resJo.addProperty("returnMsg", "ok");
                                } else {
                                    transactionStatus.setRollbackOnly();
                                    resJo.addProperty("returnCode", "10002");
                                    resJo.addProperty("returnMsg", "绑定包装箱出现异常");
                                    logger.error(String.format("生成订单出现异常"));
                                }
                            }

                        } else {
                            transactionStatus.setRollbackOnly();
                            resJo.addProperty("returnCode", "10002");
                            resJo.addProperty("returnMsg", "绑定包装箱出现异常");
                            logger.error(String.format("更新包装箱状态出现异常"));
                        }
                    } else {
                        transactionStatus.setRollbackOnly();
                        resJo.addProperty("returnCode", "10001");
                        resJo.addProperty("returnMsg", "包装箱未进行注册");
                        logger.error(String.format("包装箱(%s)未进行注册", boxBindingInfo.getBoxId()));
                    }
                } else {
                    resJo.addProperty("returnCode", "10003");
                    resJo.addProperty("returnMsg", "绑定消息数据格式错误");
                    logger.error(String.format("绑定包装箱(%s)消息数据格式错误", boxBindingInfo.getBoxId()));
                }
            } catch (Exception e) {
                transactionStatus.setRollbackOnly();
                logger.error(String.format("条码（%s）绑定包装箱（%s）出现异常", boxBindingInfo.getBarcodeId() == null ? "":boxBindingInfo.getBarcodeId() , boxBindingInfo.getBoxId()), e);
                resJo.addProperty("returnCode", "10002");
                resJo.addProperty("returnMsg", "绑定包装箱出现异常");
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Bind Box Ack <<----" + resJo.toString());
            }

            return resJo.toString();
        });

    }

    /**
     * 派货
     *
     * @param assignment
     * @return
     */
    @Override
    public String assignBox(final Assignment assignment) {

        if (logger.isDebugEnabled()) {
            logger.debug("Assign Box --->>" + new Gson().toJson(assignment));
        }

        final String entCode = assignment.getEntCode();

        return transactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus transactionStatus) {
                JsonObject resJo = new JsonObject();
                try {
                    resJo = handleAssignBox(assignment, entCode, null);

                    resJo.addProperty("returnCode", "0");
                    resJo.addProperty("returnMsg", "ok");
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    logger.error(String.format("派货（%s）出现异常", assignment.getOrderId()), e);
                    resJo.addProperty("returnCode", "10003");
                    resJo.addProperty("returnMsg", "派货出现异常");
                }

                if (logger.isDebugEnabled()) {
                    logger.debug("Assign Box Ack <<---" + resJo.toString());
                }

                return resJo.toString();
            }
        });
    }

    /**
     * 处理派货信息
     * @param assignment
     * @param entCode
     * @return
     */
    private JsonObject handleAssignBox(Assignment assignment, String entCode, Byte orderType) {

        JsonObject resJo = new JsonObject();
        long orderCount = 0l;
        if (orderType != null && orderType == OrderType.CONSUMER.getIndex().byteValue()) {
            orderCount = orderService.countOrderById(assignment.getOrderId(), "");
        } else {
            orderCount = orderService.countOrderById(assignment.getOrderId(), entCode);
        }
        if (orderCount <= 0) {
            resJo.addProperty("returnCode", "10004");
            resJo.addProperty("returnMsg", "派货订单未绑定");
        } else {

            // 派货信息
            OrderDriverInfo orderDriverInfo = null;

            // 司机信息
            List<DriverInfo> driverInfos = assignment.getDrivers();
            OperatorInfo operatorInfo = null;
            String prevDriverId = "";
            String prevDriverName = "";
            for (int i = driverInfos.size() - 1; i >= 0; i--) {  // 倒序为了获取当前司机下一个中转司机
                long operatorCount = operatorInfoService.countOperator(driverInfos.get(i).getId(), entCode);

                operatorInfo = new OperatorInfo();
                operatorInfo.setOperatorId(driverInfos.get(i).getId());
//              operatorInfo.setUserName(driverInfos.get(i).getName().concat(driverInfos.get(i).getId()));  // 登录用户名为名称+编号
                operatorInfo.setName(driverInfos.get(i).getName());
                operatorInfo.setTel(driverInfos.get(i).getPhone());
                operatorInfo.setType(OperatorType.DRIVER.getIndex().byteValue());
                operatorInfo.setStatus(ValidStatus.VALID.getIndex().byteValue());
                operatorInfo.setAge(driverInfos.get(i).getAge() == null ? 0 : driverInfos.get(i).getAge().byteValue());
                operatorInfo.setSex(driverInfos.get(i).getSex() == null ? 0 : driverInfos.get(i).getSex().byteValue());
                if (operatorCount > 0) { // 更新
                    operatorInfo.setModifyTime(new Date());
                    operatorInfo.setModifyTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                    operatorInfoService.updateOperatorByOperatorId(operatorInfo, entCode);
                } else {  // 新增
                    operatorInfo.setUserName(operatorInfoService.generateUserName(driverInfos.get(i).getName(), driverInfos.get(i).getId()));
                    operatorInfo.setCreateTime(new Date());
                    operatorInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                    operatorInfo.setModifyTime(new Date());
                    operatorInfo.setModifyTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                    operatorInfo.setUserPwd(PasswordUtils.md5(Constants.OPERATOR_DEFAULT_PASSWORD));
//                                operatorInfoService.save(operatorInfoDO);
                    operatorInfoService.saveOperator(operatorInfo, entCode);
                }

                // 派货信息
                orderDriverInfo = new OrderDriverInfo();
                orderDriverInfo.setOrderId(assignment.getOrderId());
                orderDriverInfo.setDriverId(driverInfos.get(i).getId());
                orderDriverInfo.setDriverName(driverInfos.get(i).getName());
                orderDriverInfo.setDriverPhone(driverInfos.get(i).getPhone());
                orderDriverInfo.setDriverOrder(driverInfos.get(i).getOrder().byteValue());
                orderDriverInfo.setNextDriverId(prevDriverId);
                orderDriverInfo.setTransportType(TransportType.DELIVERY.getIndex().byteValue());

                long orderDriverCount = orderDriverService.countOrderDriver(assignment.getOrderId(), driverInfos.get(i).getId(), entCode);

                if (orderDriverCount > 0) { // 更新
//                                orderDriverService.update(orderDriverDO, fe);
                    orderDriverService.updateByOrderIdAndDriverId(orderDriverInfo, entCode);
                } else {  // 新增
                    orderDriverInfo.setCreateTime(new Date());
                    orderDriverInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                    orderDriverService.saveOrderDriver(orderDriverInfo, entCode);
//                                orderDriverService.save(orderDriverDO);
                }

                prevDriverId = driverInfos.get(i).getId();
                prevDriverName = driverInfos.get(i).getName();
            }

            List<BoxInfo> boxInfos = boxInfoService.getBoxInfoByOrderid(assignment.getOrderId(), entCode);

            for (BoxInfo boxInfo : boxInfos) {
                boxInfo.setOperatorId(prevDriverId);
                boxInfo.setOperator(prevDriverName);
                boxInfo.setOrderId(assignment.getOrderId());
                boxInfoService.updateBoxInfoByOrderId(boxInfo, entCode);
            }

            resJo.addProperty("returnCode", "0");
            resJo.addProperty("returnMsg", "ok");
        }
        return resJo;
    }

    /**
     * 检测绑定消息的正确信息
     * @param boxBindingInfo
     * @return
     */
    private boolean handleBindInfoValid(BoxBindingInfo boxBindingInfo) {
        if (boxBindingInfo.getBarcodeId() != null && boxBindingInfo.getBarcodeId().length() > 48) {
            logger.error(String.format("绑定条码(%s)长度超过48～", boxBindingInfo.getBarcodeId()));
            return true;
        }
        if (boxBindingInfo.getBoxId() != null && boxBindingInfo.getBoxId().length() > 48) {
            logger.error(String.format("包装箱ID(%s)长度超过48～", boxBindingInfo.getBoxId()));
            return true;
        }
        if (boxBindingInfo.getBoxName() != null && boxBindingInfo.getBoxName().length() > 30) {
            boxBindingInfo.setBoxName(boxBindingInfo.getBoxName().substring(0, 30));
        }
        if (boxBindingInfo.getHandleType() != null && boxBindingInfo.getHandleType().length() > 8) {
            logger.error(String.format("绑定信息类型(%s)长度超过8～", boxBindingInfo.getHandleType()));
            return true;
        }
        if (boxBindingInfo.getOrderId() != null && boxBindingInfo.getOrderId().length() > 48) {
            logger.error(String.format("订单号(%s)长度超过48～", boxBindingInfo.getOrderId()));
            return true;
        }
        if (boxBindingInfo.getRecheckerId() != null && boxBindingInfo.getRecheckerId().length() > 24) {
            logger.error(String.format("复核人ID(%s)长度超过24～", boxBindingInfo.getRecheckerId()));
            return true;
        }
        if (boxBindingInfo.getRecheckerName() != null && boxBindingInfo.getRecheckerName().length() > 24) {
            boxBindingInfo.setRecheckerName(boxBindingInfo.getRecheckerName().substring(0,24));
        }
        if (boxBindingInfo.getRecheckerPhone() != null && boxBindingInfo.getRecheckerPhone().length() > 24) {
            logger.error(String.format("复核人电话(%s)长度超过24～", boxBindingInfo.getRecheckerPhone()));
            return true;
        }
        return false;
    }

    /**
     * 处理药品信息
     * @param drugs
     * @return
     */
    private String handleDrugs(List<DrugInfo> drugs) {
        List<DrugInfo> newDrugs = Lists.newArrayList();
        if (drugs != null) {
            DrugInfo newDrugInfo = null;
            int loop = 0;
            for (DrugInfo drugInfo : drugs) {
                if (loop > 30) {  // 计算大概超过30个种类药品清单，就忽略
                    break;
                }
                newDrugInfo = new DrugInfo();
                if (drugInfo.getName() != null && drugInfo.getName().length() > 30) {
                    newDrugInfo.setName(drugInfo.getName().substring(0,30));
                } else {
                    newDrugInfo.setName(drugInfo.getName());
                }
                if (drugInfo.getAmount() != null && drugInfo.getAmount().length() > 10) {
                    newDrugInfo.setAmount(drugInfo.getAmount().substring(0,10));
                } else {
                    newDrugInfo.setAmount(drugInfo.getAmount());
                }
                if (drugInfo.getManufacturer() != null && drugInfo.getManufacturer().length() > 80) {
                    newDrugInfo.setManufacturer(drugInfo.getManufacturer().substring(0,80));
                } else {
                    newDrugInfo.setManufacturer(drugInfo.getManufacturer());
                }
                if (drugInfo.getUnit() != null && drugInfo.getUnit().length() > 10) {
                    newDrugInfo.setUnit(drugInfo.getUnit().substring(0,10));
                } else {
                    newDrugInfo.setUnit(drugInfo.getUnit());
                }
                newDrugs.add(newDrugInfo);
                loop++;
            }
        }

        String drugsStr = "";
        try {
            drugsStr = new Gson().toJson(newDrugs);
        } catch (Exception e) {
            logger.error("解析药品清单出现异常");
        }

        return drugsStr;
    }

    /**
     * 处理C端用户绑定业务 -- 分配派货人员
     * @param entCode
     */
    @Override
    public boolean handleCbindBusi(final String orderId, final String entCode, final String boxId, final OperatorInfo operatorInfo) {

        if (operatorInfo == null) {  // 给定的派货人为null，则直接失败
            return false;
        }

        boolean res = transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                boolean resTran = false;
                try {
                    FilterExample fe = new FilterExample();
                    fe.createCriteria().andFieldEqualTo("order_id", orderId);

                    OrderDO orderDO = new OrderDO();
                    orderDO.setState(OrderState.DONE.getIndex().byteValue());
                    orderDO.setModifyTime(new Date());
                    orderService.update(orderDO, fe);


                    Assignment assignment = new Assignment();
                    assignment.setOrderId(orderId);

                    List<DriverInfo> driverInfos = Lists.newArrayList();
                    DriverInfo driverInfo = new DriverInfo();
                    driverInfo.setId(operatorInfo.getOperatorId());
                    driverInfo.setPhone(operatorInfo.getTel() != null ? operatorInfo.getTel() : "");
                    driverInfo.setOrder(1);
                    driverInfo.setSex(operatorInfo.getSex() != null ? operatorInfo.getSex().intValue() : 0);
                    driverInfo.setName(operatorInfo.getUserName());
                    if (operatorInfo.getAge() != null) {
                        driverInfo.setAge(operatorInfo.getAge().intValue());
                    } else {
                        driverInfo.setAge(0);
                    }
                    driverInfo.setLastModify(String.valueOf(DateTime.now().getMillis()));
                    driverInfos.add(driverInfo);

                    assignment.setDrivers(driverInfos);

                    JsonObject jsonObject = handleAssignBox(assignment, entCode, OrderType.CONSUMER.getIndex().byteValue()); // 调用派货接口
                    if (!jsonObject.get("returnCode").getAsString().equals("0")) {  // 有异常了
                        transactionStatus.setRollbackOnly();
                    } else {
                        // 记录包装箱变更状态
                        BoxRecordInfo boxRecord = new BoxRecordInfo();
                        boxRecord.setRfid(boxId);
                        boxRecord.setOrderId(orderId);
                        boxRecord.setOperatorId(operatorInfo.getOperatorId());
                        boxRecord.setOperator(operatorInfo.getUserName());
                        boxRecord.setOperateType(OperateType.ASSIGN.getIndex().byteValue());
                        boxRecord.setCreateTime(new Date());
                        boxRecord.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

                        long recordCount = boxRecordService.countBoxRecord(boxId, orderId, BoxStatus.BINDING.getIndex().byteValue(), entCode);
                        if (recordCount > 0) {
                            boxRecordService.updateBoxRecord(boxRecord, boxId, orderId, BoxStatus.BINDING.getIndex().byteValue(), entCode);
                        } else {
                            boxRecordService.saveBoxRecord(boxRecord, entCode);
                        }
                        resTran = true;
                    }

                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                    logger.error(String.format("订单(%s)分配派货人员出现异常", orderId), e);
                }
                return resTran;
            }
        });
        return res;
    }

//    /**
//     * 处理C端用户绑定业务
//     * @param boxBindingInfo
//     * @param entCode
//     * @param resJo
//     * @param transactionStatus
//     */
//    private void handleCbindBusi(BoxBindingInfo boxBindingInfo,
//                                 String entCode,
//                                 JsonObject resJo,
//                                 TransactionStatus transactionStatus,
//                                 String boxId) {
//
//        FilterExample fe = new FilterExample();
//        fe.createCriteria().andFieldEqualTo("order_id", boxBindingInfo.getOrderId());
//
//        OrderDO orderDO = new OrderDO();
//        orderDO.setState(OrderState.DONE.getIndex().byteValue());
//        orderDO.setModifyTime(new Date());
//        orderService.update(orderDO, fe);
//
//        List<OperatorInfoVO> operatorInfos = operatorInfoService.getOperators(entCode);
//        if (!operatorInfos.isEmpty()) {
//            OperatorInfoVO operatorInfo = operatorInfos.get(0);
//
//            // 自动分配给第一个配送员
//            Assignment assignment = new Assignment();
//            assignment.setOrderId(boxBindingInfo.getOrderId());
//
//            List<DriverInfo> driverInfos = Lists.newArrayList();
//            DriverInfo driverInfo = new DriverInfo();
//            driverInfo.setId(operatorInfo.getOperatorId());
//            driverInfo.setPhone(operatorInfo.getTel());
//            driverInfo.setOrder(1);
//            driverInfo.setSex(operatorInfo.getSex().intValue());
//            driverInfo.setName(operatorInfo.getUserName());
//            if (operatorInfo.getAge() != null) {
//                driverInfo.setAge(operatorInfo.getAge().intValue());
//            } else {
//                driverInfo.setAge(0);
//            }
//            driverInfo.setLastModify(String.valueOf(DateTime.now().getMillis()));
//            driverInfos.add(driverInfo);
//
//            assignment.setDrivers(driverInfos);
//
//            JsonObject jsonObject = handleAssignBox(assignment, entCode, OrderType.CONSUMER.getIndex().byteValue()); // 调用派货接口
//            if (!jsonObject.get("returnCode").getAsString().equals("0")) {  // 有异常了
//                transactionStatus.setRollbackOnly();
//            } else {
//                // 记录包装箱变更状态
//                BoxRecordInfo boxRecord = new BoxRecordInfo();
//                boxRecord.setRfid(boxId);
//                boxRecord.setOrderId(boxBindingInfo.getOrderId());
//                boxRecord.setOperatorId(operatorInfo.getOperatorId());
//                boxRecord.setOperator(operatorInfo.getUserName());
//                boxRecord.setOperateType(BoxStatus.TRANSPORTING.getIndex().byteValue());
//                boxRecord.setCreateTime(new Date());
//                boxRecord.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
//
//                long recordCount = boxRecordService.countBoxRecord(boxId, boxBindingInfo.getOrderId(), BoxStatus.BINDING.getIndex().byteValue(), entCode);
//                if (recordCount > 0) {
//                    boxRecordService.updateBoxRecord(boxRecord, boxId, boxBindingInfo.getOrderId(), BoxStatus.BINDING.getIndex().byteValue(), entCode);
//                } else {
//                    boxRecordService.saveBoxRecord(boxRecord, entCode);
//                }
//
//                resJo.addProperty("returnCode", "0");
//                resJo.addProperty("returnMsg", "ok");
//            }
//        }
//    }

}

//    /**
//     * 绑定包装箱
//     * @param boxBindingInfo
//     * @return
//     */
//    @Override
//    public String bindBox(final BoxBindingInfo boxBindingInfo) {
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("Bind Box --->>" + new Gson().toJson(boxBindingInfo));
//        }
//
//        final String entCode = getEntCodeByAk(boxBindingInfo.getAccessToken());
//
//        return transactionTemplate.execute(new TransactionCallback<String>() {
//            @Override
//            public String doInTransaction(TransactionStatus transactionStatus) {
//                JsonObject resJo = new JsonObject();
//
//                try {
//                    // 判断需要绑定的箱子是否入库
//                    FilterExample fe = new FilterExample();
//                    fe.createCriteria().andFieldEqualTo("rfid", boxBindingInfo.getBoxId());
//                    long boxCount = boxBaseService.count(fe);
//
//                    if (boxCount > 0) {
//                        BoxInfo boxInfo = new BoxInfo();
//                        boxInfo.setBarcode(boxBindingInfo.getBarcodeId());
//                        boxInfo.setOrderId(boxBindingInfo.getOrderId());
//                        boxInfo.setStatus(BoxStatus.BINDING.getIndex().byteValue()); // 设置包装箱状态为"配货"
//                        boxInfo.setOperatorId(boxBindingInfo.getRecheckerId());
//                        boxInfo.setOperator(boxBindingInfo.getRecheckerName());
//                        boxInfo.setOperateTime(new Date());
//                        boxInfo.setUpdateTime(new Date());
//                        boxInfo.setBoxName(boxBindingInfo.getBoxName());
//                        boxInfo.setBindTime(new Date());
//                        boxInfo.setDetail(new Gson().toJson(boxBindingInfo.getDrugs()));
//
//                        boxInfoService.updateBoxInfoByRfid(boxInfo, entCode);
//
////                        fe.clear();
////                        fe.createCriteria().andFieldEqualTo("rfid", boxBindingInfo.getBoxId());
////                        boxInfoService.update(boxInfoDO,fe);
//
//                        // 记录包装箱变更状态
//                        BoxRecordInfo boxRecord = new BoxRecordInfo();
//                        boxRecord.setRfid(boxBindingInfo.getBoxId());
//                        boxRecord.setOrderId(boxBindingInfo.getOrderId());
//                        boxRecord.setOperatorId(boxBindingInfo.getRecheckerId());
//                        boxRecord.setOperator(boxBindingInfo.getRecheckerName());
//                        boxRecord.setOperateType(BoxStatus.BINDING.getIndex().byteValue());
//                        boxRecord.setCreateTime(new Date());
//
////                        fe.clear();
////                        fe.createCriteria().andFieldEqualTo("rfid", boxBindingInfo.getBoxId()).andFieldEqualTo("order_id", boxBindingInfo.getOrderId()).andFieldEqualTo("operate_type", BoxStatus.BINDING.getIndex().byteValue());
////                        long recordCount = boxRecordService.count(fe);
//                        long recordCount = boxRecordService.countBoxRecord(boxBindingInfo.getBoxId(), boxBindingInfo.getOrderId(), BoxStatus.BINDING.getIndex().byteValue(), entCode);
//                        if (recordCount > 0) {
////                            boxRecordService.update(boxRecordDO, fe);
//                            boxRecordService.updateBoxRecord(boxRecord, boxBindingInfo.getBoxId(), boxBindingInfo.getOrderId(), BoxStatus.BINDING.getIndex().byteValue(), entCode);
//                        } else {
////                            boxRecordService.save(boxRecordDO);
//                            boxRecordService.saveBoxRecord(boxRecord, entCode);
//                        }
//
//                        // 订单
////                        fe.clear();
////                        fe.createCriteria().andFieldEqualTo("order_id", boxBindingInfo.getOrderId()).andFieldEqualTo("status", ValidStatus.VALID.getIndex().byteValue());
////                        long orderCount = orderService.count(fe);
//                        long orderCount = orderService.countOrderById(boxBindingInfo.getOrderId(), entCode);
//
//                        OrderInfo orderInfo = new OrderInfo();
//                        orderInfo.setOrderId(boxBindingInfo.getOrderId());
//
//                        if (orderCount > 0) { // 更新
////                            fe.clear();
////                            fe.createCriteria().andFieldEqualTo("order_id", boxBindingInfo.getOrderId());
////                            orderService.update(orderDO, fe);
//                            orderService.updateOrder(orderInfo, orderInfo.getOrderId(), entCode);
//                        } else {  // 新增
////                            orderDO.setStatus(ValidStatus.VALID.getIndex().byteValue());
////                            orderDO.setCreateTime(new Date());
////                            orderService.save(orderDO);
//
//                            orderInfo.setStatus(ValidStatus.VALID.getIndex().byteValue());
//                            orderInfo.setCreateTime(new Date());
//                            orderInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
//                            orderService.saveOrder(orderInfo, entCode);
//                        }
//
//                        resJo.addProperty("returnCode", "0");
//                        resJo.addProperty("returnMsg", "ok");
//                    } else {
//                        resJo.addProperty("returnCode", "10001");
//                        resJo.addProperty("returnMsg", "包装箱不存在");
//                    }
//                } catch (Exception e) {
//                    transactionStatus.setRollbackOnly();
//                    logger.error(String.format("条码（%s）绑定包装箱（%s）出现异常", boxBindingInfo.getBarcodeId(), boxBindingInfo.getBoxId()), e);
//                    resJo.addProperty("returnCode", "10002");
//                    resJo.addProperty("returnMsg", "绑定包装箱出现异常");
//                }
//
//                if (logger.isDebugEnabled()) {
//                    logger.debug("Bind Box Ack <<----" + resJo.toString());
//                }
//
//                return resJo.toString();
//            }
//        });
//
//    }
//
//    /**
//     * 派货
//     * @param assignment
//     * @return
//     */
//    @Override
//    public String assignBox(final Assignment assignment) {
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("Assign Box --->>" + new Gson().toJson(assignment));
//        }
//
//        final String entCode = getEntCodeByAk(assignment.getAccessToken());
//
//        return transactionTemplate.execute(new TransactionCallback<String>() {
//            @Override
//            public String doInTransaction(TransactionStatus transactionStatus) {
//                JsonObject resJo = new JsonObject();
//                try {
//                    FilterExample fe = new FilterExample();
//
////                    fe.createCriteria().andFieldEqualTo("order_id", assignment.getOrderId());
////                    long orderCount = orderService.count(fe);
//                    long orderCount = orderService.countOrderById(assignment.getOrderId(), entCode);
//                    if (orderCount <= 0) {
//                        resJo.addProperty("returnCode", "10004");
//                        resJo.addProperty("returnMsg", "派货订单未绑定");
//                    } else {
//                        OrderInfo orderInfo = new OrderInfo();
//
//                        // 客户联系人信息
//                        List<LinkerInfo> linkerInfos = assignment.getCustomer().getContacts();
//                        CustomLinkerDO customLinkerDO = null;
//                        for (int i = 0; i < linkerInfos.size(); i++) {
//                            customLinkerDO = new CustomLinkerDO();
//                            customLinkerDO.setLinkerId(linkerInfos.get(i).getId());
//                            customLinkerDO.setLinkerName(linkerInfos.get(i).getName());
//                            customLinkerDO.setLinkerPhone(linkerInfos.get(i).getPhone());
//                            customLinkerDO.setCustomId(assignment.getCustomer().getId());
//                            customLinkerDO.setUserName(linkerInfos.get(i).getName());
//                            customLinkerDO.setStatus(ValidStatus.VALID.getIndex().byteValue());
//
//                            if (i == 0) { // 默认第1个为客户订单联系人
//                                orderInfo.setLinkerId(linkerInfos.get(i).getId());
//                                orderInfo.setLinker(linkerInfos.get(i).getName());
//                                orderInfo.setLinkerPhone(linkerInfos.get(i).getPhone());
//
//                                customLinkerDO.setActiveFlag(WhetherType.YES.getIndex().byteValue());
//                            }
//
//                            fe.clear();
//                            fe.createCriteria().andFieldEqualTo("linker_id", linkerInfos.get(i).getId()).andFieldEqualTo("status", ValidStatus.VALID.getIndex());
//                            long linkerCount = customLinkerService.count(fe);
//                            if (linkerCount > 0) {  // 更新
//                                fe.clear();
//                                fe.createCriteria().andFieldEqualTo("linker_id", linkerInfos.get(i).getId());
//                                customLinkerDO.setModifyTime(new Date());
//                                customLinkerService.update(customLinkerDO, fe);
//                            } else { // 新增
//                                customLinkerDO.setModifyTime(new Date());
//                                customLinkerDO.setCreateTime(new Date());
//                                customLinkerDO.setUserPwd(PasswordUtils.md5(Constants.CUSTOMER_DEFAULT_PASSWORD));
//                                customLinkerService.save(customLinkerDO);
//                            }
//                        }
//
//                        // 派货信息
//                        OrderDriverInfo orderDriverInfo = null;
//
//                        // 司机信息
//                        List<DriverInfo> driverInfos = assignment.getDrivers();
//                        OperatorInfo operatorInfo = null;
//                        String prevDriverId = "";
//                        String prevDriverName = "";
//                        for (int i = driverInfos.size() - 1; i >= 0; i--) {  // 倒序为了获取当前司机下一个中转司机
////                            fe.clear();
////                            fe.createCriteria().andFieldEqualTo("operator_id", driverInfos.get(i).getId()).andFieldEqualTo("status", ValidStatus.VALID.getIndex());;
////                            long operatorCount = operatorInfoService.count(fe);
//                            long operatorCount = operatorInfoService.countOperator(driverInfos.get(i).getId(),entCode);
//
//                            operatorInfo = new OperatorInfo();
//                            operatorInfo.setOperatorId(driverInfos.get(i).getId());
////                            operatorInfo.setUserName(driverInfos.get(i).getName().concat(driverInfos.get(i).getId()));  // 登录用户名为名称+编号
//                            operatorInfo.setName(driverInfos.get(i).getName());
//                            operatorInfo.setTel(driverInfos.get(i).getPhone());
//                            operatorInfo.setType(OperatorType.DRIVER.getIndex().byteValue());
//                            operatorInfo.setStatus(ValidStatus.VALID.getIndex().byteValue());
//                            operatorInfo.setAge(driverInfos.get(i).getAge() == null ? 0 : driverInfos.get(i).getAge().byteValue());
//                            operatorInfo.setSex(driverInfos.get(i).getSex() == null ? 0 : driverInfos.get(i).getSex().byteValue());
//                            if (operatorCount > 0) { // 更新
////                                fe.clear();
////                                fe.createCriteria().andFieldEqualTo("operator_id", driverInfos.get(i).getId());
////                                operatorInfoDO.setModifyTime(new Date());
////                                operatorInfoService.update(operatorInfoDO, fe);
//
//                                operatorInfo.setModifyTime(new Date());
//                                operatorInfo.setModifyTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
//                                operatorInfoService.updateOperatorByOperatorId(operatorInfo, entCode);
//                            } else {  // 新增
//                                operatorInfo.setUserName(operatorInfoService.generateUserName(driverInfos.get(i).getName(), driverInfos.get(i).getId()));
//                                operatorInfo.setCreateTime(new Date());
//                                operatorInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
//                                operatorInfo.setModifyTime(new Date());
//                                operatorInfo.setModifyTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
//                                operatorInfo.setUserPwd(PasswordUtils.md5(Constants.OPERATOR_DEFAULT_PASSWORD));
////                                operatorInfoService.save(operatorInfoDO);
//                                operatorInfoService.saveOperator(operatorInfo, entCode);
//                            }
//
//
//                            // 派货信息
//                            orderDriverInfo = new OrderDriverInfo();
//                            orderDriverInfo.setOrderId(assignment.getOrderId());
//                            orderDriverInfo.setDriverId(driverInfos.get(i).getId());
//                            orderDriverInfo.setDriverName(driverInfos.get(i).getName());
//                            orderDriverInfo.setDriverPhone(driverInfos.get(i).getPhone());
//                            orderDriverInfo.setDriverOrder(driverInfos.get(i).getOrder().byteValue());
//                            orderDriverInfo.setNextDriverId(prevDriverId);
//
////                            fe.clear();
////                            fe.createCriteria().andFieldEqualTo("order_id", assignment.getOrderId()).andFieldEqualTo("driver_id", driverInfos.get(i).getId());
////                            long orderDriverCount = orderDriverService.count(fe);
//                            long orderDriverCount = orderDriverService.countOrderDriver(assignment.getOrderId(), driverInfos.get(i).getId(), entCode);
//
//                            if (orderDriverCount > 0) { // 更新
////                                orderDriverService.update(orderDriverDO, fe);
//                                orderDriverService.updateByOrderIdAndDriverId(orderDriverInfo, entCode);
//                            } else {  // 新增
//                                orderDriverInfo.setCreateTime(new Date());
//                                orderDriverInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
//                                orderDriverService.saveOrderDriver(orderDriverInfo, entCode);
////                                orderDriverService.save(orderDriverDO);
//                            }
//
//                            prevDriverId = driverInfos.get(i).getId();
//                            prevDriverName = driverInfos.get(i).getName();
//                        }
//
//                        // 更新箱子操作人
////                        fe.clear();
////                        fe.createCriteria().andFieldEqualTo("order_id", assignment.getOrderId());
////                        List<BoxInfoDO> boxInfoDOList = boxInfoService.getList(fe);
//                        List<BoxInfo> boxInfos = boxInfoService.getBoxInfoByOrderid(assignment.getOrderId(), entCode);
//
//                        for (BoxInfo boxInfo : boxInfos) {
//                            boxInfo.setOperatorId(prevDriverId);
//                            boxInfo.setOperator(prevDriverName);
//
//                            boxInfoService.updateBoxInfoByOrderId(boxInfo, entCode);
////                            boxInfoService.update(boxInfoDO);
//
//                        }
//
//                        // 客户信息
//                        fe.clear();
//                        fe.createCriteria().andFieldEqualTo("custom_id", assignment.getCustomer().getId()).andFieldEqualTo("status", ValidStatus.VALID.getIndex());;;
//                        long customCount = customInfoService.count(fe);
//
//                        CustomInfoDO customInfoDO = new CustomInfoDO();
//                        customInfoDO.setCustomId(assignment.getCustomer().getId());
//                        customInfoDO.setCustomName(assignment.getCustomer().getName());
//                        customInfoDO.setCustomAddress(assignment.getCustomer().getAddress());
//                        if (customCount > 0) {  // 更新
//                            customInfoDO.setModifyTime(new Date());
//                            fe.clear();
//                            fe.createCriteria().andFieldEqualTo("custom_id", assignment.getCustomer().getId());
//                            customInfoDO.setModifyTime(new Date());
//                            customInfoService.update(customInfoDO, fe);
//                        } else {  // 新增
//                            customInfoDO.setModifyTime(new Date());
//                            customInfoDO.setCreateTime(new Date());
//                            customInfoDO.setStatus(ValidStatus.VALID.getIndex().byteValue());
//                            customInfoDO.setCustomPwd(PasswordUtils.md5(Constants.CUSTOMER_DEFAULT_PASSWORD));
//                            customInfoService.save(customInfoDO);
//                        }
//
//                        // 订单信息
////                        fe.clear();
////                        fe.createCriteria().andFieldEqualTo("order_id", assignment.getOrderId());
//                        orderInfo.setCustomerId(assignment.getCustomer().getId());
//                        orderInfo.setCustomerAddress(assignment.getCustomer().getAddress());
//                        orderInfo.setCustomer(assignment.getCustomer().getName());
////                        orderService.update(orderDO, fe);
//                        orderService.updateOrder(orderInfo, assignment.getOrderId(), entCode);
//
//                        resJo.addProperty("returnCode", "0");
//                        resJo.addProperty("returnMsg", "ok");
//                    }
//                } catch (Exception e) {
//                    transactionStatus.setRollbackOnly();
//                    logger.error(String.format("派货（%s）出现异常", assignment.getOrderId()), e);
//                    resJo.addProperty("returnCode", "10003");
//                    resJo.addProperty("returnMsg", "派货出现异常");
//                }
//
//                if (logger.isDebugEnabled()) {
//                    logger.debug("Assign Box Ack <<---" + resJo.toString());
//                }
//
//                return resJo.toString();
//            }
//        });
//    }
