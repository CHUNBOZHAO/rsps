package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.common.vo.web.BoxBaseInfoVO;
import com.izhuixin.rsps.common.vo.web.DataTableReqDataVO;
import com.izhuixin.rsps.common.vo.web.DataTableResDataVO;
import com.izhuixin.rsps.domain.automatic.BoxBaseDO;
import com.izhuixin.rsps.domain.manual.BoxBaseInfo;

import java.util.List;
import java.util.Map;

public interface BoxBaseService extends CrudService<BoxBaseDO> {


    String getBoxId(String boxId);

    String getBoxIdByBle(String bleMac);

    String getTidFromBoxId(String boxId);

    Map<String, String> getBoxIdMap(String entCode);

    /**
     * 获取所有包装箱信息
     */
    List<BoxBaseInfoVO> getAllBoxBase();

    /**
     * 保存包装箱信息
     */
    boolean saveBox(BoxBaseInfoVO boxBaseInfoVO);

    /**
     * 检测rfid重复
     */
    boolean checkRfid(String rfid,Integer id);

    /**
     * 删除智能包装箱
     */
    boolean deleteBox(String id);

    /**
     * 通过id获取包装箱
     */
    BoxBaseInfoVO getBoxBaseById(Integer id);

    /**
     * 分页查询
     * @param
     * @param
     * @return
     */
    DataTableResDataVO getBoxBaseInfoByPage(DataTableReqDataVO dataTableReqDataVO);

    /**
     * 通过rfid获取epcId
     */
    String getEpcId(String rfid);

}
