package com.izhuixin.rsps.controller;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.ManagerUserDetail;
import com.izhuixin.rsps.common.constant.BoxStatus;
import com.izhuixin.rsps.common.constant.OperateType;
import com.izhuixin.rsps.common.constant.OperatorType;
import com.izhuixin.rsps.common.vo.web.BoxFlowRecordVO;
import com.izhuixin.rsps.common.vo.web.BoxInfoVO;
import com.izhuixin.rsps.common.vo.web.BoxesCustomInfoVO;
import com.izhuixin.rsps.common.vo.web.BoxesOperatorInfoVO;
import com.izhuixin.rsps.service.BoxInfoService;
import com.izhuixin.rsps.service.BoxRecordService;
import com.izhuixin.rsps.service.BoxStatusReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("box")
public class ManagerBoxController {

    @Autowired
    private BoxInfoService boxInfoService;

    @Autowired
    private BoxRecordService boxRecordService;

    @Autowired
    private BoxStatusReportService boxStatusReportService;

    /**
     * 查询指定状态包装箱使用客户信息列表
     * @param modelMap
     * @return
     */
    @RequestMapping("/queryCustoms")
    public String queryCustoms(ModelMap modelMap, Byte boxStatus, Long total, HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        //Map<String, Object> a = (Map<String, Object>)request.getSession().getAttribute("claims");
        String a = request.getHeader("user_name");
        request.getSession().setAttribute("username","username");
        List<BoxesCustomInfoVO> customInfoVOS = null;
        if (boxStatus != null) {
            List<Byte> bytes = Lists.newArrayList();
            if (boxStatus.byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue()) {
                bytes.add(BoxStatus.TRANSPORTING.getIndex().byteValue());
                bytes.add(BoxStatus.TRANSIT.getIndex().byteValue());
            } else {
                bytes.add(boxStatus);
            }
            customInfoVOS = boxInfoService.queryCustomInfos(bytes, userDetail.getEntCode().concat("_"));
        }
        if (customInfoVOS == null) {
            customInfoVOS = Lists.newArrayList();
        }

        modelMap.put("customs", customInfoVOS);
        modelMap.put("boxStatus", boxStatus);
        modelMap.put("statusDesc", BoxStatus.getDesc(boxStatus));
        modelMap.put("total", total);
        return "manager/box/custom_list";
    }

    /**
     * 查询指定状态包装箱使用操作人信息列表
     * @param modelMap
     * @param boxStatus
     * @return
     */
    @RequestMapping("/queryOperators")
    public String queryOperators(ModelMap modelMap, Byte boxStatus, Long total, HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<BoxesOperatorInfoVO> operatorInfoVOS = null;
        if (boxStatus != null) {
            List<Byte> bytes = Lists.newArrayList();
            if (boxStatus.byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue()) {
                bytes.add(BoxStatus.TRANSPORTING.getIndex().byteValue());
                bytes.add(BoxStatus.TRANSIT.getIndex().byteValue());
            } else {
                bytes.add(boxStatus);
            }
            operatorInfoVOS = boxInfoService.queryOperators(bytes, userDetail.getEntCode().concat("_"));
        }
        if (operatorInfoVOS == null) {
            operatorInfoVOS = Lists.newArrayList();
        }

        modelMap.put("operators", operatorInfoVOS);
        modelMap.put("boxStatus", boxStatus);
        modelMap.put("statusDesc", BoxStatus.getDesc(boxStatus));
        modelMap.put("total", total);
        return "manager/box/operator_list";
    }

    /**
     * 查询客户使用的包装箱信息
     * @param modelMap
     * @param boxStatus
     * @param customId
     * @return
     */
    @RequestMapping("/queryCustomBoxes")
    public String queryCustomBoxes(ModelMap modelMap, Byte boxStatus, String customId,
                                   String customName, HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<BoxInfoVO> boxInfoVOList = null;
        if (boxStatus != null) {
            List<Byte> bytes = Lists.newArrayList();
            if (boxStatus.byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue()) {
                bytes.add(BoxStatus.TRANSPORTING.getIndex().byteValue());
                bytes.add(BoxStatus.TRANSIT.getIndex().byteValue());
            } else {
                bytes.add(boxStatus);
            }
            boxInfoVOList = boxInfoService.queryBoxesByCustomId(bytes, customId, userDetail.getEntCode().concat("_"));
        }
        if (boxInfoVOList == null) {
            boxInfoVOList = Lists.newArrayList();
        }

        modelMap.put("boxes", boxInfoVOList);
        modelMap.put("statusDesc", BoxStatus.getDesc(boxStatus));
        modelMap.put("customName", customName);
        modelMap.put("boxStatus", boxStatus);
        return "manager/box/box_list";
    }

