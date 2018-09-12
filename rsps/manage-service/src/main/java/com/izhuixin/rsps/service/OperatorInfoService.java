package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.dao.OperatorInfo;
import com.izhuixin.rsps.common.vo.web.OperatorInfoVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("api-service")
@RequestMapping("v1/operatorData/")
public interface OperatorInfoService {

    @RequestMapping("/userStatus/check/{userName}/{entCode}")
    boolean checkUserStatus(@PathVariable("userName") String userName,
                            @PathVariable("entCode") String entCode);

    @RequestMapping("/login/handle")
    boolean handleLogin(@RequestParam("userName") String userName,
                        @RequestParam("userPwd") String userPwd,
                        @RequestParam("entCode") String entCode);

    @RequestMapping("/password/update")
    boolean updatePwd(@RequestParam("userName") String userName,
                      @RequestParam("newUserPwd") String newUserPwd,
                      @RequestParam("entCode") String entCode);

    @RequestMapping("/operatorInfo/name/operatorType/get")
    OperatorInfo getOperatorInfoByName(@RequestParam("name") String name,
                                       @RequestParam("operatorType") Byte operatorType,
                                       @RequestParam("userCode") String userCode);

    @RequestMapping("/operatorInfo/name/get")
    OperatorInfo getOperatorInfoByName(@RequestParam("name") String name,
                                       @RequestParam("userCode") String userCode);

    @RequestMapping("/operatorInfo/userName/get")
    OperatorInfo getOperatorInfoByUserName(@RequestParam("userName") String userName,
                                           @RequestParam("entCode") String entCode);

    @RequestMapping("/operatorInfos/get/{entCode}")
    List<OperatorInfo> getOperatorInfos(@PathVariable("entCode") String entCode);

    @RequestMapping("/operators/userId/{userId}/{entCode}")
    List<OperatorInfoVO> getOperatorsByUserId(@PathVariable("userId") String userId,
                                              @PathVariable("entCode") String entCode);

    @RequestMapping("/operatorInfoVO/save")
    boolean saveInfo(@RequestBody OperatorInfoVO operatorInfoVO,
                     @RequestParam("entCode") String entCode);

    @RequestMapping("/info/id/{id}/{entCode}")
    OperatorInfoVO getInfoById(@PathVariable("id") Long id,
                               @PathVariable("entCode") String entCode);

    @RequestMapping("/password/check")
    boolean checkPwd(@RequestParam("id") Integer id,
                     @RequestParam("oldPwd") String oldPwd,
                     @RequestParam("entCode") String entCode);

    @RequestMapping("/password/update/id")
    boolean updatePwd(@RequestParam("id") Integer id,
                      @RequestParam("newPwd") String newPwd,
                      @RequestParam("entCode") String entCode);

    @RequestMapping("/user/delete/{id}/{entCode}")
    boolean deleteUser(@PathVariable("id") Integer id,
                       @PathVariable("entCode") String entCode);

    @RequestMapping("/password/reset/{id}/{entCode}")
    boolean resetPwd(@PathVariable("id") Integer id,
                     @PathVariable("entCode") String entCode);

    @RequestMapping("/userName/generate")
    String generateUserName(@RequestParam("name") String name,
                            @RequestParam("operatorId") String operatorId);

    @RequestMapping("/appSession/valid/check")
    boolean checkValidAppSession(@RequestParam("userId") String userId,
                                 @RequestParam("appSessionId") String appSessionId,
                                 @RequestParam("entCode") String entCode);

    @RequestMapping("/operator/count/{operatorId}/{entCode}")
    Integer countOperator(@PathVariable("operatorId") String operatorId,
                          @PathVariable("entCode") String entCode);

    @RequestMapping("/operator/save")
    boolean saveOperator(@RequestBody OperatorInfo operatorInfo,
                         @RequestParam("entCode") String entCode);

    @RequestMapping("/operator/update/operatorId")
    boolean updateOperatorByOperatorId(@RequestBody OperatorInfo operatorInfo,
                                       @RequestParam("entCode") String entCode);

    @RequestMapping("/operatorInfo/get/customId/{customId}/{entCode}")
    OperatorInfo getOperatorInfoByCustomId(@RequestBody String customId,
                                           @PathVariable("entCode") String entCode);

    /**
     * 检测用户编号是否重名
     */
    @RequestMapping("/operatorInfo/checkOperatorNo")
    boolean checkOperatorNo(@RequestParam("operatorNo") String operatorNo,@RequestParam("entCode") String entCode,@RequestParam("id") Integer id);
}
