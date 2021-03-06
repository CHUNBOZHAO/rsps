package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.BoxLocationRecordInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoxLocationRecordDao {

    /**
     * 通过包装rfid、订单ID获取包装箱位置信息
     * @param rfid
     * @param orderId
     * @param entCode
     * @return
     */
    List<BoxLocationRecordInfo> getBoxLocationRecords(@Param("rfid") String rfid,
                                                      @Param("orderId") String orderId,
                                                      @Param("signDate") String signDate,
                                                      @Param("entCode") String entCode);


    /**
     * 保存包装箱位置信息
     * @param boxLocationRecordInfo
     * @param entCode
     * @return
     */
    Integer saveBoxLocationRecord(@Param("boxLocationRecord") BoxLocationRecordInfo boxLocationRecordInfo,
                                  @Param("entCode") String entCode);
}
