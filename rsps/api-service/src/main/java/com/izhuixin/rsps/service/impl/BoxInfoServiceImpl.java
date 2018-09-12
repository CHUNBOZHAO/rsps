package com.izhuixin.rsps.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.izhuixin.rsps.common.constant.BoxStatus;
import com.izhuixin.rsps.common.constant.LineRelatedType;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.common.object.Pair;
import com.izhuixin.rsps.common.page.Paginator;
import com.izhuixin.rsps.common.vo.web.BoxInfoVO;
import com.izhuixin.rsps.common.vo.web.BoxesCustomInfoVO;
import com.izhuixin.rsps.common.vo.web.BoxesOperatorInfoVO;
import com.izhuixin.rsps.dao.manual.BoxInfoDao;
import com.izhuixin.rsps.dao.manual.BoxTypeDao;
import com.izhuixin.rsps.dao.manual.LineTransferDao;
import com.izhuixin.rsps.dao.manual.OrderDao;
import com.izhuixin.rsps.domain.automatic.BoxInfoDO;
import com.izhuixin.rsps.domain.manual.*;
import com.izhuixin.rsps.service.BoxBaseService;
import com.izhuixin.rsps.service.BoxInfoService;
import com.izhuixin.rsps.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Map;

@Service
public class BoxInfoServiceImpl extends AbstractCrudService<BoxInfoDO> implements BoxInfoService {

    @Autowired
    private BoxInfoDao boxInfoDao;

    @Autowired
    private BoxTypeDao boxTypeDao;

    @Autowired
    private LineTransferDao lineTransferDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private BoxBaseService boxBaseService;

    @Autowired
    private TransactionTemplate transactionTemplate;


    /**
     * 通过rfid获取包装箱信息
     * @param rfid
     * @param entCode
     * @return
     */
    @Override
    public BoxInfo getBoxInfoByRfid(String rfid, String entCode) {
        BoxInfo boxInfo = null;
        try {
            boxInfo = boxInfoDao.queryBoxByRfid(rfid, entCode);
        } catch (Exception e) {
            logger.error(String.format("通过rfid(%s)获取包装信息出现异常", rfid), e);
        }
        return boxInfo;
    }

    @Override
    public List<BoxInfo> getBoxInfoByOrderid(String orderId, String entCode) {
        List<BoxInfo> boxInfos = null;
        try {
            boxInfos = boxInfoDao.queryBoxesByOrderId(orderId, entCode);
        } catch (Exception e) {
            logger.error(String.format("通过订单ID(%s)获取包装箱信息出现异常", orderId), e);
        }
        if (boxInfos == null) {
            boxInfos = Lists.newArrayList();
        }
        return boxInfos;
    }

    /**
     * 通过指定条件获取包装箱信息
     * @param operatorId
     * @param boxStatus
     * @param beginTime
     * @param endTime
     * @param paginator
     * @return
     */
    @Override
    public List<BoxInfo> queryBoxes(String operatorId,
                                    List<Byte> boxStatus,
                                    Long beginTime,
                                    Long endTime,
                                    Paginator paginator,
                                    String entCode) {
        List<BoxInfo> listBox = null;
        try {
            paginator.setItems(boxInfoDao.countBoxesByCondition(operatorId, boxStatus, beginTime, endTime, entCode));
            listBox = boxInfoDao.queryBoxesByCondition(operatorId, boxStatus, beginTime, endTime, entCode, paginator.rowBounds());
        } catch (Exception e) {
            logger.error("获取包装箱信息出现异常", e);
        }

        if (listBox == null) {
            listBox = Lists.newArrayList();
        }
        return listBox;
    }

    /**
     * 获取配货状态及指定操作的包装箱信息
     * @param operatorId
     * @param paginator
     * @param entCode
     * @return
     */
    @Override
    public List<BoxInfo> queryPendingBoxes(String operatorId, String entCode, Paginator paginator) {
        List<BoxInfo> listBox = null;
        try {
            paginator.setItems(boxInfoDao.countPendingBoxes(operatorId, entCode));
            listBox = boxInfoDao.queryPendingBoxes(operatorId, entCode, paginator.rowBounds());
        } catch (Exception e) {
            logger.error("获取包装箱信息出现异常", e);
        }

        if (listBox == null) {
            listBox = Lists.newArrayList();
        }
        return listBox;
    }

