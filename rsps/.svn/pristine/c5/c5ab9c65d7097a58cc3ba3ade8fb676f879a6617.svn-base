package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.app.AppReqBoxes;
import com.izhuixin.rsps.common.vo.app.AppResOrders;
import com.izhuixin.rsps.common.vo.web.BoxesCustomInfoVO;
import com.izhuixin.rsps.service.BoxInfoService;
import com.izhuixin.rsps.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/")
public class RestApiController {

    @Autowired
    private RestService restService;

    @Autowired
    private BoxInfoService boxInfoService;
    /**
     * 获取包装箱信息
     * @return
     */
    @RequestMapping(value = "boxData/box/list1/{entCode}", method = RequestMethod.POST)
    @ResponseBody
    public AppResOrders requestBoxes(@RequestBody AppReqBoxes appReqBoxes, @PathVariable("entCode") String entCode) {
        appReqBoxes.setEntCode(entCode);
        return restService.requestBoxes(appReqBoxes);
    }

    @RequestMapping(value = "boxData/box/customs/{entCode}", method = RequestMethod.GET)
    @ResponseBody
    public List<BoxesCustomInfoVO> queryCustomInfos(@RequestParam List<Byte> listStatus, @PathVariable("entCode") String entCode) {

        return boxInfoService.queryCustomInfos(listStatus, entCode.concat("_"));
    }
}
