package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.BoxInfo;
import com.izhuixin.rsps.domain.manual.BoxTypeInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxOperateDao {

    /**
     * 通过客户联系人获取包装箱信息
     * @param linkerId -- 客户联系人ID
     * @return
     */
    List<BoxInfo> getBoxsByLinker(@Param("linkerId") String linkerId, RowBounds rowBounds);


    /***
     * 通过客户ID获取客户当前订单包装箱信息
     * @param customId
     * @param rowBounds
     * @return
     */
    List<BoxInfo> getCustomCurrentBoxes(@Param("customId") String customId,
                                        @Param("entCodes") List<String> entCodes,
                                        RowBounds rowBounds);

    /**
     * 获取客户当前包装箱数量
     * @param customId
     * @param entCodes
     * @return
     */
    Integer countCustomCurrentBoxes(@Param("customId") String customId,
                                    @Param("entCodes") List<String> entCodes);


    /**
     * 通过客户ID获取客户历史订单包装箱信息
     * @param customId
     * @param rowBounds
     * @return
     */
    List<BoxInfo> getCustomHistoryBoxes(@Param("customId") String customId,
                                        @Param("entCodes") List<String> entCodes,
                                        RowBounds rowBounds);

    Integer countCustomHistoryBoxes(@Param("customId") String customId,
                                    @Param("entCodes") List<String> entCode);

    /***
     * 通过客户ID、订单ID获取客户当前订单包装箱信息
     * @param customId
     * @param orderId
     * @return
     */
    List<BoxInfo> getCustomCurrentBoxesWithOrderId(@Param("customId") String customId,
                                                   @Param("orderId") String orderId,
                                                   @Param("entCodes") List<String> entCodes);

    /**
     * 通过客户ID、订单ID获取客户历史订单包装箱信息
     * @param customId
     * @param orderId
     * @return
     */
    List<BoxInfo> getCustomHistoryBoxesWithOrderId(@Param("customId") String customId,
                                                   @Param("orderId") String orderId,
                                                   @Param("entCodes") List<String> entCodes);


    /**
     * 通过客户ID、订单ID获取C端客户历史订单包装箱信息
     * @param customId
     * @param tel
     * @param entCodes
     * @return
     */
    List<BoxInfo> getCustomerHistoryBoxes(@Param("customId") String customId,
                                          @Param("tel") String tel,
                                          @Param("orderId") String orderId,
                                          @Param("entCodes") List<String> entCodes);


    /**
     * 获取B端用户包装箱
     * @param customId
     * @param entCodes
     * @return
     */
    List<BoxInfo> getMyBoxesForB(@Param("customId") String customId,
                                 @Param("entCodes") List<String> entCodes);

    /**
     * 获取C端用户包装箱
     * @param tel
     * @param entCodes
     * @return
     */
    List<BoxInfo> getMyBoxesForC(@Param("tel") String tel,
                                 @Param("entCodes") List<String> entCodes);

    /**
     * 获取B端用户包装箱数量
     * @param customId
     * @param entCodes
     * @return
     */
    Integer getMyBoxesCountForB(@Param("customId") String customId,
                                @Param("entCodes") List<String> entCodes);

    /**
     * 获取C端用户包装箱数量
     * @param tel
     * @param entCodes
     * @return
     */
    Integer getMyBoxesCountForC(@Param("tel") String tel,
                                @Param("entCodes") List<String> entCodes);


    /**
     * 通过包装ID获取包装箱型号信息
     * @param boxId
     * @return
     */
    BoxTypeInfo getBoxTypeByBoxId(@Param("boxId") String boxId);

}
