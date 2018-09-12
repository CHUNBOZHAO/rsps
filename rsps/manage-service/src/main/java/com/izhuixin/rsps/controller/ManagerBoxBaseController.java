package com.izhuixin.rsps.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.constant.OperateType;
import com.izhuixin.rsps.common.vo.web.*;
import com.izhuixin.rsps.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("boxBase")
@Controller
public class ManagerBoxBaseController {

    @Autowired
    private BoxBaseService boxBaseService;
    @Autowired
    private EnterpriseAndUserService enterpriseAndUserService;
    @Autowired
    private BoxDetailService boxDetailService;
    @Autowired
    private BoxRecordService boxRecordService;
    @Autowired
    private CoapCmdService coapCmdService;

    /**
     * 包装箱管理主页
     * @return
     */
    @RequestMapping("/list/show")
    public String listShow(ModelMap map) {

        List<EnterpriseAndUserInfo> euInfos = enterpriseAndUserService.getAllEnterpriseAndUserInfo();
        List<EntNameVO> entNames = new ArrayList<>();
        for (EnterpriseAndUserInfo enterprise : euInfos) {
            EntNameVO entName = new EntNameVO();
            entName.setEntName(enterprise.getEntName());
            entName.setEntId(enterprise.getEntId());
            entNames.add(entName);

        }
        map.put("entNames",entNames);
        return "manager/boxBase/box_base";
    }

    /**
     * ajax加载数据
     */
    @RequestMapping("/list/content")
    public String listContent(ModelMap map){
        return "manager/boxBase/box_base_list_content";
    }

    /**
     * 加载数据
     */
    @RequestMapping("/list/data")
    @ResponseBody
    public String listData(HttpServletRequest request,DataTableReqDataVO dataTableReqDataVO){

//        dataTableReqDataVO.set

        Gson gson = new Gson();
        return gson.toJson(boxBaseService.getboxBaseInfoVOPage(dataTableReqDataVO));
    }
    /**
     * 包装箱详情页面
     */
    @RequestMapping("/list/box/detail")
    public String listDetail(HttpServletRequest request,ModelMap map){
        BoxDetailInfo boxDetailInfo = null;
        BoxBaseInfoVO boxBaseInfoVO = null;

        if(request.getParameter("id")!=null)
        {
            boxDetailInfo = boxDetailService.getBoxDetail(boxBaseService.getBoxBaseById(Integer.parseInt(request.getParameter("id"))).getUuid());
            boxBaseInfoVO = boxBaseService.getBoxBaseById(Integer.parseInt(request.getParameter("id")));
        }

        if(boxBaseInfoVO.getHardwareVersion()==null){
            boxBaseInfoVO.setHardwareVersion("未设置型号");
        }
        if(boxBaseInfoVO.getSoftwareVersion()==null){
            boxBaseInfoVO.setSoftwareVersion("未设置型号");
        }
        List<BoxFlowRecordVO> boxFlowInfo = boxRecordService.getBoxFlowInfo(boxBaseService.getBoxBaseById(Integer.parseInt(request.getParameter("id"))).getRfid(), null, enterpriseAndUserService.getEnterpriseAndUserInfoByEntName(boxBaseService.getBoxBaseById(Integer.parseInt(request.getParameter("id"))).getEntName()).getEntCode().concat("_"));
        if(boxFlowInfo!=null&&boxFlowInfo.size()!=0){
            boxBaseInfoVO.setStatus(OperateType.getDesc(boxFlowInfo.get(0).getOperateType()));
        }else {
            boxBaseInfoVO.setStatus("当前包装箱无状态");
        }
        map.put("boxDetail",boxBaseInfoVO);
        if(boxDetailInfo!=null){
            boxDetailInfo.setBoxId(boxBaseInfoVO.getRfid());
            map.put("boxDetailInfo",boxDetailInfo);
        }else {
            boxDetailInfo = new BoxDetailInfo();
            boxDetailInfo.setBoxId(boxBaseService.getBoxBaseById(Integer.parseInt(request.getParameter("id"))).getRfid());
            boxDetailInfo.setUuid(boxBaseService.getBoxBaseById(Integer.parseInt(request.getParameter("id"))).getUuid());
            map.put("boxDetailInfo",boxDetailInfo);
        }

        return "manager/boxBase/box_detail";
    }

    @RequestMapping("/boxDetail/save/handle")
    @ResponseBody
    public String saveHandle(BoxDetailInfo boxDetailInfo){
        JsonObject jsonObject = new JsonObject();


        boolean b = boxDetailService.saveBoxDetail(boxDetailInfo);

        //调用coap接口
        BoxPara boxPara = new BoxPara();
        boxPara.setBoxid(boxDetailInfo.getBoxId());
        boxPara.setBroadcastInterval(boxDetailInfo.getBrcycle());
        boxPara.setCanConnect(boxDetailInfo.getCanconnect());
        boxPara.setEnableInterrupt(boxDetailInfo.getCaninterrupt());
        boxPara.setBroadcastPower(boxDetailInfo.getBrpower());
        boxPara.setTempInterval(boxDetailInfo.getTempcycle());
        boxPara.setVoltageInterval(boxDetailInfo.getVolcycle());
        boxPara.setRfidWriteInterval(boxDetailInfo.getRfidcycle());
        boxPara.setOpenCheckDelay(boxDetailInfo.getOpencheckdelay());
        boxPara.setVibrateThreshold(boxDetailInfo.getVibrationCount1());
        boxPara.setCycleMode(boxDetailInfo.getCommunicateMode());
        boxPara.setCycleTimes(boxDetailInfo.getCommunicateRate());

        coapCmdService.cmdPara(boxPara);

        if(b){
            jsonObject.addProperty("result","success");
        }else{
            jsonObject.addProperty("result","failure");
        }

        return jsonObject.toString();
    }
}
