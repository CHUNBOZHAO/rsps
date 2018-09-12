package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.common.vo.web.BoxFlowRecordVO;
import com.izhuixin.rsps.domain.automatic.BoxRecordDO;
import com.izhuixin.rsps.domain.manual.BoxRecordInfo;

import java.util.List;

public interface BoxRecordService extends CrudService<BoxRecordDO> {
    boolean saveBoxRecord(BoxRecordInfo boxRecordInfo, String entCode);

    Integer countBoxRecord(String rfid,
                           String orderId,
                           Byte operateType,
                           String entCode);

    boolean updateBoxRecord(BoxRecordInfo boxRecordInfo,
                            String rfid,
                            String orderId,
                            Byte operateType,
                            String entCode);

    List<BoxFlowRecordVO> getBoxFlowInfo(String boxId, String orderId, String entCode);
}
