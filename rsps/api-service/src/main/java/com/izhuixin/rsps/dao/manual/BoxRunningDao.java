package com.izhuixin.rsps.dao.manual;

import org.apache.ibatis.annotations.Param;

/**
 * 包装箱运行信息
 */
public interface BoxRunningDao {

    /**
     * 计算包装箱记录
     * @param boxId
     * @return
     */
    Integer countBox(@Param("boxId") String boxId);


    /**
     * 更新包装箱循环次数
     * @param boxId
     * @param count
     * @return
     */
    Integer updateCycleIndex(@Param("boxId") String boxId,
                             @Param("count") String count);

}
