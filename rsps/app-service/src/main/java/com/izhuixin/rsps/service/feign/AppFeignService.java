package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.model.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "api-service")
public interface AppFeignService {

    /**
     * 用户密码修改
     * @param appLoginUser
     * @return
     */
    @RequestMapping(value = "v1/userData/user/updatePwd/{entCode}", method = RequestMethod.POST)
    String updatePwd(@RequestBody AppReqLoginUser appLoginUser, @PathVariable("entCode") String entCode);

    /**
     * 获取包装箱信息
     * @param appReqBoxes
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/boxData/box/list/{entCode}",method = RequestMethod.POST)
    String getBoxes(@RequestBody AppReqBoxes appReqBoxes, @PathVariable("entCode") String entCode);

    /**
     * 获取线路订单
     * @param appReqBoxes
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/boxData/line/box/list/{entCode}",method = RequestMethod.POST)
    String getLineBoxes(@RequestBody AppReqBoxes appReqBoxes, @PathVariable("entCode") String entCode);

    /**
     * 线路查询
     * @param appReqLines
     * @return
     */
    @RequestMapping(value = "v1/lineData/query/{entCode}",method = RequestMethod.POST)
    String queryLines(@RequestBody AppReqLines appReqLines, @PathVariable("entCode") String entCode);

    /**
     * 查询揽货订单
     * @param appReqTakeOrder
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/order/takeOrder/query/{entCode}",method = RequestMethod.POST)
    String queryTakeOrders(@RequestBody AppReqTakeOrder appReqTakeOrder, @PathVariable("entCode") String entCode);

    /**
     * 设置线路
     * @param appReqLineSetting
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/lineData/setting/{entCode}",method = RequestMethod.POST)
    String settingLine(@RequestBody AppReqLineSetting appReqLineSetting, @PathVariable("entCode") String entCode);

    /***
     * 包装箱状态修改
     * @return
     */
    @RequestMapping(value = "v1/boxData/box/update/{entCode}",method = RequestMethod.POST)
    String updateBox(@RequestBody AppReqPushBoxInfo appReqPushBoxInfo, @PathVariable("entCode") String entCode);

    /**
     * 检查app更新
     * @param appReqQueryVersion
     * @return
     */
    @RequestMapping(value = "v1/version/query",method = RequestMethod.POST)
    String queryVersion(@RequestBody AppReqQueryVersion appReqQueryVersion);

    /***
     * 通过条件查询包装箱信息
     * @param appReqQueryBox
     * @return
     */
    @RequestMapping(value = "v1/boxData/box/queryBox/{entCode}",method = RequestMethod.POST)
    String queryBoxes(@RequestBody AppReqQueryBox appReqQueryBox, @PathVariable("entCode") String entCode);

    /**
     * C端绑定包装项目
     * @param boxBindingInfo
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/boxData/bindBox/{entCode}",method = RequestMethod.POST)
    String bindBox(@RequestBody BoxBindingInfo boxBindingInfo, @PathVariable("entCode") String entCode);

    /**
     * 注册
     * @param appReqOpRegister
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/opData/register/{entCode}",method = RequestMethod.POST)
    String registerOperator(@RequestBody AppReqOpRegister appReqOpRegister, @PathVariable("entCode") String entCode);



    /**
     * 检测企业用户名
     * @param userName
     * @return
     */
    @RequestMapping(value = "v1/enterpriseData/userName/check/{userName}",method = RequestMethod.POST)
    String checkUserName(@PathVariable("userName") String userName);


//    /**
//     * 登录
//     * @param appLoginUser
//     * @return
//     */
//    @RequestMapping(value = "v1/userdata/user/login",method = RequestMethod.POST)
//    AppResOperator handleLogin(@RequestBody AppReqLoginUser appLoginUser);


    /**
     * 检查用户是否存在
     * @param userName
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/operatorData/userStatus/check/{userName}/{entCode}",method = RequestMethod.POST)
    boolean checkUserStatus(@PathVariable("userName") String userName, @PathVariable("entCode") String entCode);

    /**
     * 根据用户名密码进行登录
     * @param userName
     * @param userPwd
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/operatorData/login/handle",method = RequestMethod.POST)
    boolean handleLogin(@RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd, @RequestParam("entCode") String entCode);

    /**
     * 通过用户名来获取用户的信息
     * @param userName
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/operatorData/operatorInfo/userName/get",method = RequestMethod.POST)
    OperatorInfo getOperatorInfoByUserName(@RequestParam("userName") String userName, @RequestParam("entCode") String entCode);

    /**
     * 修改操作员信息
     * @param operatorInfo
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/operatorData/operator/update/operatorId", method = RequestMethod.POST)
    boolean updateOperatorByOperatorId(@RequestBody OperatorInfo operatorInfo, @RequestParam("entCode") String entCode);

    /**
     * 检测登录状态
     * @param userName
     * @param operatorId
     * @param imei
     * @param entCode
     * @return
     */
    @RequestMapping(value = "v1/operatorData/login/logged/handle")
    boolean checkLogged(@RequestParam("userName") String userName,
                        @RequestParam("operatorId") String operatorId,
                        @RequestParam("imei") String imei,
                        @RequestParam("entCode") String entCode);
}
