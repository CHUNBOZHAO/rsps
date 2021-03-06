package com.izhuixin.rsps.controller;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.ManagerUserDetail;
import com.izhuixin.rsps.common.vo.web.*;
import com.izhuixin.rsps.service.LineService;
import com.izhuixin.rsps.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("line")
public class ManagerLineController {

    @Autowired
    private LineService lineService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 线路列表
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "manager/line/line_list";
    }

    /**
     * 线路列表内容
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/list")
    public String listContent(ModelMap modelMap, HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<LineInfoVO> lineInfos = lineService.queryChildLines(userDetail.getUserId(), userDetail.getEntCode().concat("_"));
        modelMap.put("lineInfos", lineInfos);

        return "manager/line/line_list_content";
    }

    /**
     * 线路信息新增、编辑页面
     * @param modelMap
     * @param request
     * @param lineId
     * @return
     */
    @RequestMapping("/show")
    public String showLine(ModelMap modelMap, HttpServletRequest request, String lineId) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<SysUserInfoVO> sysUserInfoVOS = null;

        String mode = "";
        LineInfoVO lineInfo = null;
        if (StringUtils.isNotBlank(lineId)) {
            lineInfo = lineService.queryLine(lineId, userDetail.getEntCode().concat("_"));
        }
        if (lineInfo == null) {
            lineInfo = new LineInfoVO();

            sysUserInfoVOS = sysUserService.getDeepSysUserInfoVOs(userDetail.getUserId());
            SysUserInfoVO curUser = sysUserService.getUserInfoVOByUserId(userDetail.getUserId());
            sysUserInfoVOS.add(0, curUser);

            mode = "add";
        } else {
            sysUserInfoVOS = Lists.newArrayList();
            SysUserInfoVO curUser = sysUserService.getUserInfoVOByUserId(lineInfo.getUserId());
            sysUserInfoVOS.add(0, curUser);

            mode = "edit";
        }

        modelMap.put("mode", mode);
        modelMap.put("lineInfo", lineInfo);
        modelMap.put("parentUsers", sysUserInfoVOS);

        return "manager/line/line_add";
    }

    /**
     * 保存/编辑线路信息
     * @param request
     * @param lineInfoVO
     * @return
     */
    @RequestMapping("/save/handle")
    @ResponseBody
    public String handleSave(HttpServletRequest request, LineInfoVO lineInfoVO) {
        JsonObject resJo = new JsonObject();
        resJo.addProperty("result", "failed");

        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        if (StringUtils.isBlank(lineInfoVO.getLineId())) {
//            lineInfoVO.setUserId(userDetail.getUserId());
            boolean checkRes = lineService.checkLineName(null, lineInfoVO.getLineName(), lineInfoVO.getUserId(), userDetail.getEntCode().concat("_"));
            if (checkRes) {
                resJo.addProperty("msgCode", "101");
            } else {
                boolean resSave = lineService.saveLine(lineInfoVO, userDetail.getEntCode().concat("_"));
                if (resSave) {
                    resJo.addProperty("result", "success");
                }
            }
        } else {
            boolean checkRes = lineService.checkLineName(lineInfoVO.getLineId(), lineInfoVO.getLineName(), lineInfoVO.getUserId(), userDetail.getEntCode().concat("_"));
            if (checkRes) {
                resJo.addProperty("msgCode", "101");
            } else {
                boolean resUpdate = lineService.updateLine(lineInfoVO, userDetail.getEntCode().concat("_"));
                if (resUpdate) {
                    resJo.addProperty("result", "success");
                }
            }
        }
        return resJo.toString();
    }

    /**
     * 删除线路
     * @param request
     * @param lineId
     * @return
     */
    @RequestMapping("/del/handle/{lineId}")
    @ResponseBody
    public String handleDel(HttpServletRequest request, @PathVariable("lineId") String lineId) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        boolean res = lineService.removeLine(lineId, userDetail.getEntCode().concat("_"));
        return res ? "success" : "failed";
    }

    /**
     * 线路关联客户显示主页
     * @param lineId
     * @param lineName
     * @return
     */
    @RequestMapping("/related/custom/show")
    public String showRelatedCustom(ModelMap modelMap,
                                    String lineId,
                                    String lineName) {
        modelMap.put("lineId", lineId);
        modelMap.put("lineName", lineName);
        return "manager/line/related_custom_list";
    }

    /**
     * 线路关联客户显示列表
     * @param lineId
     * @return
     */
    @RequestMapping("/related/custom/list")
    public String listRelatedCustom(ModelMap modelMap, String lineId) {
        modelMap.put("lineId", lineId);
        return "manager/line/related_custom_list_content";
    }

    /**
     * 线路关联客户显示列表
     * @param request
     * @param lineId
     * @return
     */
    @RequestMapping("/related/custom/list/data")
    @ResponseBody
    public String listRelatedCustomData(HttpServletRequest request,
                                        String lineId,
                                        DataTableReqDataVO dtReqData) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        DataTableResDataVO dataTableVO = lineService.queryDtDataAssociatedCustom(lineId, dtReqData, userDetail.getEntId(), userDetail.getEntCode().concat("_"));
        return new Gson().toJson(dataTableVO);
    }


    /**
     * 取消线路客户关联
     * @param request
     * @param lineId
     * @param customIds
     * @return
     */
    @RequestMapping("/custom/relate/cancel")
    @ResponseBody
    public String deleteRelatedCustom(HttpServletRequest request, String lineId, String[] customIds) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        boolean res = lineService.removeLineAndCustom(lineId, customIds, userDetail.getEntCode().concat("_"));
        JsonObject resJo = new JsonObject();
        resJo.addProperty("result", res ? "success" : "failed");
        return resJo.toString();
    }

    /***
     * 线路客户关联
     * @param request
     * @param lineId
     * @param customIds
     * @return
     */
    @RequestMapping("/custom/relate/do")
    @ResponseBody
    public String bindRelatedCustom(HttpServletRequest request, String lineId, String[] customIds) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        boolean res = lineService.saveLineAndCustom(lineId, customIds, userDetail.getEntCode().concat("_"));
        JsonObject resJo = new JsonObject();
        resJo.addProperty("result", res ? "success" : "failed");
        return resJo.toString();
    }


    /**
     * 线路关联中转站显示主页
     * @param lineId
     * @param lineName
     * @return
     */
    @RequestMapping("/related/transfer/show")
    public String showRelatedTransfer(ModelMap modelMap, String lineId, String lineName, String curTransferId) {
        modelMap.put("lineId", lineId);
        modelMap.put("lineName", lineName);
        modelMap.put("curTransferId", curTransferId);
        return "manager/line/related_transfer_list";
    }

    /**
     * 线路关联中转站显示列表
     * @param request
     * @param lineId
     * @return
     */
    @RequestMapping("/related/transfer/list")
    public String listRelatedTransfer(ModelMap modelMap,
                                      HttpServletRequest request,
                                      String lineId,
                                      String curTransferId) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        String transferId = curTransferId;
        if (StringUtils.isBlank(transferId)) {
            transferId = userDetail.getUserId();
        }
        List<RelatedSysUserVO> transfers = lineService.queryAssociatedTransfer(lineId, transferId, userDetail.getEntCode().concat("_"));
        modelMap.put("transfers", transfers);
        modelMap.put("lineId", lineId);
        return "manager/line/related_transfer_list_content";
    }


    /**
     * 取消线路中转站关联
     * @param request
     * @param lineId
     * @param transferIds
     * @return
     */
    @RequestMapping("/transfer/relate/cancel")
    @ResponseBody
    public String deleteRelatedTransfer(HttpServletRequest request, String lineId, String[] transferIds) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        boolean res = lineService.removeLineAndTransfer(lineId, transferIds, userDetail.getEntCode().concat("_"));
        JsonObject resJo = new JsonObject();
        resJo.addProperty("result", res ? "success" : "failed");
        return resJo.toString();
    }

    /***
     * 线路中转站关联
     * @param request
     * @param lineId
     * @param transferIds
     * @return
     */
    @RequestMapping("/transfer/relate/do")
    @ResponseBody
    public String bindRelatedTransfer(HttpServletRequest request, String lineId, String[] transferIds) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        boolean res = lineService.saveLineAndTransfer(lineId, transferIds, userDetail.getEntCode().concat("_"));
        JsonObject resJo = new JsonObject();
        resJo.addProperty("result", res ? "success" : "failed");
        return resJo.toString();
    }


    /**
     * 线路关联配送员显示主页
     * @param lineId
     * @param lineName
     * @return
     */
    @RequestMapping("/related/operator/show")
    public String showRelatedOperator(ModelMap modelMap,
                                      String lineId,
                                      String lineName) {
        modelMap.put("lineId", lineId);
        modelMap.put("lineName", lineName);
        return "manager/line/related_operator_list";
    }

    /**
     * 线路关联配送员显示列表
     * @param request
     * @param lineId
     * @return
     */
    @RequestMapping("/related/operator/list")
    public String listRelatedOperator(ModelMap modelMap, HttpServletRequest request, String lineId) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        List<RelatedOperatorVO> operatorInfos = lineService.queryAssociatedOperator(lineId, userDetail.getUserId(), userDetail.getEntCode().concat("_"));
        modelMap.put("operatorInfos", operatorInfos);
        modelMap.put("lineId", lineId);
        return "manager/line/related_operator_list_content";
    }


    /**
     * 取消线路配送员关联
     * @param request
     * @param lineId
     * @param operatorIds
     * @return
     */
    @RequestMapping("/operator/relate/cancel")
    @ResponseBody
    public String deleteRelatedOperator(HttpServletRequest request, String lineId, String[] operatorIds) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        boolean res = lineService.removeLineAndOperator(lineId, operatorIds, userDetail.getEntCode().concat("_"));
        JsonObject resJo = new JsonObject();
        resJo.addProperty("result", res ? "success" : "failed");
        return resJo.toString();
    }

    /***
     * 线路配送员关联
     * @param request
     * @param lineId
     * @param operatorIds
     * @return
     */
    @RequestMapping("/operator/relate/do")
    @ResponseBody
    public String bindRelatedOperator(HttpServletRequest request, String lineId, String[] operatorIds) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        boolean res = lineService.saveLineAndOperator(lineId, operatorIds, userDetail.getEntCode().concat("_"));
        JsonObject resJo = new JsonObject();
        resJo.addProperty("result", res ? "success" : "failed");
        return resJo.toString();
    }

    /**
     * 线路关联-客户维度列表显示
     * @return
     */
    @RequestMapping("/custom/list/index")
    public String showCustomRelatedLines() {
        return "manager/line/custom_list";
    }

    /**
     * 线路关联-客户维度列表显示内容
     * @return
     */
    @RequestMapping("/custom/list/content")
    public String showCustomRelatedLinesContent() {
        return "manager/line/custom_list_content";
    }

    /**
     * 线路关联-客户维度列表数据
     * @param request
     * @param dtReqData
     * @return
     */
    @RequestMapping("/custom/list/data")
    @ResponseBody
    public String queryCustomRelatedLines(HttpServletRequest request, DataTableReqDataVO dtReqData) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        DataTableResDataVO dataTableVO = lineService.queryDtDataCustoms(dtReqData, userDetail.getEntId(), userDetail.getEntCode().concat("_"));
        return new Gson().toJson(dataTableVO);
    }


    /**
     * 客户关联线路显示主页
     * @param customId
     * @param customName
     * @return
     */
    @RequestMapping("/related/line/show")
    public String showRelatedLine(ModelMap modelMap,
                                    String customId,
                                    String customName) {
        modelMap.put("customId", customId);
        modelMap.put("customName", customName);
        return "manager/line/related_line_list";
    }

    /**
     * 客户关联线路显示列表
     * @param customId
     * @return
     */
    @RequestMapping("/related/line/list")
    public String listRelatedLine(ModelMap modelMap, HttpServletRequest request, String customId) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<Object[]> objects = lineService.queryAssociatedLine(customId, userDetail.getUserId(), userDetail.getEntCode().concat("_"));

        modelMap.put("customId", customId);
        modelMap.put("lineInfos", objects);
        return "manager/line/related_line_list_content";
    }

    /**
     * 取消线路客户关联
     * @param request
     * @param lineIds
     * @param customId
     * @return
     */
    @RequestMapping("/line/relate/cancel")
    @ResponseBody
    public String deleteRelatedLine(HttpServletRequest request, String[] lineIds, String customId) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        boolean res = lineService.removeCustomAndLine(customId, lineIds, userDetail.getEntCode().concat("_"));
        JsonObject resJo = new JsonObject();
        resJo.addProperty("result", res ? "success" : "failed");
        return resJo.toString();
    }

    /***
     * 线路客户关联
     * @param request
     * @param customId
     * @param lineIds
     * @return
     */
    @RequestMapping("/line/relate/do")
    @ResponseBody
    public String bindRelatedLine(HttpServletRequest request, String[] lineIds, String customId) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        boolean res = lineService.saveCustomAndLine(customId, lineIds, userDetail.getEntCode().concat("_"));
        JsonObject resJo = new JsonObject();
        resJo.addProperty("result", res ? "success" : "failed");
        return resJo.toString();
    }


}
