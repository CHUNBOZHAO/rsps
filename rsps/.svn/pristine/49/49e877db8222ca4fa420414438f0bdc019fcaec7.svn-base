package com.izhuixin.rsps.service.impl;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.izhuixin.rsps.common.constant.*;
import com.izhuixin.rsps.common.dba.BaseAbstractService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.common.object.Pair;
import com.izhuixin.rsps.common.page.Paginator;
import com.izhuixin.rsps.common.util.LocationCache;
import com.izhuixin.rsps.common.util.PasswordUtils;
import com.izhuixin.rsps.common.util.SnowflakeIdWorker;
import com.izhuixin.rsps.common.util.UUID;
import com.izhuixin.rsps.common.vo.app.*;
import com.izhuixin.rsps.common.vo.web.BoxInfoVO;
import com.izhuixin.rsps.domain.automatic.BoxBaseDO;
import com.izhuixin.rsps.domain.automatic.OperatorLocation;
import com.izhuixin.rsps.domain.automatic.OrderDO;
import com.izhuixin.rsps.domain.automatic.SystemParam;
import com.izhuixin.rsps.domain.manual.*;
import com.izhuixin.rsps.service.*;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AppRestServiceImpl extends BaseAbstractService implements AppRestService {

    @Autowired
    private OperatorInfoService operatorInfoService;

    @Autowired
    private BoxInfoService boxInfoService;

    @Autowired
    private OrderDriverService orderDriverService;

    @Autowired
    private OrderBoxService orderBoxService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BoxRecordService boxRecordService;

    @Autowired
    private BoxLocationRecordService boxLocationRecordService;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private BoxBaseService boxBaseService;

    @Autowired
    private SysParamService sysParamService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private OperatorLocationService operatorLocationService;

    @Autowired
    private LineService lineService;

    @Autowired
    private RecycleApplyInfoService recycleApplyInfoService;

    @Autowired
    private LineTransferService lineTransferService;

    @Autowired
    private ThreadPoolTaskExecutor poolTaskExecutor;

    @Value("${login.once.flag}")
    private byte loginOnceFlag;

    /**
     * 用户登录
     * @param loginUser
     * @return
     */
    @Override
    public AppResOperator handleLogin(AppReqLoginUser loginUser) {
        if (logger.isDebugEnabled()) {
            logger.debug("Handle Login --->> " + new Gson().toJson(loginUser));
        }

        String entCode = loginUser.getEntCode();

        //AppAckContent ackContent = new AppAckContent();
        AppResOperator appResOperator = new AppResOperator();
        boolean res = operatorInfoService.checkUserStatus(loginUser.getUserName(), entCode);
        if (res) {
            res = operatorInfoService.handleLogin(loginUser.getUserName(), loginUser.getUserPwd(), entCode);
            if (res) {
                OperatorInfo operatorInfo = operatorInfoService.getOperatorInfoByUserName(loginUser.getUserName(), entCode);
                if (operatorInfo != null) {

                    appResOperator.setId(operatorInfo.getId());
                    appResOperator.setOperatorId(operatorInfo.getOperatorId());
                    appResOperator.setUserName(operatorInfo.getUserName());
                    appResOperator.setUserType(operatorInfo.getType());
                    appResOperator.setHeadUrl(operatorInfo.getHeadUrl());
                    appResOperator.setAge(operatorInfo.getAge());
                    appResOperator.setSex(operatorInfo.getSex());
                    appResOperator.setStatus(ErrorCode.OK.getIndex().toString());
                    appResOperator.setMessage(ErrorCode.OK.getDescr());
                    appResOperator.setAppSessionId(operatorInfo.getAppSessionId());
                    appResOperator.setEntCode(entCode.replace("_",""));
                    appResOperator.setOperatorNo(operatorInfo.getOperatorNo());

                    if (logger.isDebugEnabled()) {
                        logger.debug("Handle Login Ack <<---" + new Gson().toJson(appResOperator));
                    }
                    return appResOperator;
                } else {
                    appResOperator.setStatus(ErrorCode.USER_NAME_NOT_EXIST.getIndex().toString());
                    appResOperator.setMessage(ErrorCode.USER_NAME_NOT_EXIST.getDescr());
                }
            } else {
                appResOperator.setStatus(ErrorCode.USER_NAME_PWD_ERROR.getIndex().toString());
                appResOperator.setMessage(ErrorCode.USER_NAME_PWD_ERROR.getDescr());
            }
        } else {
            appResOperator.setStatus(ErrorCode.USER_NAME_NOT_EXIST.getIndex().toString());
            appResOperator.setMessage(ErrorCode.USER_NAME_NOT_EXIST.getDescr());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Handle Login Ack <<---" + new Gson().toJson(appResOperator));
        }
        return appResOperator;
    }

    /**
     * 修改用户密码
     * @param loginUser
     * @return
     */
    @Override
    public String updatePwd(AppReqLoginUser loginUser) {
        if (logger.isDebugEnabled()) {
            logger.debug("Update Pwd --->>" + new Gson().toJson(loginUser));
        }

        String entCode = loginUser.getEntCode();

        AppAckContent ackContent = new AppAckContent();

        boolean res = operatorInfoService.handleLogin(loginUser.getUserName(), loginUser.getUserPwd(), entCode);
        if (res) {
            res = operatorInfoService.updatePwd(loginUser.getUserName(), loginUser.getNewUserPwd(), entCode);
            if (res) {

                ackContent.setStatus(ErrorCode.OK.getIndex().toString());
                ackContent.setMessage(ErrorCode.OK.getDescr());
            } else {
                ackContent.setStatus(ErrorCode.ERROR.getIndex().toString());
                ackContent.setMessage(ErrorCode.ERROR.getDescr());
            }
        } else {
            ackContent.setStatus(ErrorCode.USER_NAME_PWD_ERROR.getIndex().toString());
            ackContent.setMessage(ErrorCode.USER_NAME_PWD_ERROR.getDescr());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Update Pwd Ack <<---" + new Gson().toJson(ackContent));
        }
        return new Gson().toJson(ackContent);
    }

    /**
     * 用户注册
     * @param appReqOpRegister
     * @return
     */
    @Override
    public String registerOperator(AppReqOpRegister appReqOpRegister) {
        if (logger.isDebugEnabled()) {
            logger.debug("Update Pwd --->>" + new Gson().toJson(appReqOpRegister));
        }
        String entCode = appReqOpRegister.getEntCode();
        AppAckContent ackContent = new AppAckContent();
        boolean res = operatorInfoService.checkUserStatus(entCode.concat(appReqOpRegister.getUserName()), entCode);
        if (!res) { // 用户名不存在
            OperatorInfo operatorInfo = new OperatorInfo();
            operatorInfo.setUserName(entCode.concat(appReqOpRegister.getUserName()));
            operatorInfo.setUserPwd(PasswordUtils.md5(appReqOpRegister.getPassword()));
            operatorInfo.setOperatorId("op".concat(String.valueOf(SnowflakeIdWorker.getInstance().nextId())));
            operatorInfo.setCreateTime(new Date());
            operatorInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            operatorInfo.setName(appReqOpRegister.getUserName());
            operatorInfo.setType(OperatorType.DRIVER.getIndex().byteValue());
            operatorInfo.setTel("");
            operatorInfo.setSex((byte)0);
            operatorInfo.setAge((byte)30);
            operatorInfo.setModifyTime(new Date());
            operatorInfo.setModifyTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
            boolean resSave = operatorInfoService.saveOperator(operatorInfo, entCode);
            if (resSave) {
                ackContent.setStatus(ErrorCode.OK.getIndex().toString());
                ackContent.setMessage(ErrorCode.OK.getDescr());
            } else {
                ackContent.setStatus(ErrorCode.ERROR.getIndex().toString());
                ackContent.setMessage(ErrorCode.ERROR.getDescr());
            }
        } else {
            ackContent.setStatus(ErrorCode.USER_NAME_EXIST.getIndex().toString());
            ackContent.setMessage(ErrorCode.USER_NAME_EXIST.getDescr());
        }
        return new Gson().toJson(ackContent);
    }

    /**
     * 获取包装箱信息
     * @param appReqBoxes
     * @return
     */
    @Override
    public String queryBoxes(AppReqBoxes appReqBoxes) {

        if (logger.isDebugEnabled()) {
            logger.debug("Query Boxes ---> " + new Gson().toJson(appReqBoxes));
        }

        String entCode = appReqBoxes.getEntCode();
//        Byte accessWay = enterpriseService.getAccessWayByEntCode(entCode.replace("_",""));
        byte accessWay = 0;
        if (appReqBoxes.getAccessType() == null) { // 如果为空，则不自动分配方式
            accessWay = AccessType.TWO.getIndex().byteValue();
        } else {
            accessWay = appReqBoxes.getAccessType().byteValue();
        }

        if (accessWay == AccessType.TWO.getIndex().byteValue()) { // 自行分配线路的方式，获取运输、滞留、回收的包装箱
            List<Byte> statusList = Lists.newArrayList();
            statusList.add(BoxStatus.TRANSPORTING.getIndex().byteValue());
            statusList.add(BoxStatus.RETENTION.getIndex().byteValue());
            statusList.add(BoxStatus.RECYCLE.getIndex().byteValue());
            appReqBoxes.setBoxStatus(statusList);
        }

        AppResOrders appResOrders = new AppResOrders();

        Paginator paginator = new Paginator();
//        paginator.setItemsPerPage(appReqBoxes.getPageSize());
        paginator.setItemsPerPage(1000);  // todo 临时不进行分页, 不进行分页处理
        paginator.setPage(appReqBoxes.getPageIndex());
        List<BoxInfo> listBox = boxInfoService.queryBoxes(appReqBoxes.getOperatorId(),
                                                            appReqBoxes.getBoxStatus(),
                                                            appReqBoxes.getBeginTime(),
                                                            appReqBoxes.getEndTime(),
                                                            paginator,
                                                            entCode);
        appResOrders.setSize(listBox.size());
        appResOrders.setTotal(paginator.getItems());

        List<AppResBox> listResBox = Lists.newArrayList();

        // 包装箱内容结构转换
        getBoxes(listResBox, listBox, entCode);

        Map<String, Pair<String, String>> customTransferMap = boxInfoService.queryCustomTransferMap(appReqBoxes.getOperatorId(), entCode);
        for (AppResBox appResBox : listResBox) {
            if (customTransferMap.containsKey(appResBox.getCustomId())) {
                Pair<String, String> transferInfo = customTransferMap.get(appResBox.getCustomId());
                if (transferInfo == null) {
                    continue;
                }
                appResBox.setNextStationId(transferInfo.getFirst());
                appResBox.setNextStation(transferInfo.getSecond());
            }
        }

        appResOrders.setOrders(listResBox);
        appResOrders.setStatus(ErrorCode.OK.getIndex());
        appResOrders.setMessage(ErrorCode.OK.getDescr());

        if (logger.isDebugEnabled()) {
            logger.debug("Query Boxes Ack <<---" + new Gson().toJson(appResOrders));
        }

        return new Gson().toJson(appResOrders);
    }

    /**
     * 获取线路包装箱信息
     * @param appReqBoxes
     * @return
     */
    @Override
    public String queryLineBoxes(AppReqBoxes appReqBoxes) {

        if (logger.isDebugEnabled()) {
            logger.debug("Query Line Boxes ---> " + new Gson().toJson(appReqBoxes));
        }

        String entCode = appReqBoxes.getEntCode();
//        Byte accessWay = enterpriseService.getAccessWayByEntCode(entCode.replace("_",""));
        byte accessWay = 0;
        if (appReqBoxes.getAccessType() == null) { // 如果为空，则不自动分配方式
            accessWay = AccessType.TWO.getIndex().byteValue();
        } else {
            accessWay = appReqBoxes.getAccessType().byteValue();
        }

        AppResOrders appResOrders = new AppResOrders();
        List<AppResBox> listResBox = Lists.newArrayList();
        if (accessWay == AccessType.TWO.getIndex().byteValue()) {  // 自行分配线路，需要查询线路的订单包装箱
            List<BoxInfo> listBox = boxInfoService.queryLineBoxes(appReqBoxes.getOperatorId(), entCode);

            // 通过nextOperateId过滤分配给其他配送员的包装箱
            List<BoxInfo> listFilterBox = listBox.stream().filter(boxInfo ->  boxInfo.getNextOperatorId() == null || boxInfo.getNextOperatorId().equals("") || boxInfo.getNextOperatorId().equals(appReqBoxes.getOperatorId())).collect(Collectors.toList());

            List<BoxInfo> listTransferBox = boxInfoService.queryTransferBoxes(appReqBoxes.getOperatorId(), entCode);

            // C端流程目前不走该流程, 暂时注释
//            List<BoxInfo> listCBox = boxInfoService.queryCLineBoxes(appReqBoxes.getOperatorId(), entCode, paginator);

            listFilterBox.addAll(listTransferBox);
            // 包装箱内容结构转换
            getBoxes(listResBox, listFilterBox, entCode);
        }

        appResOrders.setOrders(listResBox);
        appResOrders.setStatus(ErrorCode.OK.getIndex());
        appResOrders.setMessage(ErrorCode.OK.getDescr());

        if (logger.isDebugEnabled()) {
            logger.debug("Query Line Boxes Ack <<---" + new Gson().toJson(appResOrders));
        }

        return new Gson().toJson(appResOrders);
    }

    /***
     * 推送箱子状态信息
     * @param appReqPushBoxInfo
     * @return
     */
    @Override
    public String pushBoxStatus(final AppReqPushBoxInfo appReqPushBoxInfo) {

        if (logger.isDebugEnabled()) {
            logger.debug("Push Box Status --->> " + new Gson().toJson(appReqPushBoxInfo));
        }

        final String entCode = appReqPushBoxInfo.getEntCode();

        return transactionTemplate.execute(transactionStatus -> {

            AppResPushStatus ackContent = null;
            try {
                String boxId = "";
                if (appReqPushBoxInfo.getData() != null) {
                    List<String> errorIds = Lists.newArrayList();
                    for (AppPushBoxInfo appPushBoxInfo : appReqPushBoxInfo.getData()) {

                        boxId = boxBaseService.getBoxId(appPushBoxInfo.getRfid());

                        if (StringUtils.isBlank(boxId)) {
                            logger.error(String.format("包装箱ID(%s)不存在", appPushBoxInfo.getRfid()));
                            errorIds.add(appPushBoxInfo.getRfid());
                            continue;
                        }

                        BoxInfo originalBoxInfo = boxInfoService.getBoxInfoByRfid(boxId, entCode);
                        if (originalBoxInfo == null) {
                            logger.error(String.format("包装箱ID(%s)在企业(%s)不存在", appPushBoxInfo.getRfid(), entCode));
                            errorIds.add(appPushBoxInfo.getRfid());
                            continue;
                        }

                        // 当有装车、签收、回收、中转操作时，而当前包装箱的状态为闲置，则说明是错误信息
                        if ((appPushBoxInfo.getStatus().byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue() ||
                                appPushBoxInfo.getStatus().byteValue() == BoxStatus.RETENTION.getIndex().byteValue() ||
                                appPushBoxInfo.getStatus().byteValue() == BoxStatus.RECYCLE.getIndex().byteValue() ||
                                appPushBoxInfo.getStatus().byteValue() == BoxStatus.TRANSIT.getIndex().byteValue()) &&
                                originalBoxInfo.getStatus().byteValue() == BoxStatus.LEISURE.getIndex().byteValue()) {
                            errorIds.add(appPushBoxInfo.getRfid());
                            continue;
                        }

                        // 中转处理
                        String beginTransferId = "0";
                        if (appPushBoxInfo.getSignStatus() != null &&
                                appPushBoxInfo.getStatus() != null &&
                                appPushBoxInfo.getSignStatus().byteValue() == SignStatus.SIGN_IN.getIndex().byteValue()) {  // 签收消息
                            String transferId = lineTransferService.checkDeliveryStatus(boxId, appPushBoxInfo.getOperatorId(), entCode);
                            if (StringUtils.isNotBlank(transferId)) {  // 中转站点签收
                                beginTransferId = transferId;
                                appPushBoxInfo.setStatus(BoxStatus.TRANSIT.getIndex().byteValue());
                                appPushBoxInfo.setSignStatus(SignStatus.NOT_SIGN.getIndex().byteValue());
//                                    appPushBoxInfo.setSignStatus(SignStatus.TRANSFER_SIGN.getIndex().byteValue()); // 中转签收
                            }
                        }

                        // 更新包装箱信息
                        BoxInfo boxInfo = new BoxInfo();
                        boxInfo.setRfid(boxId);
                        boxInfo.setStatus(appPushBoxInfo.getStatus());
                        boxInfo.setOperatorId(appPushBoxInfo.getOperatorId());
                        boxInfo.setOperator(appPushBoxInfo.getOperator());
                        boxInfo.setSignStatus(appPushBoxInfo.getSignStatus());
                        boxInfo.setOperateTime(new Date());
                        boxInfo.setOperateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                        boxInfo.setBeginTransferId(beginTransferId);
                        if (appPushBoxInfo.getStatus().byteValue() == OperateType.RECYCLE.getIndex().byteValue()) {  // 回收
                            boxInfo.setSignStatus((byte) 0);
                            boxInfo.setUpdateTime(new Date());
                            boxInfo.setUpdateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                        } else if (appPushBoxInfo.getStatus().byteValue() == OperateType.GO_HOME.getIndex().byteValue()) {  // 回库
                            boxInfo.setOrderId("");
                            boxInfo.setBoxName("");
                            boxInfo.setSignStatus((byte) 0);
                            boxInfo.setDetail("");
                            boxInfo.setUpdateTime(new Date());
                            boxInfo.setUpdateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                            boxInfo.setBarcode("");
                            boxInfo.setBeginTransferId("0");
                            boxInfo.setDuLatitude(0.0);
                            boxInfo.setDuLongitude(0.0);
                        }
                        boxInfoService.updateBoxInfoByRfid(boxInfo, entCode);

                        // 获取更新后的包装信息
                        BoxInfo boxInfoNew = boxInfoService.getBoxInfoByRfid(boxId, entCode);

                        // 包装箱操作记录
                        if (originalBoxInfo.getStatus() != null && originalBoxInfo.getStatus().byteValue() == BoxStatus.LEISURE.getIndex().byteValue() &&
                                originalBoxInfo.getStatus().byteValue() == BoxStatus.LEISURE.getIndex()) {
                            logger.warn(String.format("回收智能包装箱(%s)出现重复数据", appPushBoxInfo.getRfid()));
                        } else {
                            // 记录包装箱变更状态
                            BoxRecordInfo boxRecordInfo = new BoxRecordInfo();
                            boxRecordInfo.setRfid(boxId);
                            boxRecordInfo.setOrderId(originalBoxInfo != null ? originalBoxInfo.getOrderId() : "");
                            boxRecordInfo.setOperatorId(appPushBoxInfo.getOperatorId());
                            boxRecordInfo.setOperator(appPushBoxInfo.getOperator());
                            // 操作类型基本和包装箱状态对应，拒签时需要处理
                            if (appPushBoxInfo.getSignStatus() != null && appPushBoxInfo.getStatus().byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue() &&
                                    appPushBoxInfo.getSignStatus().byteValue() == SignStatus.DENIED_SIGN.getIndex().byteValue()) {
                                boxRecordInfo.setOperateType(OperateType.DENIED_SIGN.getIndex().byteValue());
                            } else if (appPushBoxInfo.getSignStatus() != null && appPushBoxInfo.getSignStatus().byteValue() == SignStatus.TRANSFER_SIGN.getIndex().byteValue()) {
                                boxRecordInfo.setOperateType(OperateType.TRANSIT_SIGN_IN.getIndex().byteValue());
                            } else {
                                boxRecordInfo.setOperateType(appPushBoxInfo.getStatus().byteValue());
                            }
                            boxRecordInfo.setCreateTime(new Date());
                            boxRecordInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                            boxRecordInfo.setExceptionType(appPushBoxInfo.getExceptionType());
                            boxRecordInfo.setExceptionDesc(appPushBoxInfo.getExceptionDesc());
                            boxRecordInfo.setExtraValue(beginTransferId);
                            boxRecordService.saveBoxRecord(boxRecordInfo, entCode);
                        }

                        // 签收时，同步数据到历史订单表
                        if (appPushBoxInfo.getSignStatus() != null && appPushBoxInfo.getStatus() != null) {
                            syncHistoryInfo(appPushBoxInfo, boxInfoNew, entCode);
                        }

                        // 回收、回库，更新包装箱回收申请记录状态
                        if (appPushBoxInfo.getStatus().byteValue() == OperateType.RECYCLE.getIndex().byteValue() ||
                                appPushBoxInfo.getStatus().byteValue() == OperateType.GO_HOME.getIndex().byteValue()) {
                            recycleApplyInfoService.updateState(boxId, RecycleState.DONE.getIndex().byteValue());
                        }
                    }

                    ackContent = new AppResPushStatus();
                    ackContent.setStatus(ErrorCode.OK.getIndex().toString());
                    ackContent.setMessage(ErrorCode.OK.getDescr());
                    ackContent.setErrorIds(errorIds);
                } else {
                    ackContent = new AppResPushStatus();
                    ackContent.setStatus(ErrorCode.DATA_PUSH_IS_NULL.getIndex().toString());
                    ackContent.setMessage(ErrorCode.DATA_PUSH_IS_NULL.getDescr());
                }
            } catch (Exception e) {
                ackContent = new AppResPushStatus();
                ackContent.setStatus(ErrorCode.DB_ERROR.getIndex().toString());
                ackContent.setMessage(ErrorCode.DB_ERROR.getDescr());
                logger.error("app推送包装箱状态变更信息出现异常", e);
                transactionStatus.setRollbackOnly();
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Push Box Status Ack <<--- " + new Gson().toJson(ackContent));
            }
            return new Gson().toJson(ackContent);
        });

    }

    /**
     * 包装箱注册
     * @param reqRegister
     * @return
     */
    @Override
    public String registerBox(final AppReqRegister reqRegister) {

        if (logger.isDebugEnabled()) {
            logger.debug("Register Box --->> " + new Gson().toJson(reqRegister));
        }

        final String entCode = reqRegister.getEntCode();
        final String entId = enterpriseService.getEntIdByEntCode(entCode.replace("_",""));

        return transactionTemplate.execute(transactionStatus -> {
            AppAckContent ackContent = null;

            try {

                BoxBaseDO boxBaseDO = new BoxBaseDO();
                boxBaseDO.setRfid(reqRegister.getRfid());
                boxBaseDO.setUuid(reqRegister.getUuid());
                boxBaseDO.setType(reqRegister.getType());
                boxBaseDO.setEpc(reqRegister.getEpc() != null ? reqRegister.getEpc() : "");
                boxBaseDO.setTid(reqRegister.getTid() != null ? reqRegister.getTid() : "");
                boxBaseDO.setSoftwareVersion(reqRegister.getSoftwareVersion());
                boxBaseDO.setHardwareVersion(reqRegister.getHardwareVersion());
                boxBaseDO.setEntId(entId);

                FilterExample fe = new FilterExample();
                fe.createCriteria().andFieldEqualTo("uuid", reqRegister.getUuid());
                List<BoxBaseDO> boxBaseDOS = boxBaseService.getList(fe);
                if (boxBaseDOS != null && !boxBaseDOS.isEmpty()) {
                    for (BoxBaseDO baseDO : boxBaseDOS) {
                        boxBaseService.remove(baseDO.getId());  // 删除BoxBase数据表记录

//                            fe.clear();
//                            fe.createCriteria().andFieldEqualTo("rfid", baseDO.getRfid());  // 删除BoxInfo数据表记录
//                            boxInfoService.remove(fe);
                        boxInfoService.removeBoxInfo(baseDO.getRfid(), entCode);
                        logger.info(String.format("注册包装箱前删除蓝牙地址为(%s)的包装箱(%s)绑定信息", reqRegister.getUuid(), baseDO.getRfid()));
                    }
                }

                fe.clear();
                fe.createCriteria().andFieldEqualTo("rfid", reqRegister.getRfid());
                long count = boxBaseService.count(fe);
                if (count > 0) {  // 更新
                    boxBaseDO.setCreateTime(new Date());
                    boxBaseService.update(boxBaseDO, fe);
                    logger.warn(String.format("注册包装箱rfid(%s)出现重复,数据需要进行更新", reqRegister.getRfid()));
                } else {  // 新增
                    boxBaseDO.setCreateTime(new Date());
                    boxBaseDO.setUniqueId(UUID.geneate());
                    boxBaseService.save(boxBaseDO);
                }

                BoxInfo boxInfo = new BoxInfo();
                boxInfo.setRfid(reqRegister.getRfid());
                boxInfo.setStatus(BoxStatus.LEISURE.getIndex().byteValue());
                boxInfo.setUpdateTime(new Date());
                count = boxInfoService.count(fe);
                if (count > 0) {
//                        boxInfoService.update(boxInfoDO, fe);
                    boxInfoService.updateBoxInfoByRfid(boxInfo, entCode);
                } else {
//                        boxInfoService.save(boxInfoDO);
                    boolean res = boxInfoService.saveBoxInfo(boxInfo, entCode);
                    if (!res) {
                        transactionStatus.setRollbackOnly();
                    }
                }

                ackContent = new AppAckContent();
                ackContent.setStatus(ErrorCode.OK.getIndex().toString());
                ackContent.setMessage(ErrorCode.OK.getDescr());
            } catch (Exception e) {
                ackContent = new AppAckContent();
                ackContent.setStatus(ErrorCode.DB_ERROR.getIndex().toString());
                ackContent.setMessage(ErrorCode.DB_ERROR.getDescr());
                logger.error(String.format("注册包装箱（%s）出现异常", reqRegister.getRfid()), e);
                transactionStatus.setRollbackOnly();
            }

            if (logger.isDebugEnabled()) {
                logger.debug("Register Box Ack <<--- " + new Gson().toJson(ackContent));
            }
            return new Gson().toJson(ackContent);
        });
    }

    /**
     * 通过rfid查询设备
     * @param rfid
     * @return
     */
    @Override
    public String queryBoxByRfid(String rfid) {

        if (logger.isDebugEnabled()) {
            logger.debug("Query Box By RFID --->> " + rfid);
        }

        AppResBoxBase appResBoxBase = new AppResBoxBase();

        String boxId = "";

        try {
            boxId = boxBaseService.getBoxId(rfid);

            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("rfid", boxId);
            List<BoxBaseDO> boxBaseDOS = boxBaseService.getList(fe);
            if (boxBaseDOS != null && !boxBaseDOS.isEmpty()) {
                if (boxBaseDOS.size() > 1) {
                    appResBoxBase.setRfid(rfid);
                    appResBoxBase.setUuid("");
                    appResBoxBase.setSize(boxBaseDOS.size());
                    appResBoxBase.setStatus(ErrorCode.ERROR.getIndex().intValue());
                    appResBoxBase.setMessage(ErrorCode.ERROR.getDescr());
                } else {
                    appResBoxBase.setRfid(rfid);
                    appResBoxBase.setUuid(boxBaseDOS.get(0).getUuid());
                    appResBoxBase.setType(boxBaseDOS.get(0).getType());
                    appResBoxBase.setSoftwareVersion(boxBaseDOS.get(0).getSoftwareVersion());
                    appResBoxBase.setHardwareVersion(boxBaseDOS.get(0).getHardwareVersion());
                    appResBoxBase.setSize(1);
                    appResBoxBase.setStatus(ErrorCode.OK.getIndex().intValue());
                    appResBoxBase.setMessage(ErrorCode.OK.getDescr());
                }
            } else {
                appResBoxBase.setRfid(rfid);
                appResBoxBase.setUuid("");
                appResBoxBase.setSize(0);
                appResBoxBase.setStatus(ErrorCode.BOX_QUERY_EMPTY.getIndex().intValue());
                appResBoxBase.setMessage(ErrorCode.BOX_QUERY_EMPTY.getDescr());
            }
        } catch (Exception e) {
            logger.error(String.format("通过RFID (%s)获取包装箱信息出现异常", rfid), e);
            appResBoxBase.setRfid(rfid);
            appResBoxBase.setUuid("");
            appResBoxBase.setSize(0);
            appResBoxBase.setStatus(ErrorCode.ERROR.getIndex().intValue());
            appResBoxBase.setMessage(ErrorCode.ERROR.getDescr());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Query Box By RFID Ack <<--- " + new Gson().toJson(appResBoxBase));
        }
        return new Gson().toJson(appResBoxBase);
    }


    /**
     * 通过指定蓝牙ID的设备
     * @param uuid
     * @return
     */
    @Override
    public String queryBoxByBluetoothId(String uuid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Query Box By BluetoothId --->> " + uuid);
        }

        AppResBoxBase appResBoxBase = new AppResBoxBase();

        try {
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("uuid", uuid);
            List<BoxBaseDO> boxBaseDOS = boxBaseService.getList(fe);
            if (boxBaseDOS != null && !boxBaseDOS.isEmpty()) {
                if (boxBaseDOS.size() > 1) {
                    appResBoxBase.setRfid("");
                    appResBoxBase.setUuid(uuid);
                    appResBoxBase.setSize(boxBaseDOS.size());
                    appResBoxBase.setStatus(ErrorCode.ERROR.getIndex().intValue());
                    appResBoxBase.setMessage(ErrorCode.ERROR.getDescr());
                } else {
                    appResBoxBase.setRfid(boxBaseDOS.get(0).getRfid());
                    appResBoxBase.setUuid(uuid);
                    appResBoxBase.setSize(1);
                    appResBoxBase.setType(boxBaseDOS.get(0).getType());
                    appResBoxBase.setSoftwareVersion(boxBaseDOS.get(0).getSoftwareVersion());
                    appResBoxBase.setHardwareVersion(boxBaseDOS.get(0).getHardwareVersion());
                    appResBoxBase.setStatus(ErrorCode.OK.getIndex().intValue());
                    appResBoxBase.setMessage(ErrorCode.OK.getDescr());
                }
            } else {
                appResBoxBase.setRfid("");
                appResBoxBase.setUuid(uuid);
                appResBoxBase.setSize(0);
                appResBoxBase.setStatus(ErrorCode.BOX_QUERY_EMPTY.getIndex().intValue());
                appResBoxBase.setMessage(ErrorCode.BOX_QUERY_EMPTY.getDescr());
            }
        } catch (Exception e) {
            logger.error(String.format("通过蓝牙MAC(%s)获取包装箱信息出现异常", uuid), e);
            appResBoxBase.setRfid("");
            appResBoxBase.setUuid(uuid);
            appResBoxBase.setSize(0);
            appResBoxBase.setStatus(ErrorCode.ERROR.getIndex().intValue());
            appResBoxBase.setMessage(ErrorCode.ERROR.getDescr());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Query Box By BluetoothId <<--- " + new Gson().toJson(appResBoxBase));
        }
        return new Gson().toJson(appResBoxBase);
    }


    /***
     * 检测用户session是否过期
     * @param appReqBase
     * @return
     */
    @Override
    public boolean checkUserSessionId(AppReqBase appReqBase) {
        if (loginOnceFlag == 1) {

            String entCode = appReqBase.getEntCode();

            if (StringUtils.isNotBlank(appReqBase.getUserId())) {
                try {
                    return operatorInfoService.checkValidAppSession(appReqBase.getUserId(), appReqBase.getSessionId(), entCode);
                } catch (Exception e) {
                    logger.error(String.format("检测用户(%s)访问session是否过期出现异常", appReqBase.getUserId()), e);
                }

            }
            return false;
        } else {
            return true;
        }

    }


    /**
     * 查询指定位置附近的包装箱信息
     * @param appReqQueryBoxLocation
     * @return
     */
    @Override
    public String queryNearbyBoxes(AppReqQueryBoxLocation appReqQueryBoxLocation) {
        if (logger.isDebugEnabled()) {
            logger.debug("Query Nearby Boxes --->> " + new Gson().toJson(appReqQueryBoxLocation));
        }

        String entCode = appReqQueryBoxLocation.getEntCode();

        String res = "";
        AppAckContent ackContent = null;
        if (appReqQueryBoxLocation.getRadius() == null || appReqQueryBoxLocation.getLatitude() == null ||
                appReqQueryBoxLocation.getLongitude() == null || appReqQueryBoxLocation.getPageIndex() == null ||
                appReqQueryBoxLocation.getPageIndex() < 1 || appReqQueryBoxLocation.getPageSize() == null ||
                appReqQueryBoxLocation.getPageSize() <= 0 || appReqQueryBoxLocation.getStatus() == null) {
            ackContent = new AppAckContent();
            ackContent.setStatus(ErrorCode.REQUEST_PARAM_ERROR.getIndex().toString());
            ackContent.setMessage(ErrorCode.REQUEST_PARAM_ERROR.getDescr());
            res = new Gson().toJson(ackContent);
            logger.error(String.format("请求参数错误"));
        } else {
            Double precision = appReqQueryBoxLocation.getRadius() / 1000 * 0.01;
            List<BoxInfo> boxInfos = boxInfoService.queryNearbyBoxes(precision,
                    appReqQueryBoxLocation.getLatitude(),
                    appReqQueryBoxLocation.getLongitude(),
                    appReqQueryBoxLocation.getStatus(),
                    appReqQueryBoxLocation.getPageIndex(),
                    appReqQueryBoxLocation.getPageSize(),
                    entCode);

            AppResBoxLocation appResBoxLocation = new AppResBoxLocation();
            appResBoxLocation.setStatus(ErrorCode.OK.getIndex());
            appResBoxLocation.setMessage(ErrorCode.OK.getDescr());
            appResBoxLocation.setTotal(boxInfoService.countNearbyBoxes(appReqQueryBoxLocation.getRadius(),
                    appReqQueryBoxLocation.getLatitude(),
                    appReqQueryBoxLocation.getLongitude(),
                    appReqQueryBoxLocation.getStatus(),
                    entCode).intValue());
            appResBoxLocation.setSize(boxInfos.size());
            List<AppResBox> boxes = appResBoxLocation.getBoxes();
            AppResBox appResBox = null;
            for (BoxInfo boxInfo : boxInfos) {
                appResBox = new AppResBox();
                appResBox.setBoxId(boxInfo.getRfid());
                appResBox.setBarcode(boxInfo.getBarcode());
                appResBox.setBoxName(boxInfo.getBoxName());
                appResBox.setDuAddress(boxInfo.getDuAddress());
                appResBox.setDuLatitude(boxInfo.getDuLatitude());
                appResBox.setDuLongitude(boxInfo.getDuLongitude());
                appResBox.setOperatorId(boxInfo.getOperatorId());
                appResBox.setOperatorName(boxInfo.getOperator());
                appResBox.setOrderId(boxInfo.getOrderId());
                boxes.add(appResBox);
            }
            res = new Gson().toJson(appResBoxLocation);
        }
        return res;
    }

    /**
     * 查询app版本信息
     * @param appReqQueryVersion
     * @return
     */
    @Override
    public String queryAppVersion(AppReqQueryVersion appReqQueryVersion, String versionDesc) {

        if (logger.isDebugEnabled()) {
            logger.debug("queryAppVersion --->> " + versionDesc);
        }

        String res = "";
        AppResVersionInfo appResVersionInfo = new AppResVersionInfo();
        appResVersionInfo.setTotal(0);
        appResVersionInfo.setSize(1);

        FilterExample fe = new FilterExample();
        fe.createCriteria().andFieldEqualTo("param_name", versionDesc).andFieldEqualTo("param_status", 1);
        try {
            Optional<SystemParam> systemParamOptional = sysParamService.get(fe);
            if (systemParamOptional.isPresent()) {
                SystemParam systemParam = systemParamOptional.get();
                if (StringUtils.isNotBlank(systemParam.getParamValue())) {
                    appResVersionInfo.setStatus(ErrorCode.OK.getIndex().intValue());
                    appResVersionInfo.setMessage(ErrorCode.OK.getDescr());
                    appResVersionInfo.setVersion(systemParam.getParamValue());  // 版本号
                    appResVersionInfo.setRemark(systemParam.getParamDescr());  // 版本描述
                    appResVersionInfo.setUrl(systemParam.getParamRemark());   // 版本下载地址
                    res = new Gson().toJson(appResVersionInfo);
                }
            }
        } catch (Exception e) {
            logger.error("查询app版本出现异常");
        }

        // 返回错误
        if (StringUtils.isBlank(res)) {
            appResVersionInfo.setStatus(ErrorCode.VERSION_APP_QUERY_ERROR.getIndex().intValue());
            appResVersionInfo.setMessage(ErrorCode.VERSION_APP_QUERY_ERROR.getDescr());
            appResVersionInfo.setVersion("");
            appResVersionInfo.setRemark("");
            appResVersionInfo.setUrl("");
            res = new Gson().toJson(appResVersionInfo);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("queryAppVersion Ack --->> " + res);
        }
        return res;
    }

    /**
     * 查询揽货订单
     * @param appReqTakeOrder
     * @return
     */
    @Override
    public String queryTakeOrders(AppReqTakeOrder appReqTakeOrder) {
        // todo 获取分页信息下次在补充
        String res = "";
        AppResTakeOrder appResTakeOrder = new AppResTakeOrder();
        try {
            if (logger.isDebugEnabled()) {
                logger.debug("Query Take Orders --->> " + new Gson().toJson(appReqTakeOrder));
            }

            String entCode = appReqTakeOrder.getEntCode();

            List<AppTakeOrderInfo> takeOrderInfos = Lists.newArrayList();
            Integer total = 0;
            if (appReqTakeOrder != null) {
                List<OrderInfo> orderInfos = orderService.getTakeOrders(appReqTakeOrder.getOperatorId(), entCode);
                total = orderInfos.size();
                AppTakeOrderInfo appTakeOrderInfo = null;
                if (!orderInfos.isEmpty()) {
                    for (OrderInfo orderInfo : orderInfos) {
                        appTakeOrderInfo = new AppTakeOrderInfo();
                        appTakeOrderInfo.setCustomId(orderInfo.getCustomerId());
                        appTakeOrderInfo.setOrderId(orderInfo.getOrderId());
                        appTakeOrderInfo.setSender(orderInfo.getSenderName());
                        appTakeOrderInfo.setSenderTel(orderInfo.getSenderTel());
                        appTakeOrderInfo.setSenderAddress(orderInfo.getSenderAddress());
                        appTakeOrderInfo.setReceiver(orderInfo.getReceiverName());
                        appTakeOrderInfo.setReceiverTel(orderInfo.getReceiverTel());
                        appTakeOrderInfo.setReceiverAddress(orderInfo.getReceiverAddress());
                        appTakeOrderInfo.setRemark(orderInfo.getDetail());
                        // 获取订单需要包装箱信息
                        List<BoxTypeInfo> boxTypeInfos = boxInfoService.getBoxTypes(orderInfo.getOrderId());
                        List<AppBoxTypeInfo> appBoxTypeInfos = Lists.newArrayList();
                        if (boxTypeInfos != null) {
                            AppBoxTypeInfo appBoxTypeInfo = null;
                            for (BoxTypeInfo boxTypeInfo : boxTypeInfos) {
                                appBoxTypeInfo = new AppBoxTypeInfo();
                                appBoxTypeInfo.setTypeId(boxTypeInfo.getTypeId());
                                appBoxTypeInfo.setSize(boxTypeInfo.getSize());
                                appBoxTypeInfo.setName(boxTypeInfo.getName());
                                appBoxTypeInfo.setCount(boxTypeInfo.getCount());
                                appBoxTypeInfo.setColor(boxTypeInfo.getColor());
                                appBoxTypeInfos.add(appBoxTypeInfo);
                            }
                        }
                        appTakeOrderInfo.setBoxes(appBoxTypeInfos);
                        takeOrderInfos.add(appTakeOrderInfo);
                    }
                }
            }
            appResTakeOrder.setOrders(takeOrderInfos);
            appResTakeOrder.setTotal(total);
            appResTakeOrder.setSize(total);
            appResTakeOrder.setStatus(ErrorCode.OK.getIndex());
            appResTakeOrder.setMessage(ErrorCode.OK.getDescr());

            res = new Gson().toJson(appResTakeOrder);
        } catch (Exception e) {
            logger.error("查询揽货订单出现异常", e);
            appResTakeOrder.setOrders(Lists.<AppTakeOrderInfo>newArrayList());
            appResTakeOrder.setTotal(0);
            appResTakeOrder.setSize(0);
            appResTakeOrder.setStatus(ErrorCode.ERROR.getIndex());
            appResTakeOrder.setMessage(ErrorCode.ERROR.getDescr());

            res = new Gson().toJson(appResTakeOrder);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("Query Take Orders Ack --->> " + new Gson().toJson(appResTakeOrder));
        }
        return res;
    }

    /**
     * 推送操作人员位置
     * @param appReqPushOpLocation
     * @return
     */
    @Override
    public String pushOperatorLocation(final AppReqPushOpLocation appReqPushOpLocation) {

        // 位置信息比较频繁，交给线程池处理
        poolTaskExecutor.execute(() -> {
            boolean res = false;
            try {
                if (logger.isDebugEnabled()) {
                    logger.debug("Push Operator Location --->> " + new Gson().toJson(appReqPushOpLocation));
                }

                String entId = enterpriseService.getEntIdByEntCode(appReqPushOpLocation.getEntCode().replace("_",""));
                boolean checkRes = operatorLocationService.checkExist(appReqPushOpLocation.getOperatorName(), entId);
                OperatorLocation operatorLocation = new OperatorLocation();
                operatorLocation.setCoordType(appReqPushOpLocation.getCoordType());
                operatorLocation.setEntId(entId);
                operatorLocation.setLatitude(appReqPushOpLocation.getLatitude());
                operatorLocation.setLongitude(appReqPushOpLocation.getLongitude());
                operatorLocation.setModifyTime(new Date());
                operatorLocation.setOperatorId(appReqPushOpLocation.getOperatorId());
                operatorLocation.setOperatorName(appReqPushOpLocation.getOperatorName());
                operatorLocation.setAddress(appReqPushOpLocation.getAddress());
                if (checkRes) { // 更新
                    res = operatorLocationService.updateLocation(operatorLocation);
                } else {
                    operatorLocation.setCreateTime(new Date());
                    res = operatorLocationService.saveLocation(operatorLocation);
                }

                // 查询该操作人员名下"运输"、"回收"状态的包装箱信息
                List<Byte> boxStatus = Lists.newArrayList();
                boxStatus.add(BoxStatus.TRANSPORTING.getIndex().byteValue());
                boxStatus.add(BoxStatus.RECYCLE.getIndex().byteValue());
                List<BoxInfoVO> boxInfos = boxInfoService.queryBoxesByOperatorId(boxStatus, appReqPushOpLocation.getOperatorId(), appReqPushOpLocation.getEntCode());

                BoxInfo boxInfo = null;
                for (BoxInfoVO itemBoxInfo : boxInfos) {
                    if (appReqPushOpLocation.getLatitude().longValue() != Double.MIN_VALUE && appReqPushOpLocation.getLongitude() != Double.MIN_VALUE) {

                        boolean checkLocationRes = LocationCache.checkRepeat(itemBoxInfo.getOrderId(), itemBoxInfo.getRfid(), appReqPushOpLocation.getLatitude(), appReqPushOpLocation.getLongitude());
                        if (!checkLocationRes) { // 重复数据不保存
                            boxInfo = new BoxInfo();
                            boxInfo.setRfid(itemBoxInfo.getRfid());
                            boxInfo.setDuAddress(appReqPushOpLocation.getAddress());
                            boxInfo.setDuCoordType(appReqPushOpLocation.getCoordType());
                            boxInfo.setDuLatitude(appReqPushOpLocation.getLatitude());
                            boxInfo.setDuLongitude(appReqPushOpLocation.getLongitude());
                            boxInfo.setUpdateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                            boxInfoService.updateBoxInfoByRfid(boxInfo, appReqPushOpLocation.getEntCode());

                            BoxLocationRecordInfo recordInfo = new BoxLocationRecordInfo();
                            recordInfo.setRfid(itemBoxInfo.getRfid());
                            recordInfo.setOrderId(itemBoxInfo.getOrderId());
                            recordInfo.setDuAddress(appReqPushOpLocation.getAddress());
                            recordInfo.setDuCoordType(appReqPushOpLocation.getCoordType());
                            recordInfo.setDuLatitude(appReqPushOpLocation.getLatitude());
                            recordInfo.setDuLongitude(appReqPushOpLocation.getLongitude());
                            recordInfo.setCreateTime(new Date());
                            recordInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));

                            boxLocationRecordService.saveBoxLocation(recordInfo, appReqPushOpLocation.getEntCode());
                        }
                    } else {
                        if (logger.isDebugEnabled()) {
                            logger.debug("上传坐标信息不正确");
                        }
                    }
                }

            } catch (Exception e) {
                logger.error("推送操作人员位置信息出现异常");
            }
        });

        AppResBase appResBase = new AppResBase();
        appResBase.setStatus(ErrorCode.OK.getIndex());
        appResBase.setMessage(ErrorCode.OK.getDescr());

        return new Gson().toJson(appResBase);
    }

    /**
     * 查询线路
     * @param appReqLines
     */
    @Override
    public String queryLines(AppReqLines appReqLines) {

        if (logger.isDebugEnabled()) {
            logger.debug("Query Lines --->> " + new Gson().toJson(appReqLines));
        }

        List<LineInfo> lineInfos = lineService.getLinesWithOperatorId(appReqLines.getOperatorId(), appReqLines.getEntCode());
        AppResLines appResLines = new AppResLines();
        List<AppLinesEntity> lines = Lists.newArrayList();
        AppLinesEntity appLinesEntity = null;
        for (LineInfo lineInfo : lineInfos) {
            appLinesEntity = new AppLinesEntity();
            appLinesEntity.setLineId(lineInfo.getLineId());
            appLinesEntity.setLineName(lineInfo.getLineName());
            appLinesEntity.setFlag(String.valueOf(lineInfo.getFlag()));
            lines.add(appLinesEntity);
        }
        appResLines.setLines(lines);
        appResLines.setStatus(ErrorCode.OK.getIndex());
        appResLines.setMessage(ErrorCode.OK.getDescr());

        if (logger.isDebugEnabled()) {
            logger.debug("Query Lines Ack --->> " + new Gson().toJson(appResLines));
        }
        return new Gson().toJson(appResLines);
    }

    /**
     * 设置线路
     * @param appReqLineSetting
     * @return
     */
    @Override
    public String settingLine(AppReqLineSetting appReqLineSetting) {

        if (logger.isDebugEnabled()) {
            logger.debug("Query Setting Line --->> " + new Gson().toJson(appReqLineSetting));
        }

        AppResBase appResBase = new AppResBase();
        boolean res = lineService.settingLines(appReqLineSetting.getOperatorId(), appReqLineSetting.getLineIds(), appReqLineSetting.getEntCode());
        if (res) {
            appResBase.setStatus(ErrorCode.OK.getIndex());
            appResBase.setMessage(ErrorCode.OK.getDescr());
        } else {
            appResBase.setStatus(ErrorCode.ERROR.getIndex());
            appResBase.setMessage(ErrorCode.ERROR.getDescr());
        }


        if (logger.isDebugEnabled()) {
            logger.debug("Query Setting Line --->> " + new Gson().toJson(appResBase));
        }
        return new Gson().toJson(appResBase);
    }


    /**
     * 包装箱信息内容的转换
     * BoxInfo -> AppResBox
     * @param listResBox
     * @param listBox
     * @param entCode
     */
    private void getBoxes(List<AppResBox> listResBox, List<BoxInfo> listBox, String entCode) {
        AppResBox appResBox = null;
        OrderDriverInfo nextOrderDriver = null;
        String tid = "";
        List<String> tempBoxIds = Lists.newArrayList();

        Map<String, String> boxIdMap = boxBaseService.getBoxIdMap(entCode);  // rfid -- tid的对照表


        // 获取相关订单信息
        List<String> orderIds = Lists.newArrayList();
        for (BoxInfo boxInfo : listBox) {
            orderIds.add(boxInfo.getOrderId());
        }
        Map<String, OrderInfo> orders = orderService.getOrdersByIds(orderIds, entCode);

        for (BoxInfo boxInfo : listBox) {
            appResBox = new AppResBox();

            if (tempBoxIds.contains(boxInfo.getRfid())) {  // 去掉重复的包装箱，当多条线路配置了同一客户会产生多条包装箱数据
                continue;
            } else {
                tempBoxIds.add(boxInfo.getRfid());
            }

            // 英特小读卡器、pad读取的是tid，则需要返回tid给app
            tid = boxIdMap.get(boxInfo.getRfid());

            appResBox.setBoxId(tid);
            appResBox.setBarcode(boxInfo.getBarcode());
            appResBox.setBoxName(boxInfo.getBoxName());
            if (boxInfo.getStatus().byteValue() == BoxStatus.TRANSIT.getIndex().byteValue()) {  // 中转状态的包装箱，给前端为配货状态
                appResBox.setBoxStatus(BoxStatus.BINDING.getIndex().byteValue());
            } else {
                appResBox.setBoxStatus(boxInfo.getStatus());
            }
            appResBox.setSignStatus(boxInfo.getSignStatus());
            appResBox.setDetail(boxInfo.getDetail());
            appResBox.setDuAddress(boxInfo.getDuAddress());
            appResBox.setDuCoordType(boxInfo.getDuCoordType());
            appResBox.setDuLatitude(boxInfo.getDuLatitude());
            appResBox.setDuLongitude(boxInfo.getDuLongitude());
            appResBox.setBleMac(boxInfo.getBtMac());
            appResBox.setNextStationId(boxInfo.getNextTransferId());
            appResBox.setNextStation(boxInfo.getNextTransferName());
            if (boxInfo.getOperateTime() != null) {
                appResBox.setOperatorDate(new DateTime(boxInfo.getOperateTime()).toString("yyyy-MM-dd HH:mm:ss"));
            } else {
                appResBox.setOperatorDate("");
            }

            // 获取订单实体信息
            getOrderEntity(appResBox, orders, boxInfo, entCode);


            appResBox.setOperatorId(boxInfo.getOperatorId());
            appResBox.setOperatorName(boxInfo.getOperator());
//             todo 目前没有这个关联
//            if (boxInfo.getOrderId() != null) {
//                nextOrderDriver = orderDriverService.queryNextDriver(boxInfo.getOperatorId(), boxInfo.getOrderId(), entCode);
//                if (nextOrderDriver != null) {
//                    appResBox.setNextDriverId(nextOrderDriver.getDriverId());  // 通过订单司机关联表获取
//                    appResBox.setNextDriverName(nextOrderDriver.getDriverName());  // 同上
//                }
//            }
            appResBox.setOrderId(boxInfo.getOrderId());

            listResBox.add(appResBox);
        }
    }


    /**
     * 获取订单实体
     * @param appResBox
     * @param boxInfo
     * @param entCode
     * @return
     */
    private void getOrderEntity(AppResBox appResBox, Map<String, OrderInfo> orders, BoxInfo boxInfo, String entCode) {

        OrderInfo orderInfo = null;
        if (orders.containsKey(boxInfo.getOrderId())) {  // 从orders中获取数据
            orderInfo = orders.get(boxInfo.getOrderId());
        }
        if (orderInfo == null) { // 从数据库获取数据
            orderInfo = orderService.getOrderById(boxInfo.getOrderId(), entCode); // 从B端中获取数据
        }

        AppOrderEntity appOrderEntity = new AppOrderEntity();
        if (orderInfo != null) {
            appResBox.setCustomId(orderInfo.getCustomerId());  // 通过订单获取
            appResBox.setCustomName(orderInfo.getCustomer());  // 通过订单获取

            appOrderEntity.setOrderType(OrderType.BUSINESS.getIndex().byteValue());
            appOrderEntity.setCustomerId(orderInfo.getCustomerId());
            appOrderEntity.setCustomer(orderInfo.getCustomer());
            appOrderEntity.setReceiverId(orderInfo.getCustomerId());
            appOrderEntity.setReceiverName(orderInfo.getCustomer());
        } else {
            appResBox.setCustomId("");  // 通过订单获取
            appResBox.setCustomName("");  // 通过订单获取

            try {
                FilterExample fe = new FilterExample();
                fe.createCriteria().andFieldEqualTo("order_id", boxInfo.getOrderId());
                Optional<OrderDO> orderDOOptional = orderService.get(fe);
                if (orderDOOptional.isPresent()) {
                    OrderDO orderDO = orderDOOptional.get();
                    appOrderEntity.setOrderType(OrderType.CONSUMER.getIndex().byteValue());
                    appOrderEntity.setCustomerId(orderDO.getCustomerId());
                    appOrderEntity.setCustomer(orderDO.getCustomer());
                    appOrderEntity.setOrderId(orderDO.getOrderId());
                    appOrderEntity.setSenderId(orderDO.getSenderId());
                    appOrderEntity.setSenderName(orderDO.getSenderName());
                    appOrderEntity.setSenderTel(orderDO.getSenderTel());
                    appOrderEntity.setSenderAddress(orderDO.getSenderAddress());
                    appOrderEntity.setReceiverId(orderDO.getReceiverId());
                    appOrderEntity.setReceiverName(orderDO.getReceiverName());
                    appOrderEntity.setReceiverTel(orderDO.getReceiverTel());
                    appOrderEntity.setReceiverAddress(orderDO.getReceiverAddress());

                    appResBox.setCustomId(orderDO.getReceiverTel());      // 收件没有在系统注册，则不存在，使用手机替代
                    appResBox.setCustomName(orderDO.getReceiverName());  // 通过订单获取
                }
            } catch (Exception e) {
                logger.error(String.format("通过订单ID(%s)获取订单信息", boxInfo.getOrderId()));
            }
        }
        appResBox.setAppOrderEntity(appOrderEntity);
    }

    /**
     * 送达同步到历史信息
     * @param appPushBoxInfo
     * @param boxInfoNew
     * @param entCode
     */
    private void syncHistoryInfo(AppPushBoxInfo appPushBoxInfo, BoxInfo boxInfoNew, String entCode) {

        poolTaskExecutor.execute(() -> {
            if (appPushBoxInfo.getStatus().byteValue() == BoxStatus.RETENTION.getIndex().byteValue() ||
                    appPushBoxInfo.getStatus().byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue()) {

                if (appPushBoxInfo.getSignStatus().byteValue() == SignStatus.SIGN_IN.getIndex().byteValue() ||
                        appPushBoxInfo.getSignStatus().byteValue() == SignStatus.DENIED_SIGN.getIndex().byteValue()) {

                    if (boxInfoNew != null) {
                        // 历史订单信息
                        OrderBoxInfo orderBoxInfo = new OrderBoxInfo();
                        orderBoxInfo.setRfid(boxInfoNew.getRfid());
                        orderBoxInfo.setBarcode(boxInfoNew.getBarcode());
                        orderBoxInfo.setBoxName(boxInfoNew.getBoxName());
                        orderBoxInfo.setDetail(boxInfoNew.getDetail());
                        orderBoxInfo.setOrderId(boxInfoNew.getOrderId());
                        orderBoxInfo.setStatus(ValidStatus.VALID.getIndex().byteValue());
                        orderBoxInfo.setSignStatus(appPushBoxInfo.getSignStatus().byteValue());


                        Integer orderBoxCount = orderBoxService.countOrderBoxByRfidAndBarCode(boxInfoNew.getRfid(), boxInfoNew.getBarcode(), entCode);
                        if (orderBoxCount > 0) {
                            orderBoxService.updateOrderBox(orderBoxInfo, entCode);
                        } else {
                            orderBoxInfo.setPkgId(SnowflakeIdWorker.getInstance().nextId());  // 生成系统自定义ID
                            orderBoxInfo.setCreateTime(new Date());
                            orderBoxInfo.setCreateTimeStr(DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
                            orderBoxService.saveOrderBox(orderBoxInfo, entCode);
                        }
                    } else {
                        logger.error(String.format("通过rfid（%s）获取包装箱信息出现异常", appPushBoxInfo.getRfid()));
                    }
                }

            }
        });

    }


}
