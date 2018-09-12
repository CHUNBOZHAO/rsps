package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dba.CrudService;
import com.izhuixin.rsps.common.vo.web.OperatorInfoVO;
import com.izhuixin.rsps.domain.automatic.OperatorInfoDO;
import com.izhuixin.rsps.domain.manual.OperatorInfo;

import java.util.List;

public interface OperatorInfoService extends CrudService<OperatorInfoDO> {

    boolean checkUserStatus(String userName, String entCode);

    boolean handleLogin(String userName, String userPwd, String entCode);

    boolean checkLogged(String userName, String operatorId, String imei, String entCode);

    boolean updatePwd(String userName, String newUserPwd, String entCode);

    OperatorInfo getOperatorInfoByName(String userName, Byte operatorType, String userCode);

    OperatorInfo getOperatorInfoByName(String userName, String userCode);

    OperatorInfo getOperatorInfoByUserName(String userName, String entCode);

    List<OperatorInfo> getOperatorInfos(String entCode);

    List<OperatorInfoVO> getOperatorsByUserId(String entCode, String userId);

    boolean saveInfo(OperatorInfoVO operatorInfoVO, String entCode);

    OperatorInfoVO getInfoById(Long id, String entCode);

    boolean checkPwd(Integer id, String oldPwd, String entCode);

    boolean updatePwd(Integer id, String newPwd, String entCode);

    boolean deleteUser(Integer id, String entCode);

    boolean resetPwd(Integer id, String entCode);

    String generateUserName(String name, String operatorId);

    boolean checkValidAppSession(String userId, String appSessionId, String entCode);

    Integer countOperator(String operatorId, String entCode);

    boolean saveOperator(OperatorInfo operatorInfo, String entCode);

    boolean updateOperatorByOperatorId(OperatorInfo operatorInfo, String entCode);

    OperatorInfo getOperatorInfoByCustomId(String customId, String entCode);
    /**
     * 检测用户编号是否重名
     */
    boolean checkOperatorNo(String operatorNo,String entCode,Integer id);
}
