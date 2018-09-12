package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.OrderInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {

    /**
     * 通过订单ID获取订单信息
     * @param orderId
     * @return
     */
    OrderInfo getOrderById(@Param("orderId") String orderId, @Param("entCode") String entCode);

    /**
     * 获取订单记录数
     * @param orderId
     * @param entCode
     * @return
     */
    Integer countOrderById(@Param("orderId") String orderId, @Param("entCode") String entCode);

    /**
     * 保存订单
     * @param orderInfo
     * @return
     */
    Integer saveOrder(@Param("orderInfo") OrderInfo orderInfo, @Param("entCode") String entCode);

    /**
     * 更新订单
     * @param orderInfo
     * @param orderId
     * @param entCode
     * @return
     */
    Integer updateOrder(@Param("orderInfo") OrderInfo orderInfo,
                        @Param("orderId") String orderId,
                        @Param("entCode") String entCode);

    /**
     * 获取C端用户当前订单包装箱信息
     * @param tel
     * @param entCodes
     * @return
     */
    List<OrderInfo> getCustomerCurrentBoxes(@Param("customerId") String customerId,
                                            @Param("tel") String tel,
                                            @Param("entCodes") List<String> entCodes,
                                            @Param("orderId") String orderId);


    /**
     * 获取操作人员揽货订单
     * @param operatorId
     * @return
     */
    List<OrderInfo> getTakeOrders(@Param("operatorId") String operatorId);


    /**
     * 获取C端订单信息
     * @param orderId
     * @return
     */
    OrderInfo getOrder(@Param("orderId") String orderId);


    /**
     * 通过订单集合获取订单信息
     * @param orderId
     * @return
     */
    List<OrderInfo> getOrdersByIds(@Param("orderIds") List<String> orderId, @Param("entCode") String entCode);

}
