package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.OperatorInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 分配
 */
@Repository
public interface AllotDao {

    /**
     * 通过客户ID查询送货司机
     * @param customId
     * @param entCode
     * @return
     */
    OperatorInfo getOperatorInfoByCustomId(@Param("customId") String customId, @Param("entCode") String entCode);

}
