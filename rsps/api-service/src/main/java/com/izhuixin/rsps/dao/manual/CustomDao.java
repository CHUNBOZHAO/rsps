package com.izhuixin.rsps.dao.manual;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomDao {

    /**
     * 通过barcode获取用户ID,从BoxInfo表管理查询
     * @param barCode
     * @param entCode
     * @return
     */
    List<String> getCustomIdsByBarcode1(@Param("barCode") String barCode, @Param("entCode") String entCode);

    /**
     * 通过barcode获取用户ID,从OrderBox表管理查询
     * @param barCode
     * @param entCode
     * @return
     */
    List<String> getCustomIdsByBarcode2(@Param("barCode") String barCode, @Param("entCode") String entCode);


}
