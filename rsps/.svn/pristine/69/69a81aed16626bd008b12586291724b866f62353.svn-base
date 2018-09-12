package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.domain.automatic.RecycleApplyInfo;

public interface RecycleApplyInfoService extends CrudService<RecycleApplyInfo> {
    boolean saveInfo(RecycleApplyInfo recycleApplyInfo);

    Integer countInfo(String userId, String boxId);

    boolean updateState(String boxId, Byte recycleType);
}
