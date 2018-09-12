package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.vo.web.BoxDetailInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-service")
//从box-sevice中获取数据
//@FeignClient(value = "box-service")
@RequestMapping("v1/boxData/boxDetail")
public interface BoxDetailService {

    /**
     * 保存boxDetail
     */
    @RequestMapping(value = "/saveBoxDetail",method = RequestMethod.POST)
    boolean saveBoxDetail(@RequestBody BoxDetailInfo boxDetailInfo);
    /**
     * 获取boxDetail
     */
    @RequestMapping(value = "/getBoxDetailByBoxId",method = RequestMethod.POST)
    BoxDetailInfo getBoxDetail(@RequestParam("boxId") String boxId);
}
