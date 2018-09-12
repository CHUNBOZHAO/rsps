package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.vo.web.BoxBaseInfoVO;
import com.izhuixin.rsps.common.vo.web.DataTableReqDataVO;
import com.izhuixin.rsps.common.vo.web.DataTableResDataVO;
import com.izhuixin.rsps.domain.manual.BoxBaseInfo;
import com.izhuixin.rsps.service.BoxBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1/boxData/base")
public class BoxBaseController {

    @Autowired
    private BoxBaseService boxBaseService;

    /**
     * 获取包装箱ID
     * @param boxId
     * @return
     */
    @RequestMapping("/boxId/get/{boxId}")
    public String getBoxId(@PathVariable("boxId") String boxId) {
        return boxBaseService.getBoxId(boxId);
    }

    /**
     * 通过蓝牙Mac获取包装箱ID
     * @param bleMac
     * @return
     */
    @RequestMapping("/boxId/ble/get/{bleMac}")
    public String getBoxIdByBle(@PathVariable("bleMac") String bleMac) {
        return boxBaseService.getBoxIdByBle(bleMac);
    }

    /**
     * 通过BoxId获取包装箱tid
     * @param boxId
     * @return
     */
    @RequestMapping("/tid/boxId/{boxId}")
    public String getTidFromBoxId(@PathVariable("boxId") String boxId) {
        return boxBaseService.getTidFromBoxId(boxId);
    }

    /**
     * 获取rfid -- tid的Map集合
     * @param entCode
     * @return
     */
    @RequestMapping("/boxIdMap/get/{entCode}")
    public Map<String, String> getBoxIdMap(String entCode) {
        return boxBaseService.getBoxIdMap(entCode);
    }

    /**
     * 获取所有包装箱
     */
    @RequestMapping("/boxBase/list")
    List<BoxBaseInfoVO> getAllBoxBaseInfoVO(){

        return boxBaseService.getAllBoxBase();
    }
    /**
     * 通过id获取包装箱
     */
    @RequestMapping("/boxBase/getById")
    BoxBaseInfoVO getBoxBaseById(Integer id){
        return boxBaseService.getBoxBaseById(id);
    }
    /**
     * 保存包装箱
     */
    @RequestMapping("/boxBase/save")
    boolean saveBoxBase(@RequestBody BoxBaseInfoVO boxBaseInfoVO){
        return boxBaseService.saveBox(boxBaseInfoVO);
    }

    /**
     * rfid校验
     */
    @RequestMapping("/boxBase/checkRfid")
    boolean checkRfid(String rfid,Integer id){
        return boxBaseService.checkRfid(rfid,id);
    }
    /**
     * 删除包装箱
     */
    @RequestMapping("/boxBase/delete")
    boolean deleteBox(String id){
        return boxBaseService.deleteBox(id);
    }

    /**
     * 分页查询
     */
    @RequestMapping("/boxBase/list/page")
    public DataTableResDataVO getboxBaseInfoVOPage(@RequestBody DataTableReqDataVO dataTableReqDataVO){
        return boxBaseService.getBoxBaseInfoByPage(dataTableReqDataVO);
    }
    /**
     * 通过rfid获取epcId
     */
    @RequestMapping("/boxBase/getEpc")
    public String getEpc(String rfid){

        return boxBaseService.getEpcId(rfid);
    }

}
