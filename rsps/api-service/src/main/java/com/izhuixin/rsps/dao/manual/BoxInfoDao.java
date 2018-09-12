package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.BoxInfo;
import com.izhuixin.rsps.domain.manual.BoxesCustomInfo;
import com.izhuixin.rsps.domain.manual.BoxesOperatorInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxInfoDao {


    /**
     * 通过条件获取包装箱信息
     * @param operatorId
     * @param boxStatus
     * @param beginTime
     * @param endTime
     * @return
     */
    Integer countBoxesByCondition(@Param("operatorId") String operatorId,
                                  @Param("boxStatus") List<Byte> boxStatus,
                                  @Param("beginTime") Long beginTime,
                                  @Param("endTime") Long endTime,
                                  @Param("entCode") String entCode);

    /**
     * 通过条件获取包装箱信息
     * @param operatorId
     * @param boxStatus
     * @param beginTime
     * @param endTime
     * @return
     */
    List<BoxInfo> queryBoxesByCondition(@Param("operatorId") String operatorId,
                                        @Param("boxStatus") List<Byte> boxStatus,
                                        @Param("beginTime") Long beginTime,
                                        @Param("endTime") Long endTime,
                                        @Param("entCode") String entCode,
                                        RowBounds rowBounds);

    /**
     * 获取配货状态及指定操作的包装箱数量
     * @param operatorId
     * @param entCode
     * @return
     */
    Integer countPendingBoxes(@Param("operatorId") String operatorId,
                              @Param("entCode") String entCode);

    /**
     * 获取配货状态及指定操作的包装箱信息
     * @param operatorId
     * @param entCode
     * @return
     */
    List<BoxInfo> queryPendingBoxes(@Param("operatorId") String operatorId,
                                    @Param("entCode") String entCode,
                                    RowBounds rowBounds);

    /**
     * 获取操作人对应线路的包装箱信息数量
     * @param operatorId
     * @param entCode
     * @return
     */
    Integer countLineBoxes(@Param("operatorId") String operatorId,
                           @Param("entCode") String entCode);

    /**
     * 获取操作人对应线路的包装箱信息
     * @param operatorId
     * @param entCode
     * @return
     */
    List<BoxInfo> queryLineBoxes(@Param("operatorId") String operatorId,
                                 @Param("entCode") String entCode);


    /**
     * 获取C端操作人对应线路的包装箱信息数量
     * @param operatorId
     * @param entCode
     * @return
     */
    Integer countCLineBoxes(@Param("operatorId") String operatorId,
                            @Param("entCode") String entCode);

    /**
     * 获取C端操作人对应线路的包装箱信息
     * @param operatorId
     * @param entCode
     * @return
     */
    List<BoxInfo> queryCLineBoxes(@Param("operatorId") String operatorId,
                                  @Param("entCode") String entCode,
                                  RowBounds rowBounds);

    /**
     * 获取指定状态包装信息数量
     * @param boxStatus
     * @param entCode
     * @return
     */
    Long countBoxesByStatus(@Param("boxStatus") List<Byte> boxStatus,
                            @Param("entCode") String entCode);


    /**
     * 获取指定状态的包装箱信息
     * @return
     */
    List<BoxInfo> queryBoxes(@Param("status") List<Byte> listStatus,
                             @Param("entCode") String entCode);

    /**
     * 通过订单查询包装箱信息
     * @param orderId
     * @param entCode
     * @return
     */
    List<BoxInfo> queryBoxesByOrderId(@Param("orderId") String orderId,
                                      @Param("entCode") String entCode);

    /**
     * 通过rfid获取包装箱信息
     * @param rfid
     * @param entCode
     * @return
     */
    BoxInfo queryBoxByRfid(@Param("rfid") String rfid,
                           @Param("entCode") String entCode);

    /**
     * 获取指定状态下包装包装箱所属客户列表
     * @param listStatus
     * @return
     */
    List<BoxesCustomInfo> queryCustomsWithBox(@Param("status") List<Byte> listStatus,
                                              @Param("entCode") String entCode);

    /**
     * 获取指定状态、指定客户下包装包装箱列表
     * @param listStatus
     * @param customId
     * @return
     */
    List<BoxInfo> queryBoxesByCustomId(@Param("status") List<Byte> listStatus,
                                       @Param("customId") String customId,
                                       @Param("entCode") String entCode);

    /**
     * 获取回收状态下包装箱所属操作人列表
     * @return
     */
    List<BoxesOperatorInfo> queryOperatorsWithBox(@Param("status") List<Byte> listStatus,
                                                  @Param("entCode") String entCode);

    /**
     * 获取回收状态、指定操作人下的包装箱信息
     * @param operatorId
     * @return
     */
    List<BoxInfo> queryBoxesByOperatorId(@Param("status") List<Byte> listStatus,
                                         @Param("operatorId") String operatorId,
                                         @Param("entCode") String entCode);

    /**
     * 获取所有包装箱信息
     * @return
     */
    List<BoxInfo> queryAllBoxes(@Param("entCode") String entCode);

    /***
     * 获取经度偏差内的包装箱总数
     * @param precision
     * @param lat
     * @param lon
     * @return
     */
    Long countNearbyBoxes(@Param("precision") Double precision,
                          @Param("lat") Double lat,
                          @Param("lon") Double lon,
                          @Param("status") List<Byte> listStatus,
                          @Param("entCode") String entCode);

    /**
     * 获取经度偏差内的包装箱
     * @param precision
     * @return
     */
    List<BoxInfo> queryNearbyBoxes(@Param("precision") Double precision,
                                   @Param("lat") Double lat,
                                   @Param("lon") Double lon,
                                   @Param("status") List<Byte> listStatus,
                                   @Param("startPage") Integer startPage,
                                   @Param("endPage") Integer endPage,
                                   @Param("entCode") String entCode);

    /**
     * 获取闲置包装箱基本信息
     * @param entCode
     * @return
     */
    List<BoxInfo> queryLeisureBoxes(@Param("entCode") String entCode);

    /**
     * 删除包装箱
     * @param rfid
     * @param entCode
     * @return
     */
    Integer deleteBox(@Param("rfid") String rfid,
                      @Param("entCode") String entCode);


    /**
     * 通过包装箱rfid、订单id获取包装箱信息
     * @param rfid
     * @param entCode
     * @return
     */
    BoxInfo getBoxInfoByRfidAndOrderId(@Param("rfid") String rfid,
                                       @Param("orderId") String orderId,
                                       @Param("entCode") String entCode);

    /**
     * 获取回收状态下前的包装箱使用的客户名称
     * @param boxId
     * @param entCode
     * @return
     */
    String queryCustomerByBox(@Param("boxId") String boxId,
                              @Param("entCode") String entCode);


    /**
     * 查询绑定订单的包装箱
     * @param orderId
     * @param entCodes
     * @return
     */
    List<BoxInfo> queryAllBoxesByOrderId(@Param("orderId") String orderId,
                                         @Param("entCodes") List<String> entCodes);

    /**
     * 通过rfid更新包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    Integer updateBoxByRfid(@Param("boxInfo") BoxInfo boxInfo,
                            @Param("entCode") String entCode);


    /**
     * 通过订单ID更新包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    Integer updateBoxInfoByOrderId(@Param("boxInfo") BoxInfo boxInfo,
                                   @Param("entCode") String entCode);


    /**
     * 保存包装箱信息
     * @param boxInfo
     * @param entCode
     * @return
     */
    Integer saveBox(@Param("boxInfo") BoxInfo boxInfo,
                    @Param("entCode") String entCode);


    /**
     * 获取指定客户配货状态下的包装箱
     * @param customIds
     * @param entCode
     * @return
     */
    List<BoxInfo> queryBoxesForCustoms(@Param("customIds") List<String> customIds, @Param("entCode") String entCode);

    /**
     * 通过rfid获取包装箱循环次数
     */
    Integer getCycleIndexByRfid(@Param("rfid") String rfid,@Param("entCode") String entCode);
}
