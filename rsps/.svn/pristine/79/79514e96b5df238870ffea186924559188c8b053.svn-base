package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.common.constant.RecycleState;
import com.izhuixin.rsps.common.dba.AbstractCrudService;
import com.izhuixin.rsps.common.dba.FilterExample;
import com.izhuixin.rsps.domain.automatic.RecycleApplyInfo;
import com.izhuixin.rsps.service.RecycleApplyInfoService;
import org.springframework.stereotype.Service;


@Service
public class RecycleApplyInfoServiceImpl extends AbstractCrudService<RecycleApplyInfo> implements RecycleApplyInfoService {

    /**
     * 保存申请信息
     * @param recycleApplyInfo
     * @return
     */
    @Override
    public boolean saveInfo(RecycleApplyInfo recycleApplyInfo) {
        boolean res = false;
        try {
            long resSave = save(recycleApplyInfo);
            if (resSave > 0) {
                res = true;
            } else {
                logger.warn(String.format("保存申请信息数据库出现异常"));
            }
        } catch (Exception e) {
            logger.error(String.format("保存申请信息出现异常"), e);
        }
        return res;
    }

    /**
     * 获取回收申请数量
     * @param userId
     * @param boxId
     * @return
     */
    @Override
    public Integer countInfo(String userId, String boxId) {
        Long count = 0l;
        try {
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("proposer_id", userId).andFieldEqualTo("box_id", boxId).andFieldEqualTo("state", RecycleState.UN_DO.getIndex().byteValue());
            count = count(fe);
        } catch (Exception e) {
            logger.error("获取回收申请信息出现异常");
        }
        return count.intValue();
    }

    /**
     * 更新包装箱申请状态
     * @param boxId
     * @return
     */
    @Override
    public boolean updateState(String boxId, Byte recycleState) {
        boolean res = false;
        try {
            FilterExample fe = new FilterExample();
            fe.createCriteria().andFieldEqualTo("box_id", boxId);
            RecycleApplyInfo recycleApplyInfo = new RecycleApplyInfo();
            recycleApplyInfo.setState(recycleState);
            update(recycleApplyInfo, fe);
            res = true;
        } catch (Exception e) {
            logger.error("更新回收记录出现异常");
        }
        return res;
    }

}
