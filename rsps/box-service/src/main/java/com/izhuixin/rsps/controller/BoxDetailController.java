package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.domain.BoxDetailInfo;
import com.izhuixin.rsps.service.BoxDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/boxData/boxDetail")
public class BoxDetailController {

    @Autowired
    private BoxDetailService boxDetailService;
    /**
     * 保存boxDetail
     */
    @RequestMapping(value = "/saveBoxDetail",method = RequestMethod.POST)
    public boolean saveBoxDetail(@RequestBody BoxDetailInfo boxDetailInfo){

        return boxDetailService.saveBoxDetail(boxDetailInfo);
    }

    /**
     * 获取boxDetail
     */
    @RequestMapping(value = "/getBoxDetailByBoxId",method = RequestMethod.POST)
    public BoxDetailInfo getBoxDetail(@RequestParam("boxId") String boxId){

        return boxDetailService.getBoxDetailInfoByRfid(boxId);
    }
}
