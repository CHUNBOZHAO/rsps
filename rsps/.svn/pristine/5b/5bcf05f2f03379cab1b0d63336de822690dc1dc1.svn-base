package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.domain.automatic.BoxLocationRecordDO;
import com.izhuixin.rsps.domain.manual.BoxLocationRecordInfo;

public interface BoxLocationRecordService extends CrudService<BoxLocationRecordDO> {
    String queryBoxTrack(String boxId, String orderId);

    boolean saveBoxLocation(BoxLocationRecordInfo boxLocationRecordInfo, String entCode);
}
