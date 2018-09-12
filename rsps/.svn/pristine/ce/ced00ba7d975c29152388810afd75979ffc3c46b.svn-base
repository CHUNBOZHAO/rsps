package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.web.BoxTypeVO;
import com.izhuixin.rsps.service.BoxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/boxData/boxType")
public class BoxTypeController {

    @Autowired
    private BoxTypeService boxTypeService;

    /**
     * 获取包装箱型号列表
     * @return
     */
    @RequestMapping("/list/get")
    public List<BoxTypeVO> getBoxTypes() {
        return boxTypeService.getBoxTypes();
    }

    /**
     * 获取指定包装箱型号
     * @param boxId
     * @return
     */
    @RequestMapping("/get/{boxId}")
    public BoxTypeVO getBoxType(@PathVariable String boxId) {
        return boxTypeService.getBoxType(boxId);
    }

}
