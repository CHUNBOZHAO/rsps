package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.model.AppReqLoginUser;
import com.izhuixin.rsps.model.AppResOperator;
import com.izhuixin.rsps.model.ErrorCode;
import com.izhuixin.rsps.model.OperatorInfo;
import com.izhuixin.rsps.service.AuthService;
import com.izhuixin.rsps.service.feign.AppFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private AppFeignService appFeignService;

    /**
     * operator登录
     * @param loginUser
     * @return
     */
    @Override
    public AppResOperator handleLogin(AppReqLoginUser loginUser) {

        String entCode = loginUser.getEntCode();

        //AppAckContent ackContent = new AppAckContent();
        boolean res = false;
        AppResOperator appResOperator = new AppResOperator();


        res = appFeignService.checkUserStatus(loginUser.getUserName(), entCode);
        if (res) {
            res = appFeignService.handleLogin(loginUser.getUserName(), loginUser.getUserPwd(), entCode);

            if (res) {
                OperatorInfo operatorInfo = appFeignService.getOperatorInfoByUserName(loginUser.getUserName(), entCode);
                if (operatorInfo != null) {

                    appResOperator.setId(operatorInfo.getId());
                    appResOperator.setOperatorId(operatorInfo.getOperatorId());
                    appResOperator.setUserName(operatorInfo.getUserName());
                    appResOperator.setUserType(operatorInfo.getType());
                    appResOperator.setHeadUrl(operatorInfo.getHeadUrl());
                    appResOperator.setAge(operatorInfo.getAge());
                    appResOperator.setSex(operatorInfo.getSex());
                    appResOperator.setStatus(ErrorCode.OK.getIndex().toString());
                    appResOperator.setMessage(ErrorCode.OK.getDescr());
                    appResOperator.setAppSessionId(operatorInfo.getAppSessionId());
                    appResOperator.setEntCode(entCode.replace("_",""));
                    appResOperator.setOperatorNo(operatorInfo.getOperatorNo());


                    // 更新登录设备的IMEI
                    OperatorInfo updateOp = new OperatorInfo();
                    updateOp.setOperatorId(operatorInfo.getOperatorId());
                    updateOp.setAppSessionId(loginUser.getImei());
                    appFeignService.updateOperatorByOperatorId(updateOp, entCode);

                    return appResOperator;
                } else {
                    appResOperator.setStatus(ErrorCode.USER_NAME_NOT_EXIST.getIndex().toString());
                    appResOperator.setMessage(ErrorCode.USER_NAME_NOT_EXIST.getDescr());
                }
            } else {
                appResOperator.setStatus(ErrorCode.USER_NAME_PWD_ERROR.getIndex().toString());
                appResOperator.setMessage(ErrorCode.USER_NAME_PWD_ERROR.getDescr());
            }
        } else {
            appResOperator.setStatus(ErrorCode.USER_NAME_NOT_EXIST.getIndex().toString());
            appResOperator.setMessage(ErrorCode.USER_NAME_NOT_EXIST.getDescr());
        }

        return appResOperator;
    }
}
