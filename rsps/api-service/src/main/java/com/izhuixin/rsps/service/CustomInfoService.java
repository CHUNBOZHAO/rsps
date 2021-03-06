package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.domain.automatic.CustomInfoDO;

public interface CustomInfoService extends CrudService<CustomInfoDO> {
    boolean checkTel(String tel);

    boolean saveCustomInfo(String tel, String password);

    CustomInfoDO getCustomInfo(String customId);

    boolean modifyPwd(Long id, String newPassword);

    boolean checkPwd(Long id, String oldPassword);

    String getCustomInfoByQcode(String qCode);
}
