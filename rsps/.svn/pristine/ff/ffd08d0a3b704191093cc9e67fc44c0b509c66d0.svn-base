package com.izhuixin.rsps.dao.manual;

import com.izhuixin.rsps.domain.manual.LineRelatedInfo;
import com.izhuixin.rsps.domain.manual.SysUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineTransferDao {

//    /**
//     * 获取操作员关联线路配置的中转站
//     * @param operatorId
//     * @param entCode
//     * @return
//     */
//    List<String> getTransferIds(@Param("operatorId") String operatorId,
//                                @Param("entCode") String entCode);


    /**
     * 获取中转站下所有线路的关联信息
     * @param transferId
     * @param entCode
     * @return
     */
    List<LineRelatedInfo> getLineRelatedInfos(@Param("transferId") String transferId,
                                              @Param("entCode") String entCode);


    /**
     * 获取配送员关联线路所在中转站点ID集合
     * @param operatorId
     * @param entCode
     * @return
     */
    List<String> getTransferIdsByOperatorId(@Param("operatorId") String operatorId,
                                            @Param("entCode") String entCode);


    /**
     * 获取配送员关联所有线路，这些线路关联的所有中转站
     * @param operatorId
     * @param entCode
     * @return
     */
    List<String> getRelatedLineTransferIds(@Param("operatorId") String operatorId,
                                           @Param("entCode") String entCode);


    /**
     * 获取中转站点上级站点
     * @param transferId
     * @param entCode
     * @return
     */
    List<SysUserInfo> getParentTransferInfo(@Param("transferId") String transferId,
                                            @Param("entCode") String entCode);


    /**
     * 获取包装箱订单客户所在上级中转站ID
     * @param boxId
     * @param entCode
     * @return
     */
    List<String> getParentTransferIdsByBoxId(@Param("boxId") String boxId,
                                             @Param("entCode") String entCode);

}
