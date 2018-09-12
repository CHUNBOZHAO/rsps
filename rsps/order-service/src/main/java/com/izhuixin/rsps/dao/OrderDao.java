package com.izhuixin.rsps.dao;

import com.izhuixin.rsps.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDao {

    /**
     * 根据司机编号获取未签收的订单信息
     * @param motorId
     * @return
     */
    List<Order> getAllOrderByMotorId(String motorId);

    /**
     * 更新订单状态(签收状态改为签收)
     */
    boolean updateOrderStatus(Integer orderStatus,String orderId);

    /**
     *保存订单信息
     */
    boolean saveOrder(Order order);

    /**
     * 根据订单编号删除订单信息
     */
    boolean deleteOrderByOrderId(String orderId);
    /**
     * 根据订单id更新拒签数量和拒签原因
     */
    boolean updateRefuseCountAndReason(Order order);

    /**
     * 查询一天签收量
     */
    List<String> selectDaySign();
    /**
     * 查询一天拒收量
     */
    List<String> selectDayRefused();

    /**
     * 判断存入订单是否重复
     */
    Integer checkOrder(String jobNo,String eirNo);

    /**
     * 根据时间删除订单和图片
     */

    /**
     * 更新订单
     */
    boolean updateOrder(Order order);
}