    /**
     * 获取操作人线路的包装箱信息
     * @param operatorId
     * @param entCode
     * @return
     */
    @Override
    public List<BoxInfo> queryLineBoxes(String operatorId, String entCode) {
        List<BoxInfo> newListBox = Lists.newArrayList();
        try {
            List<BoxInfo> listBox = boxInfoDao.queryLineBoxes(operatorId, entCode);

            if (listBox != null) {
                List<String> curOpTransferIds = lineTransferDao.getTransferIdsByOperatorId(operatorId, entCode);  // 获取配送员能装车的中转站点（即该配送员涉及线路所在中转站点）
                for (BoxInfo boxInfo : listBox) {
                    if (curOpTransferIds.contains(boxInfo.getBeginTransferId())) {
                        newListBox.add(boxInfo);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取包装箱信息出现异常", e);
        }

        return newListBox;
    }

    /**
     * 获取中转站点的待装车包装箱
     * @param operatorId
     * @param entCode
     * @return
     */
    @Override
    public List<BoxInfo> queryTransferBoxes(String operatorId, String entCode) {

        // 当前操作人对应线路配置的所有中转
        List<String> transferIds = lineTransferDao.getRelatedLineTransferIds(operatorId, entCode);

        List<String> customIds = Lists.newArrayList();
        Map<String, Pair<String, String>> customTransferMap = Maps.newHashMap();  // <客户ID，中转站名称>
        for (String transferId : transferIds) {
            SysUserInfo sysUserInfo = sysUserService.getUserInfoByUserId(transferId);
            if (sysUserInfo != null) {
                queryDeepBoxes(transferId, entCode, customIds, customTransferMap, sysUserInfo); // 递归获取客户信息
            }
        }

        List<BoxInfo> newBoxInfos = Lists.newArrayList();
        if (customIds.size() > 0) {
            List<BoxInfo> boxInfos = boxInfoDao.queryBoxesForCustoms(customIds, entCode);  // 符合的包装箱
            for (BoxInfo boxInfo : boxInfos) {
                Pair<String, String> transferInfo = customTransferMap.get(boxInfo.getCustomId());
                if (transferInfo == null) {
                    continue;
                }
                boxInfo.setNextTransferId(transferInfo.getFirst());
                boxInfo.setNextTransferName(transferInfo.getSecond());

                List<String> curOpTransferIds = lineTransferDao.getTransferIdsByOperatorId(operatorId, entCode);  // 获取配送员能装车的中转站点（即该配送员涉及线路所在中转站点）
                if (curOpTransferIds.contains(boxInfo.getBeginTransferId())) {
                    newBoxInfos.add(boxInfo);
                }
            }
        }
        return newBoxInfos;
    }

    /**
     * 递归获取客户信息
     * @param transferId
     * @param entCode
     * @param customIds
     */
    private void queryDeepBoxes(String transferId,
                                String entCode,
                                List<String> customIds,
                                Map<String, Pair<String, String>> customTransferMap,
                                SysUserInfo sysUserInfo) {
        List<LineRelatedInfo> lineRelatedInfos = lineTransferDao.getLineRelatedInfos(transferId, entCode); // 获取中转站下所有线路的关联信息
        for (LineRelatedInfo lineRelatedInfo : lineRelatedInfos) {
            if (lineRelatedInfo.getRelatedType().intValue() == LineRelatedType.CUSTOM.getIndex().intValue()) { // 客户
                customIds.add(lineRelatedInfo.getRelatedId());
                customTransferMap.put(lineRelatedInfo.getRelatedId(), Pair.of(sysUserInfo.getUserId(), sysUserInfo.getNickname()));
            } else {  // 中转站
                queryDeepBoxes(lineRelatedInfo.getRelatedId(), entCode, customIds, customTransferMap, sysUserInfo);  // 中转用户下所有线路对应客户, 中转
            }
        }
    }

    /**
     * 获取配送员线路上客户-中转点对应信息
     * @param operatorId
     * @param entCode
     * @return
     */
    @Override
    public Map<String, Pair<String, String>> queryCustomTransferMap(String operatorId, String entCode) {

        // 当前操作人对应线路配置的所有中转
        List<String> transferIds = lineTransferDao.getRelatedLineTransferIds(operatorId, entCode);

        Map<String, Pair<String, String>> customTransferMap = Maps.newHashMap();  // <客户ID，中转站名称>
        List<String> customIds = Lists.newArrayList();
        for (String transferId : transferIds) {
            SysUserInfo sysUserInfo = sysUserService.getUserInfoByUserId(transferId);
            if (sysUserInfo != null) {
                queryDeepBoxes(transferId, entCode, customIds, customTransferMap, sysUserInfo); // 递归获取客户信息
            }
        }
        return customTransferMap;
    }

    /**
     * 获取C端操作人线路的包装箱信息
     * @param operatorId
     * @param paginator
     * @param entCode
     * @return
     */
    @Override
    public List<BoxInfo> queryCLineBoxes(String operatorId, String entCode, Paginator paginator) {
        List<BoxInfo> listBox = null;
        try {
            paginator.setItems(boxInfoDao.countCLineBoxes(operatorId, entCode));
            listBox = boxInfoDao.queryCLineBoxes(operatorId, entCode, paginator.rowBounds());
        } catch (Exception e) {
            logger.error("获取C端线路上包装箱信息出现异常", e);
        }

        if (listBox == null) {
            listBox = Lists.newArrayList();
        }
        return listBox;
    }

    /**
     * 获取运输中的箱子的数量
     * @return
     */
    @Override
    public Long countTransportingBoxes(String entCode) {
        Long count = 0l;
        try {
            List<Byte> listStatus = Lists.newArrayList();
            listStatus.add(BoxStatus.TRANSPORTING.getIndex().byteValue());
            listStatus.add(BoxStatus.TRANSIT.getIndex().byteValue());
            count = boxInfoDao.countBoxesByStatus(listStatus, entCode);
        } catch (Exception e) {
            logger.error("获取运输状态的箱子数量出现异常", e);
        }
        return count;
    }

    /***
     * 获取滞留中的箱子数量
     * @return
     */
    @Override
    public Long countRetentionBoxes(String entCode) {
        Long count = 0l;
        try {
            List<Byte> listStatus = Lists.newArrayList();
            listStatus.add(BoxStatus.RETENTION.getIndex().byteValue());
            count = boxInfoDao.countBoxesByStatus(listStatus, entCode);
        } catch (Exception e) {
            logger.error("获取运输状态的箱子数量出现异常", e);
        }
        return count;
    }

    /**
     * 获取回收中箱子的数量
     * @return
     */
    @Override
    public Long countRecycleBoxes(String entCode) {
        Long count = 0l;
        try {
            List<Byte> listStatus = Lists.newArrayList();
            listStatus.add(BoxStatus.RECYCLE.getIndex().byteValue());
            count = boxInfoDao.countBoxesByStatus(listStatus, entCode);
        } catch (Exception e) {
            logger.error("获取运输状态的箱子数量出现异常", e);
        }
        return count;
    }

    /**
     * 获取闲置中箱子的数量
     * @return
     */
    @Override
    public Long countLeisureBoxes(String entCode) {
        Long count = 0l;
        try {
            List<Byte> listStatus = Lists.newArrayList();
            listStatus.add(BoxStatus.LEISURE.getIndex().byteValue());
            count = boxInfoDao.countBoxesByStatus(listStatus, entCode);
        } catch (Exception e) {
            logger.error("获取运输状态的箱子数量出现异常", e);
        }
        return count;
    }

    /***
     * 获取配货中箱子的数量
     * @return
     */
    @Override
    public Long countBindingBoxes(String entCode) {
        Long count = 0l;
        try {
            List<Byte> listStatus = Lists.newArrayList();
            listStatus.add(BoxStatus.BINDING.getIndex().byteValue());
            count = boxInfoDao.countBoxesByStatus(listStatus, entCode);
        } catch (Exception e) {
            logger.error("获取运输状态的箱子数量出现异常", e);
        }
        return count;
    }


    /**
     * 获取指定状态的包装箱信息
     * @return
     */
    @Override
    public List<BoxInfoVO> queryBoxesByStatus(List<Byte> listStatus, String entCode) {
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();
        try {
            List<BoxInfo> boxInfos = boxInfoDao.queryBoxes(listStatus, entCode);
            if (boxInfos != null && !boxInfos.isEmpty()) {
                for (BoxInfo boxInfo : boxInfos) {
                    boxInfoVOS.add(toBoxInfoVO(boxInfo));
                }
            }
        } catch (Exception e) {
            logger.error("获取运输状态的箱子数量出现异常", e);
        }
        return boxInfoVOS;
    }

    /**
     * 获取指定状态下包装箱被使用的客户信息
     * @param listStatus
     * @return
     */
    @Override
    public List<BoxesCustomInfoVO> queryCustomInfos(List<Byte> listStatus, String entCode) {
        List<BoxesCustomInfo> boxesCustomInfos = boxInfoDao.queryCustomsWithBox(listStatus, entCode);
        List<BoxesCustomInfoVO> customInfoVOS = Lists.newArrayList();
        BoxesCustomInfoVO boxesCustomInfoVO = null;
        for (BoxesCustomInfo boxesCustomInfo : boxesCustomInfos) {
            boxesCustomInfoVO = new BoxesCustomInfoVO();
            boxesCustomInfoVO.setCustomId(boxesCustomInfo.getCustomId());
            boxesCustomInfoVO.setCustomName(boxesCustomInfo.getCustomName());
            boxesCustomInfoVO.setCount(boxesCustomInfo.getCount());
            boxesCustomInfoVO.setCustomShowId(boxesCustomInfo.getCustomShowId());
            boxesCustomInfoVO.setCustomShowName(boxesCustomInfo.getCustomShowName());
            customInfoVOS.add(boxesCustomInfoVO);
        }
        return customInfoVOS;
    }

    /**
     * 获取指定装下包装箱归属操作人信息
     * @param listStatus
     * @return
     */
    @Override
    public List<BoxesOperatorInfoVO> queryOperators(List<Byte> listStatus, String entCode) {
        List<BoxesOperatorInfo> boxesOperatorInfos = boxInfoDao.queryOperatorsWithBox(listStatus, entCode);
        List<BoxesOperatorInfoVO> boxesOperatorInfoVOS = Lists.newArrayList();
        BoxesOperatorInfoVO operatorInfoVO = null;
        for (BoxesOperatorInfo operatorInfo : boxesOperatorInfos) {
            operatorInfoVO = new BoxesOperatorInfoVO();
            operatorInfoVO.setOperatorId(operatorInfo.getOperatorId());
            operatorInfoVO.setOperator(operatorInfo.getOperator());
            operatorInfoVO.setCount(operatorInfo.getCount());
            boxesOperatorInfoVOS.add(operatorInfoVO);
        }
        return boxesOperatorInfoVOS;
    }

    /**
     * 通过客户ID、状态查询包装箱信息
     * @param listStatus
     * @param customId
     * @return
     */
    @Override
    public List<BoxInfoVO> queryBoxesByCustomId(List<Byte> listStatus, String customId, String entCode) {
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();
        try {
            List<BoxInfo> boxInfos = boxInfoDao.queryBoxesByCustomId(listStatus, customId, entCode);
            if (boxInfos != null && !boxInfos.isEmpty()) {
                for (BoxInfo boxInfo : boxInfos) {
                    BoxInfoVO boxInfoVO = toBoxInfoVO(boxInfo);
//                    Integer duration = 0;
//                    if (boxInfo.getOperateTime() != null) {
//                        duration = Math.round((DateTime.now().getMillis() - boxInfo.getOperateTime().getTime()) / 1000 / 60 / 60);
//                    }

                    Period p = new Period(new DateTime(boxInfo.getOperateTime()), DateTime.now(), PeriodType.minutes());
                    int hour = p.getMinutes() / 60;
                    int minute = p.getMinutes() % 60;
                    String duration = hour + "小时" + minute + "分钟";

                    boxInfoVO.setDuration(duration);
                    boxInfoVOS.add(boxInfoVO);
                }
            }
        } catch (Exception e) {
            logger.error(String.format("通过客户ID(%s)、状态查询包装箱信息出现异常", customId), e);
        }
        return boxInfoVOS;
    }

    /**
     * 通过操作人ID、状态查询包装箱信息
     * @param listStatus
     * @param operatorId
     * @return
     */
    @Override
    public List<BoxInfoVO> queryBoxesByOperatorId(List<Byte> listStatus, String operatorId, String entCode) {
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();
        try {
            List<BoxInfo> boxInfos = boxInfoDao.queryBoxesByOperatorId(listStatus, operatorId, entCode);
            if (boxInfos != null && !boxInfos.isEmpty()) {
                for (BoxInfo boxInfo : boxInfos) {
                    BoxInfoVO boxInfoVO = toBoxInfoVO(boxInfo);

                    // 获取持续时间
//                    Integer duration = 0;
//                    if (boxInfo.getOperateTime() != null) {
//                        duration = Math.round((DateTime.now().getMillis() - boxInfo.getOperateTime().getTime()) / 1000 / 60 / 60);
//                    }

                    Period p = new Period(new DateTime(boxInfo.getOperateTime()), DateTime.now(), PeriodType.minutes());
                    int hour = p.getMinutes() / 60;
                    int minute = p.getMinutes() % 60;
                    String duration = hour + "小时" + minute + "分钟";

                    boxInfoVO.setDuration(duration);

                    // 获取客户名称
                    String customer = boxInfoVO.getCustomName();
                    try {
//                        customer = boxInfoDao.queryCustomerByBox(boxInfo.getRfid(), entCode);
                        if (StringUtils.isBlank(customer)) { // 如果名称为空，则获取订单寄件人名称
                            OrderInfo orderInfo = orderDao.getOrder(boxInfo.getOrderId());
                            if (orderInfo != null) {
                                customer = orderInfo.getReceiverName();
                            }
                        }
                    } catch (Exception e) {
                        logger.error(String.format("获取回收状态下前的包装箱(%s)使用客户名称失败", boxInfo.getRfid()), e);
                    }
                    boxInfoVO.setReceiver(customer);

                    boxInfoVOS.add(boxInfoVO);
                }
            }
        } catch (Exception e) {
            logger.error(String.format("通过操作人ID(%s)、状态查询包装箱信息出现异常", operatorId), e);
        }
        return boxInfoVOS;
    }

    /**
     * 查询所有的包装箱信息
     * @return
     */
    @Override
    public List<BoxInfoVO> queryAllBoxes(String entCode) {
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();
        try {
            List<BoxInfo> boxInfos = boxInfoDao.queryAllBoxes(entCode);
            if (boxInfos != null && !boxInfos.isEmpty()) {
                for (BoxInfo boxInfo : boxInfos) {
                    boxInfoVOS.add(toBoxInfoVO(boxInfo));
                }
            }
        } catch (Exception e) {
            logger.error("查询所有包装箱信息出现异常", e);
        }
        return boxInfoVOS;
    }

    /**
     * 获取所有闲置包装项目
     * @param entCode
     * @return
     */
    @Override
    public List<BoxInfoVO> queryLeisureBoxes(String entCode) {
        List<BoxInfoVO> boxInfoVOS = Lists.newArrayList();
        try {
            List<BoxInfo> boxInfos = boxInfoDao.queryLeisureBoxes(entCode);
            if (boxInfos != null && !boxInfos.isEmpty()) {
                for (BoxInfo boxInfo : boxInfos) {
                    BoxInfoVO boxInfoVO = toBoxInfoVO(boxInfo);

                    // 获取持续时间
                    if (boxInfo.getOperateTime() == null) {  // 如果操作时间为null,则操作时间为注册时间
                        boxInfo.setOperateTime(boxInfo.getCreateTime());
                    }
//                    Integer duration = 0;
//                    if (boxInfo.getOperateTime() != null) {
//                        duration = Math.round((DateTime.now().getMillis() - boxInfo.getOperateTime().getTime()) / 1000 / 60 / 60);
//                    }
                    boxInfoVO.setOperateTime(boxInfo.getOperateTime());

                    Period p = new Period(new DateTime(boxInfo.getOperateTime()), DateTime.now(), PeriodType.minutes());
                    int hour = p.getMinutes() / 60;
                    int minute = p.getMinutes() % 60;
                    String duration = hour + "小时" + minute + "分钟";
                    boxInfoVO.setDuration(duration);

                    boxInfoVOS.add(boxInfoVO);
                }
            }
        } catch (Exception e) {
            logger.error("查询所有闲置包装箱信息出现异常", e);
        }
        return boxInfoVOS;
    }

    /**
     * 获取附件的包装信息总数
     * @param precision
     * @param lat
     * @param lon
     * @return
     */
    @Override
    public Long countNearbyBoxes(Double precision, Double lat, Double lon, List<Byte> status, String entCode) {
        Long total = 0l;
        try {
            total = boxInfoDao.countNearbyBoxes(precision, lat, lon, status, entCode);
        } catch (Exception e) {
            logger.error("查询附件的包装箱总数出现异常", e);
        }
        return total;
    }

    /**
     * 获取附近的包装箱信息
     * @param precision
     * @param lat
     * @param lon
     * @return
     */
    @Override
    public List<BoxInfo> queryNearbyBoxes(Double precision,
                                          Double lat,
                                          Double lon,
                                          List<Byte> status,
                                          Integer curPage,
                                          Integer pageSize,
                                          String entCode) {
        List<BoxInfo> boxInfos = null;
        try {
            Integer startPage = (curPage - 1) * pageSize;
            Integer endPage = curPage * pageSize;
            boxInfos = boxInfoDao.queryNearbyBoxes(precision, lat, lon, status, startPage, endPage, entCode);
        } catch (Exception e) {
            logger.error("查询附近的包装箱信息出现异常", e);
        }
        if (boxInfos == null) {
            boxInfos = Lists.newArrayList();
        }
        return boxInfos;
    }

    /**
     * 删除包装箱绑定信息
     * @param rfid
     * @return
     */
    @Override
    public boolean deleteBox(final String rfid, final String entCode) {
        return transactionTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                boolean res = false;
                try {
                    FilterExample fe = new FilterExample();
                    fe.createCriteria().andFieldEqualTo("rfid", rfid);

//                    remove(fe);
                    boxInfoDao.deleteBox(rfid, entCode);

                    boxBaseService.remove(fe);
                    res = true;
                } catch (Exception e) {
                    logger.error(String.format("通过rfid(%s)删除包装箱信息出现异常", rfid));
                }
                return res;
            }
        });
    }

