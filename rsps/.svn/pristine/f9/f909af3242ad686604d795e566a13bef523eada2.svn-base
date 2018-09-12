package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.OrderDriverInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDriverDao {

    /**
     * 通过司机ID、订单ID获取配送信息
     * @param driverId
     * @param orderId
     * @param entCode
     * @return
     */
    OrderDriverInfo getInfoByDriverAndOrderId(@Param("driverId") String driverId,
                                              @Param("orderId") String orderId,
                                              @Param("entCode") String entCode);

    /**
     * 保存订单配送信息
     * @param orderDriverInfo
     * @param entCode
     * @return
     */
    Integer saveOrderDriverInfo(@Param("orderDriverInfo") OrderDriverInfo orderDriverInfo,
                                @Param("entCode") String entCode);

    /**
     * 更新订单配送信息
     * @param orderDriverInfo
     * @param entCode
     * @return
     */
    Integer updateByOrderIdAndDriverId(@Param("orderDriverInfo") OrderDriverInfo orderDriverInfo,
                                       @Param("entCode") String entCode);

    /**
     * 获取订单配送信息
     * @param orderId
     * @param driverId
     * @param entCode
     * @return
     */
    Integer countOrderDriverInfo(@Param("orderId") String orderId,
                                 @Param("driverId") String driverId,
                                 @Param("entCode") String entCode);



}
