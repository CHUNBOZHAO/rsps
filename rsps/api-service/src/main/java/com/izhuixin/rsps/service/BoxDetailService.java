package com.izhuixin.rsps.service;

import com.izhuixin.rsps.domain.manual.BoxDetailInfo;

public interface BoxDetailService {
    /**
     * 保存boxDetail
     */
    boolean saveBoxDetail(BoxDetailInfo boxDetailInfo);


    /**
     * 根据rfid获取boxDetail
     */
    BoxDetailInfo getBoxDetailInfoByRfid(String uuid);
}
