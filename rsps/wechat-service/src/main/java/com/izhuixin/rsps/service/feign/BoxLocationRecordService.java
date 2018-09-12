package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.domain.BoxLocationRecordInfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-service")
@RequestMapping("v1/boxData/location")
public interface BoxLocationRecordService {
    @RequestMapping("/record/track")
    String queryBoxTrack(@RequestParam("boxId") String boxId, @RequestParam("orderId") String orderId);

    @RequestMapping("/record/save")
    boolean saveBoxLocation(@RequestBody BoxLocationRecordInfo boxLocationRecordInfo, @RequestParam("entCode") String entCode);
}
