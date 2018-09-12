package com.izhuixin.rsps.service.feign;

import com.izhuixin.rsps.model.BoxInfo;
import com.izhuixin.rsps.model.BoxInfoVO;
import com.izhuixin.rsps.model.BoxLocationRecordInfo;
import com.izhuixin.rsps.model.OperatorLocation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "api-service")
public interface FeignService {
    @RequestMapping(value = "v1/enterpriseData/entId/get/entCode/{entCode}",method = RequestMethod.POST)
    String getEntIdByEntCode(@PathVariable(value = "entCode") String entCode);

    @RequestMapping(value = "v1/enterpriseData/entCode/get/boxId/{boxId}",method = RequestMethod.GET)
    String getEntCodeByBoxId(@PathVariable(value = "boxId") String boxId);

    @RequestMapping(value = "v1/boxData/base/boxId/ble/get/{bleMac}",method = RequestMethod.GET)
    String getBoxIdByBle(@PathVariable(value = "bleMac") String bleMac);

    @RequestMapping(value = "v1/operatorData/location/exist/check/{operatorName}/{entId}",method = RequestMethod.POST)
    boolean checkExist(@PathVariable(value = "operatorName") String operatorName, @PathVariable(value = "entId") String entId);

    @RequestMapping(value = "v1/operatorData/location/update",method = RequestMethod.POST)
    boolean updateLocation(@RequestBody OperatorLocation operatorLocation);

    @RequestMapping(value = "v1/operatorData/location/save",method = RequestMethod.POST)
    boolean saveLocation(@RequestBody OperatorLocation operatorLocation);

    @RequestMapping(value = "v1/boxData/boxInfo/boxes/query/operatorId",method = RequestMethod.POST)
    List<BoxInfoVO> queryBoxesByOperatorId(@RequestParam(value = "listStatus") List<Byte> listStatus, @RequestParam(value = "operatorId") String operatorId, @RequestParam(value = "entCode")String entCode);

    @RequestMapping(value = "v1/boxData/boxInfo/update/rfid",method = RequestMethod.POST)
    boolean updateBoxInfoByRfid(@RequestBody BoxInfo boxInfo, @RequestParam(value = "entCode")String entCode);

    @RequestMapping(value = "v1/boxData/location/record/save",method = RequestMethod.POST)
    boolean saveBoxLocation(@RequestBody BoxLocationRecordInfo boxLocationRecordInfo, @RequestParam(value = "entCode") String entCode);
}
