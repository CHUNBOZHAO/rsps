package com.izhuixin.rsps.dao;

import com.izhuixin.rsps.domain.BoxDetailInfo;
public interface BoxDetailDao {

    /**
     * 保存
     */
    boolean saveBoxDetail(BoxDetailInfo boxDetailInfo);

    /**
     * 更新
     */
    boolean updateBoxDetail(BoxDetailInfo boxDetailInfo);

    /**
     * 根据rfid查询boxDetail
     */
    BoxDetailInfo getBoxDetailInfoByBoxId(String boxId);

    /**
     * 检测boxId是否存在
     */
    Integer checkBoxId(String boxId);

}