    /**
     * 移除企业包装箱信息
     * @param rfid
     * @param entCode
     * @return
     */
    @Override
    public boolean removeBoxInfo(String rfid, String entCode) {
        boolean res = false;
        try {
            boxInfoDao.deleteBox(rfid, entCode);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("移除企业(%s)包装箱(%s)出现异常", entCode, rfid));
        }
        return res;
    }

    /**
     * 更新包装箱状态
     * @param boxInfo
     * @param entCode
     * @return
     */
    @Override
    public boolean updateBoxInfoByRfid(BoxInfo boxInfo, String entCode) {
        boolean res = false;
        String rfid = "";
        try {
            rfid = boxInfo.getRfid();
            boxInfoDao.updateBoxByRfid(boxInfo, entCode);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("通过rfid(%s)更新包装箱信息出现异常", rfid), e);
        }
        return res;
    }

    /**
     * 保存企业包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    @Override
    public boolean saveBoxInfo(BoxInfo boxInfo, String entCode) {
        boolean res = false;
        String rfid = "";
        try {
            boxInfoDao.saveBox(boxInfo, entCode);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("保存包装箱(%s)信息", rfid), e);
        }
        return res;
    }

    /**
     * 通过订单ID更新包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    @Override
    public boolean updateBoxInfoByOrderId(BoxInfo boxInfo, String entCode) {
        boolean res = false;
        String orderId = "";
        try {
            orderId = boxInfo.getOrderId();
            boxInfoDao.updateBoxInfoByOrderId(boxInfo, entCode);
            res = true;
        } catch (Exception e) {
            logger.error(String.format("通过订单ID(%s)更新包装箱信息", orderId), e);
        }
        return res;
    }

    /**
     * 获取订单需要的包装箱
     * @param orderId
     * @return
     */
    @Override
    public List<BoxTypeInfo> getBoxTypes(String orderId) {
        List<BoxTypeInfo> boxTypeInfos = null;
        try {
            boxTypeInfos = boxTypeDao.getBoxTypes(orderId);
        } catch (Exception e) {
            logger.error(String.format("获取订单(%s)需要包装箱信息出现异常", orderId));
        }
        if (boxTypeInfos == null) {
            boxTypeInfos = Lists.newArrayList();
        }
        return boxTypeInfos;
    }

    /**
     * BoxInfo --> BoxInfoVO
     * @param boxInfo
     * @return
     */
    private BoxInfoVO toBoxInfoVO(BoxInfo boxInfo) {
        BoxInfoVO boxInfoVO = new BoxInfoVO();
        boxInfoVO.setRfid(boxInfo.getRfid());
        boxInfoVO.setStatus(boxInfo.getStatus());
        boxInfoVO.setStatusDescr(BoxStatus.getDesc(boxInfo.getStatus()));
        boxInfoVO.setBoxType(boxInfo.getBoxType());
        boxInfoVO.setCreateTime(boxInfo.getCreateTime());
        boxInfoVO.setBtMac(boxInfo.getBtMac());
        boxInfoVO.setUniqueId(boxInfo.getUniqueId());
        boxInfoVO.setOperator(boxInfo.getOperator());
        boxInfoVO.setOperatorId(boxInfo.getOperatorId());
        boxInfoVO.setOperateTime(boxInfo.getOperateTime());
        boxInfoVO.setCycleIndex(boxInfo.getCycleIndex());
        boxInfoVO.setOrderId(boxInfo.getOrderId());
        boxInfoVO.setCustomId(boxInfo.getCustomId());
        boxInfoVO.setCustomName(boxInfo.getCustomName());
        return boxInfoVO;
    }

}
