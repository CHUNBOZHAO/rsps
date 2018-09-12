package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.domain.RecycleApplyInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-service")
@RequestMapping("v1/recycleData/applyInfo")
public interface RecycleApplyInfoService {
    @RequestMapping("/save")
    boolean saveInfo(@RequestBody RecycleApplyInfo recycleApplyInfo);

    @RequestMapping("/count")
    Integer countInfo(@RequestParam(value = "userId") String userId, @RequestParam(value = "boxId") String boxId);

    @RequestMapping("/updateState")
    boolean updateState(@RequestParam(value = "boxId") String boxId, @RequestParam(value = "recycleType") Byte recycleType);
}