    /**
     * 查询操作人的包装箱信息
     * @param modelMap
     * @param operatorId
     * @return
     */
    @RequestMapping("/queryOperatorBoxes")
    public String queryOperatorBoxes(ModelMap modelMap, Byte boxStatus,
                                     String operatorId, String operator,
                                     HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<BoxInfoVO> boxInfoVOList = null;
        if (boxStatus != null) {
            List<Byte> bytes = Lists.newArrayList();
            if (boxStatus.byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue()) {
                bytes.add(BoxStatus.TRANSPORTING.getIndex().byteValue());
                bytes.add(BoxStatus.TRANSIT.getIndex().byteValue());
            } else {
                bytes.add(boxStatus);
            }
            boxInfoVOList = boxInfoService.queryBoxesByOperatorId(bytes, operatorId, userDetail.getEntCode().concat("_"));
        }
        if (boxInfoVOList == null) {
            boxInfoVOList = Lists.newArrayList();
        }

        modelMap.put("boxes", boxInfoVOList);
        modelMap.put("statusDesc", BoxStatus.getDesc(boxStatus));
        modelMap.put("operator", operator);
        modelMap.put("boxStatus", boxStatus);
        return "manager/box/box_list";
    }

    /***
     * 包装箱列表
     * @param modelMap
     * @param status
     * @return
     */
    @RequestMapping("/listBox")
    public String listBox(ModelMap modelMap, Byte status, HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<BoxInfoVO> boxInfoVOList = null;
        if (status != null) {
            List<Byte> bytes = Lists.newArrayList();
            if (status.byteValue() == BoxStatus.TRANSPORTING.getIndex().byteValue()) {
                bytes.add(BoxStatus.TRANSPORTING.getIndex().byteValue());
                bytes.add(BoxStatus.TRANSIT.getIndex().byteValue());
            } else {
                bytes.add(status);
            }
            boxInfoVOList = boxInfoService.queryBoxesByStatus(bytes, userDetail.getEntCode().concat("_"));
        }
        if (boxInfoVOList == null) {
            boxInfoVOList = Lists.newArrayList();
        }

        modelMap.put("boxes", boxInfoVOList);
        modelMap.put("statusDesc", BoxStatus.getDesc(status));
        return "manager/box/box_list";
    }

    /**
     * 闲置的包装箱
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/listLeisureBox")
    public String queryLeisureBoxes(ModelMap modelMap, HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<BoxInfoVO> boxInfoVOList = boxInfoService.queryLeisureBoxes(userDetail.getEntCode().concat("_"));

        modelMap.put("boxes", boxInfoVOList);

        return "manager/box/leisure_box_list";
    }

    /**
     * 所有包装箱列表
     * @param modelMap
     * @return
     */
    @RequestMapping("/queryBoxes")
    public String queryBoxes(ModelMap modelMap, HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<BoxInfoVO> boxInfoVOList = null;
        boxInfoVOList = boxInfoService.queryAllBoxes(userDetail.getEntCode().concat("_"));
        modelMap.put("boxes", boxInfoVOList);
        return "manager/box/all_box_list";
    }

    @RequestMapping("/record/list")
    public String queryBoxRecords(ModelMap modelMap,
                                  HttpServletRequest request,
                                  String boxId,
                                  @RequestParam(required = false) String orderId) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        /**显示最近两次的循环记录*/
        List<BoxFlowRecordVO> flowRecords = boxRecordService.getBoxFlowInfo(boxId, orderId, userDetail.getEntCode().concat("_"));

//        Collections.reverse(flowRecords);

        List<BoxFlowRecordVO> flowRecords1 = new ArrayList<BoxFlowRecordVO>();

        int count = 0;

        for (BoxFlowRecordVO boxFlowRecordVO:flowRecords) {
            if(boxFlowRecordVO.getOperateType().equals(OperateType.BINDING.getIndex().byteValue())){
                count++;
            }
            if(count<=2){
                flowRecords1.add(boxFlowRecordVO);
                if(count==2){
                    break;
                }
            }
        }
//        Collections.reverse(flowRecords1);



        modelMap.put("boxId", boxId);
        modelMap.put("flowRecords", flowRecords1);
        return "manager/box/box_record_list";
    }

    /**
     * 删除包装箱
     * @param request
     * @param rfid
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public String deleteBox(HttpServletRequest request,
                            String rfid) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        if (userDetail.getUserType() != OperatorType.SUPER.getIndex().byteValue()) {
            return "access_denied";
        }

        JsonObject jsonObject = new JsonObject();

        boolean res = boxInfoService.deleteBox(rfid, userDetail.getEntCode().concat("_"));
        if (res) {
            jsonObject.addProperty("result", "success");
        } else {
            jsonObject.addProperty("result", "failed");
        }
        return jsonObject.toString();
    }


}
