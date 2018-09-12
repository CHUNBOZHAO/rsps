package com.izhuixin.rsps.dao.manual;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatorLocationDao {

    /**
     * 检测是否在线
     * @param operatorName
     * @param operatorId
     * @param startTime
     * @param endTime
     * @return
     */
    Integer checkOnline(@Param("operatorName") String operatorName,
                        @Param("operatorId") String operatorId,
                        @Param("startTime") String startTime,
                        @Param("endTime") String endTime);

}
