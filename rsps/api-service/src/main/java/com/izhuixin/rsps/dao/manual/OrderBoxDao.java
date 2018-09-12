package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.OrderBoxInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderBoxDao {

    /**
     * 通过订单ID、包装箱RFID获取订单信息
     * @param orderId
     * @param rfid
     * @param entCode
     * @return
     */
    OrderBoxInfo getInfoByOrderIdAndRfid(@Param("orderId") String orderId,
                                         @Param("rfid") String rfid,
                                         @Param("entCode") String entCode);

    /**
     * 保存
     * @param orderBoxInfo
     * @return
     */
    Integer saveInfo(@Param("orderBoxInfo") OrderBoxInfo orderBoxInfo, @Param("entCode") String entCode);


    /**
     * 更新（通过rfid,barcode更新）
     * @param orderBoxInfo
     * @param entCode
     * @return
     */
    Integer updateInfo(@Param("orderBoxInfo") OrderBoxInfo orderBoxInfo, @Param("entCode") String entCode);

    /**
     * 通过rfid,barcode获取数量
     * @param rfid
     * @param barcode
     * @param entCode
     * @return
     */
    Integer countInfoByRfidAndBarcode(@Param("rfid") String rfid,
                                      @Param("barcode") String barcode,
                                      @Param("entCode") String entCode);


}
