package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.vo.web.BoxBaseInfoVO;
import com.izhuixin.rsps.common.vo.web.DataTableReqDataVO;
import com.izhuixin.rsps.common.vo.web.DataTableResDataVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "api-service")
@RequestMapping("v1/boxData/base")
public interface BoxBaseService {

    /**
     * 获取所有包装箱
     */
    @RequestMapping("/boxBase/list")
    List<BoxBaseInfoVO> getAllBoxBaseInfoVO();
    /**
     * 通过id获取包装箱
     */
    @RequestMapping("/boxBase/getById")
    BoxBaseInfoVO getBoxBaseById(@RequestParam("id") Integer id);
    /**
     * 保存包装箱
     */
    @RequestMapping("/boxBase/save")
    boolean saveBoxBase(@RequestBody BoxBaseInfoVO boxBaseInfoVO);
    /**
     * rfid校验
     */
    @RequestMapping("/boxBase/checkRfid")
    boolean checkRfid(@RequestParam("rfid") String rfid,@RequestParam("id") Integer id);
    /**
     * 删除包装箱
     */
    @RequestMapping("/boxBase/delete")
    boolean deleteBox(@RequestParam("id") String id);

    /**
     * 分页查询
     */
    @RequestMapping("/boxBase/list/page")
    DataTableResDataVO getboxBaseInfoVOPage(@RequestBody DataTableReqDataVO dataTableReqDataVO);

    /**
     * 通过rfid获取epcId
     */
    @RequestMapping("/boxBase/getEpc")
    String getEpc(@RequestParam("rfid") String rfid);
}
