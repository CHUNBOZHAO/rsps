package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.common.object.Pair;
import com.izhuixin.rsps.common.page.Paginator;
import com.izhuixin.rsps.common.vo.web.BoxInfoVO;
import com.izhuixin.rsps.common.vo.web.BoxesCustomInfoVO;
import com.izhuixin.rsps.common.vo.web.BoxesOperatorInfoVO;
import com.izhuixin.rsps.domain.automatic.BoxInfoDO;
import com.izhuixin.rsps.domain.manual.BoxInfo;
import com.izhuixin.rsps.domain.manual.BoxTypeInfo;

import java.util.List;
import java.util.Map;

public interface BoxInfoService extends CrudService<BoxInfoDO> {


    BoxInfo getBoxInfoByRfid(String rfid, String entCode);

    List<BoxInfo> getBoxInfoByOrderid(String orderId, String entCode);

    List<BoxInfo> queryBoxes(String operatorId,
                             List<Byte> boxStatus,
                             Long beginTime,
                             Long endTime,
                             Paginator paginator,
                             String entCode);

    List<BoxInfo> queryPendingBoxes(String operatorId,
                                    String entCode,
                                    Paginator paginator);

    List<BoxInfo> queryLineBoxes(String operatorId, String entCode);

    List<BoxInfo> queryTransferBoxes(String operatorId, String entCode);

    Map<String, Pair<String, String>> queryCustomTransferMap(String operatorId, String entCode);

    List<BoxInfo> queryCLineBoxes(String operatorId, String entCode, Paginator paginator);

    Long countTransportingBoxes(String entCode);

    Long countRetentionBoxes(String entCode);

    Long countRecycleBoxes(String entCode);

    Long countLeisureBoxes(String entCode);

    Long countBindingBoxes(String entCode);

    List<BoxInfoVO> queryBoxesByStatus(List<Byte> listStatus, String entCode);

    List<BoxesCustomInfoVO> queryCustomInfos(List<Byte> listStatus, String entCode);

    List<BoxesOperatorInfoVO> queryOperators(List<Byte> listStatus, String entCode);

    List<BoxInfoVO> queryBoxesByCustomId(List<Byte> listStatus, String customId, String entCode);

    List<BoxInfoVO> queryBoxesByOperatorId(List<Byte> listStatus, String operatorId, String entCode);

    List<BoxInfoVO> queryAllBoxes(String entCode);

    List<BoxInfoVO> queryLeisureBoxes(String entCode);

    Long countNearbyBoxes(Double precision, Double lat, Double lon, List<Byte> status, String entCode);

    List<BoxInfo> queryNearbyBoxes(Double precision,
                                   Double lat,
                                   Double lon,
                                   List<Byte> status,
                                   Integer curPage,
                                   Integer pageSize,
                                   String entCode);

    boolean deleteBox(String rfid, String entCode);

    boolean removeBoxInfo(String rfid, String entCode);

    boolean updateBoxInfoByRfid(BoxInfo boxInfo, String entCode);

    boolean saveBoxInfo(BoxInfo boxInfo, String entCode);

    boolean updateBoxInfoByOrderId(BoxInfo boxInfo, String entCode);

    List<BoxTypeInfo> getBoxTypes(String orderId);
}
