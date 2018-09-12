package com.izhuixin.rsps.service.impl;

import com.google.common.collect.Lists;
import com.izhuixin.rsps.common.dba.BaseAbstractService;import com.izhuixin.rsps.dao.manual.LineTransferDao;
import com.izhuixin.rsps.domain.manual.SysUserInfo;
import com.izhuixin.rsps.service.LineTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 线路中转服务
 */
@Service
public class LineTransferServiceImpl extends BaseAbstractService implements LineTransferService {

    @Autowired
    private LineTransferDao lineTransferDao;


    /**
     * 检测包装箱送达状态
     * @param boxId
     * @param operatorId
     * @return 非空 中转站点ID  空 终点
     */
    @Override
    public String checkDeliveryStatus(String boxId, String operatorId, String entCode) {
        String res = "";
        List<String> ids1 = lineTransferDao.getRelatedLineTransferIds(operatorId, entCode);
        List<String> ids2 = getTransferIdsForBoxId(boxId, entCode);
        for (String item1 : ids1) {
            for (String item2 : ids2) {
                if (item1.equals(item2)) {
                    res = item1;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 获取包装箱上层所有中转节点
     * @param boxId
     * @param entCode
     * @return
     */
    private List<String> getTransferIdsForBoxId(String boxId, String entCode) {
        List<String> transferIds = lineTransferDao.getParentTransferIdsByBoxId(boxId, entCode);
        List<String> ids = Lists.newArrayList();
        for (String transferId : transferIds) {
            ids.add(transferId);
            getParentTransferIds(transferId, ids, entCode);
        }
        return ids;
    }


    /**
     * 获取中转站点所有上层中转节点
     * @param curTransferId
     * @param transferIds
     */
    private void getParentTransferIds(String curTransferId,
                                     List<String> transferIds,
                                     String entCode) {
        List<SysUserInfo> userInfos = lineTransferDao.getParentTransferInfo(curTransferId, entCode);
        for (SysUserInfo userInfo : userInfos) {
            transferIds.add(userInfo.getUserId());
            if (userInfo.getLevel() <= 2) {
                continue;
            } else {
                getParentTransferIds(userInfo.getUserId(), transferIds, entCode);
            }
        }
    }

}
