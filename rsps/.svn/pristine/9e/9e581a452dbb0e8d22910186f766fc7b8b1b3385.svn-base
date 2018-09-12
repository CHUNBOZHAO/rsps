package com.izhuixin.rsps.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.constant.ErrorCode;
import com.izhuixin.rsps.common.constant.OrderType;
import com.izhuixin.rsps.common.vo.app.*;
import com.izhuixin.rsps.common.vo.wms.BoxBindingInfo;
import com.izhuixin.rsps.service.AppRestService;
import com.izhuixin.rsps.service.BoxBindService;
import com.izhuixin.rsps.service.EnterpriseService;
import com.izhuixin.rsps.service.OrderPolicyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/")
public class RestAppController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AppRestService appRestService;

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private BoxBindService boxBindService;

    @Autowired
    private OrderPolicyService orderPolicyService;

    /**
     * 用户登录
     * @return
    */
    @RequestMapping("userdata/user/login")
    public String handleLogin(@RequestBody AppReqLoginUser appLoginUser) {
        String entCode = enterpriseService.checkUserName(appLoginUser.getUserName());
        if (StringUtils.isNotBlank(entCode)) {
            appLoginUser.setEntCode(entCode.concat("_"));
            return new Gson().toJson(appRestService.handleLogin(appLoginUser));
        } else {
            AppAckContent ackContent = new AppAckContent();
            ackContent.setStatus(ErrorCode.USER_NAME_NOT_EXIST.getIndex().toString());
            ackContent.setMessage(ErrorCode.USER_NAME_NOT_EXIST.getDescr());
            return new Gson().toJson(ackContent);
        }
    }

    /**
     * 用户密码修改
     * @param appLoginUser
     * @return
     */
    @RequestMapping("userData/user/updatePwd/{entCode}")
    public String updatePwd(@RequestBody AppReqLoginUser appLoginUser, @PathVariable("entCode") String entCode) {
        appLoginUser.setEntCode(entCode.concat("_"));
        return appRestService.updatePwd(appLoginUser);
    }

    /**
     * 用户密码修改 -- 兼容金华英特，更新app后废弃
     * @param appLoginUser
     * @return
     */
    @RequestMapping("userData/user/updatePwd")
    public String updatePwd(@RequestBody AppReqLoginUser appLoginUser) {
        appLoginUser.setEntCode("jhyt".concat("_"));
        return appRestService.updatePwd(appLoginUser);
    }

    /**
     * 推送操作人员信息
     * @param appReqPushOpLocation
     * @return
     */
    @RequestMapping("userData/user/pushLocation/{entCode}")
    public String pushUserLocation(@RequestBody AppReqPushOpLocation appReqPushOpLocation, @PathVariable("entCode") String entCode) {
        appReqPushOpLocation.setEntCode(entCode.concat("_"));
        return appRestService.pushOperatorLocation(appReqPushOpLocation);
    }

    /***
     * 设备登记到数据库
     * @return
     */
    @RequestMapping("boxData/box/register/{entCode}")
    public String registerBox(@RequestBody AppReqRegister appReqRegister, @PathVariable("entCode") String entCode) {
        appReqRegister.setEntCode(entCode.concat("_"));
        return appRestService.registerBox(appReqRegister);
    }

    /**
     * 获取包装箱信息
     * @return
     */
    @RequestMapping("boxData/box/list/{entCode}")
    public String getBoxes(@RequestBody AppReqBoxes appReqBoxes, @PathVariable("entCode") String entCode) {
        appReqBoxes.setEntCode(entCode.concat("_"));
        return appRestService.queryBoxes(appReqBoxes);
    }

    /**
     * 获取包装箱信息
     * @return
     */
    @RequestMapping("boxData/box/list")
    public String getBoxes(@RequestBody AppReqBoxes appReqBoxes) {
        appReqBoxes.setEntCode("jhyt".concat("_"));
        return appRestService.queryBoxes(appReqBoxes);
    }

    @RequestMapping("boxData/line/box/list/{entCode}")
    public String getLineBoxes(@RequestBody AppReqBoxes appReqBoxes, @PathVariable("entCode") String entCode) {
        appReqBoxes.setEntCode(entCode.concat("_"));
        return appRestService.queryLineBoxes(appReqBoxes);
    }

    /***
     * 推送包装箱状态信息
     * @return
     */
    @RequestMapping("boxData/box/update/{entCode}")
    public String updateBox(@RequestBody AppReqPushBoxInfo appReqPushBoxInfo, @PathVariable("entCode") String entCode) {
        appReqPushBoxInfo.setEntCode(entCode.concat("_"));
        return appRestService.pushBoxStatus(appReqPushBoxInfo);
    }

    /***
     * 推送包装箱状态信息
     * @return
     */
    @RequestMapping("boxData/box/update")
    public String updateBox(@RequestBody AppReqPushBoxInfo appReqPushBoxInfo) {
        appReqPushBoxInfo.setEntCode("jhyt".concat("_"));
        return appRestService.pushBoxStatus(appReqPushBoxInfo);
    }

    /***
     * 通过条件查询包装箱信息
     * @param appReqQueryBox
     * @return
     */
    @RequestMapping("boxData/box/queryBox/{entCode}")
    public String queryBoxes(@RequestBody AppReqQueryBox appReqQueryBox, @PathVariable("entCode") String entCode) {
        appReqQueryBox.setEntCode(entCode.concat("_"));
        if (StringUtils.isNotBlank(appReqQueryBox.getRfid()) && !StringUtils.isNotBlank(appReqQueryBox.getUuid())) {
            return appRestService.queryBoxByRfid(appReqQueryBox.getRfid());
        } else if (!StringUtils.isNotBlank(appReqQueryBox.getRfid()) && StringUtils.isNotBlank(appReqQueryBox.getUuid())) {
            return appRestService.queryBoxByBluetoothId(appReqQueryBox.getUuid());
        } else {
            return appRestService.queryBoxByRfid(appReqQueryBox.getRfid());
        }
    }

    /***
     * 通过条件查询包装箱信息
     * @param appReqQueryBox
     * @return
     */
    @RequestMapping("boxData/box/queryBox")
    public String queryBoxes(@RequestBody AppReqQueryBox appReqQueryBox) {
        appReqQueryBox.setEntCode("jhyt".concat("_"));
        if (StringUtils.isNotBlank(appReqQueryBox.getRfid()) && !StringUtils.isNotBlank(appReqQueryBox.getUuid())) {
            return appRestService.queryBoxByRfid(appReqQueryBox.getRfid());
        } else if (!StringUtils.isNotBlank(appReqQueryBox.getRfid()) && StringUtils.isNotBlank(appReqQueryBox.getUuid())) {
            return appRestService.queryBoxByBluetoothId(appReqQueryBox.getUuid());
        } else {
            return appRestService.queryBoxByRfid(appReqQueryBox.getRfid());
        }
    }

    /***
     * 查询包装箱位置信息
     * @param appReqQueryBoxLocation
     * @return
     */
    @RequestMapping("boxData/box/queryNearbyBox/{entCode}")
    public String queryBoxLocation(@RequestBody AppReqQueryBoxLocation appReqQueryBoxLocation, @PathVariable("entCode") String entCode) {
        appReqQueryBoxLocation.setEntCode(entCode.concat("_"));
        return appRestService.queryNearbyBoxes(appReqQueryBoxLocation);
    }

    /***
     * 查询包装箱位置信息
     * @param appReqQueryBoxLocation
     * @return
     */
    @RequestMapping("boxData/box/queryNearbyBox")
    public String queryBoxLocation(@RequestBody AppReqQueryBoxLocation appReqQueryBoxLocation) {
        appReqQueryBoxLocation.setEntCode("jhyt".concat("_"));
        return appRestService.queryNearbyBoxes(appReqQueryBoxLocation);
    }

    /**
     * C端绑定包装项目
     * @param boxBindingInfo
     * @param entCode
     * @return
     */
    @RequestMapping("boxData/bindBox/{entCode}")
    public String bingBox(@RequestBody BoxBindingInfo boxBindingInfo, @PathVariable("entCode") String entCode) {
        boxBindingInfo.setEntCode(entCode.concat("_"));
        boxBindingInfo.setOrderType(String.valueOf(OrderType.CONSUMER.getIndex()));
        String res = boxBindService.bindBox(boxBindingInfo);
        JsonObject jsonObject = new Gson().fromJson(res, JsonObject.class);
        if (jsonObject.get("returnCode").getAsInt() == ErrorCode.OK.getIndex().intValue()) {
            orderPolicyService.deliveryOrder(boxBindingInfo.getOrderId(), boxBindingInfo.getEntCode(), boxBindingInfo.getBoxId());
        }
        AppAckContent appAckContent = new AppAckContent();
        appAckContent.setStatus(jsonObject.get("returnCode").getAsString());
        appAckContent.setMessage(jsonObject.get("returnMsg").getAsString());
        return new Gson().toJson(appAckContent);
    }

    /**
     * 查询揽货订单
     * @param appReqTakeOrder
     * @param entCode
     * @return
     */
    @RequestMapping("order/takeOrder/query/{entCode}")
    public String queryTakeOrders(@RequestBody AppReqTakeOrder appReqTakeOrder, @PathVariable("entCode") String entCode) {
        appReqTakeOrder.setEntCode(entCode.concat("_"));
        return appRestService.queryTakeOrders(appReqTakeOrder);
    }

    /**
     * 查询揽货订单
     * @param appReqTakeOrder
     * @return
     */
    @RequestMapping("order/takeOrder/query")
    public String queryTakeOrders(@RequestBody AppReqTakeOrder appReqTakeOrder) {
        appReqTakeOrder.setEntCode("jhyt".concat("_"));
        return appRestService.queryTakeOrders(appReqTakeOrder);
    }


    /***
     * 删除包装箱
     * @return
     */
    @RequestMapping("boxData/box/delete")
    public String deleteBox() {
        // todo 删除包装箱
        return "";
    }

    /**
     * 版本查询
     * @param appReqQueryVersion
     * @return
     */
    @RequestMapping("version/query")
    public String queryVersion(@RequestBody AppReqQueryVersion appReqQueryVersion) {
        return appRestService.queryAppVersion(appReqQueryVersion, "AppVersion");
    }

    /**
     * 版本查询
     * @param appReqQueryVersion
     * @return
     */
    @RequestMapping("version/query/test")
    public String queryTestVersion(@RequestBody AppReqQueryVersion appReqQueryVersion) {
        return appRestService.queryAppVersion(appReqQueryVersion, "TestAppVersion");
    }

    /**
     * 线路查询
     * @param appReqLines
     * @return
     */
    @RequestMapping("lineData/query/{entCode}")
    public String queryLines(@RequestBody AppReqLines appReqLines, @PathVariable("entCode") String entCode) {
        appReqLines.setEntCode(entCode.concat("_"));
        return appRestService.queryLines(appReqLines);
    }

    /**
     * 设置线路
     * @param appReqLineSetting
     * @param entCode
     * @return
     */
    @RequestMapping("lineData/setting/{entCode}")
    public String settingLine(@RequestBody AppReqLineSetting appReqLineSetting, @PathVariable("entCode") String entCode) {
        appReqLineSetting.setEntCode(entCode.concat("_"));
        return appRestService.settingLine(appReqLineSetting);
    }

    @RequestMapping("opData/register/{entCode}")
    public String registerOperator(@RequestBody AppReqOpRegister appReqOpRegister, @PathVariable("entCode") String entCode) {
        appReqOpRegister.setEntCode(entCode.concat("_"));
        return appRestService.registerOperator(appReqOpRegister);
    }

//    /**
//     * 生成登录session失效返回信息
//     * @return
//     */
//    private String generateSessionInvalidAckContent() {
//        AppAckContent appAckContent = new AppAckContent();
//        appAckContent.setStatus(ErrorCode.USER_LOGIN_SESSION_INVALID.getIndex().toString());
//        appAckContent.setMessage(ErrorCode.USER_LOGIN_SESSION_INVALID.getDescr());
//        return new Gson().toJson(appAckContent);
//    }


}
