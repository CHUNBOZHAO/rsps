package com.izhuixin.rsps.dao;

import com.izhuixin.rsps.domain.BoxStatusInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxDao {

    /**
     * 获取包装箱状态数量信息
     * @param entCode
     * @return
     */
    BoxStatusInfo getBoxStatusCountInfo(@Param("entCode") String entCode);

    /**
     * 获取包装箱状态数量信息(通过日期)
     * @param entCode
     * @return
     */
    BoxStatusInfo getBoxStatusCountInfoByTime(@Param("entCode") String entCode,@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    /**
     * 遗失包装箱数量
     * @param entCode
     * @return
     */
    int countMissingBox(@Param("entCode") String entCode, @Param("confineTime") String confineTime);


    /**
     * 过期包装箱数量
     * @param entCode
     * @return
     */
    int countOverdueBox(@Param("entCode") String entCode, @Param("confineTime") String confineTime);

}
