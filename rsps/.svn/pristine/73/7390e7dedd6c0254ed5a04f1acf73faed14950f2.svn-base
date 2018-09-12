package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.BoxTypeInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxTypeDao {

    /**
     * 保存订单需求包装箱信息
     * @param orderId
     * @param boxTypeId
     * @param color
     * @param count
     * @return
     */
    boolean saveBoxType(@Param("orderId") String orderId,
                        @Param("boxTypeId") String boxTypeId,
                        @Param("color") String color,
                        @Param("count") Integer count);

    /**
     * 通过订单ID删除
     * @param orderId
     * @return
     */
    boolean deleteByOrderId(@Param("orderId") String orderId);

    /**
     * 获取订单所需包装
     * @param orderId
     * @return
     */
    List<BoxTypeInfo> getBoxTypes(@Param("orderId") String orderId);


    /**
     * 获取包装箱的心型号
     * @param boxId
     * @return
     */
    BoxTypeInfo getBoxType(@Param("boxId") String boxId);

}
