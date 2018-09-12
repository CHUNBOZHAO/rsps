package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.vo.app.*;

public interface AppRestService {
    AppResOperator handleLogin(AppReqLoginUser loginUser);

    String updatePwd(AppReqLoginUser loginUser);

    String registerOperator(AppReqOpRegister appReqOpRegister);

    String queryBoxes(AppReqBoxes appReqBoxes);

    String pushBoxStatus(AppReqPushBoxInfo appReqPushBoxInfo);

//    String pushBoxLocation(AppReqPushBoxLocation appReqPushBoxLocation);

    String registerBox(AppReqRegister reqRegister);

    String queryBoxByRfid(String rfid);

    String queryBoxByBluetoothId(String uuid);

    boolean checkUserSessionId(AppReqBase appReqBase);

    String queryNearbyBoxes(AppReqQueryBoxLocation appReqQueryBoxLocation);

    String queryAppVersion(AppReqQueryVersion appReqQueryVersion, String versionDesc);

    String queryTakeOrders(AppReqTakeOrder appReqTakeOrder);

    String pushOperatorLocation(AppReqPushOpLocation appReqPushOpLocation);

    String queryLines(AppReqLines appReqLines);

    String settingLine(AppReqLineSetting appReqLineSetting);

    String queryLineBoxes(AppReqBoxes appReqBoxes);
}
