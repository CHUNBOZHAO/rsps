package com.izhuixin.rsps.dao;

import com.izhuixin.rsps.domain.BoxEfficiencyInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 包装效率统计
 */
@Repository
public interface BoxEfficiencyDao {


    /**
     * 获取报表数据
     * @param type
     * @param entId
     * @param beginTime
     * @param endTime
     * @return
     */
    List<BoxEfficiencyInfo> getEfficiencyInfo(@Param("type") byte type,
                                              @Param("entId") String entId,
                                              @Param("beginTime") Date beginTime,
                                              @Param("endTime") Date endTime);

    /**
     * 包装包装箱效率
     * @param boxEfficiencyInfo
     * @return
     */
    Integer saveInfo(@Param("boxEfficiencyInfo") BoxEfficiencyInfo boxEfficiencyInfo);

}
