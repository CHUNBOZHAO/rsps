package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.common.BoxTypeVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "api-service")
@RequestMapping("v1/boxData/boxType")
public interface BoxTypeService {

    @RequestMapping("/list/get")
    List<BoxTypeVO> getBoxTypes();

    @RequestMapping("/get/{boxId}")
    BoxTypeVO getBoxType(@PathVariable("boxId") String boxId);
}
