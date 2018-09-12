package com.izhuixin.rsps.controller;

import com.google.common.collect.Lists;
import com.izhuixin.rsps.common.ManagerUserDetail;
import com.izhuixin.rsps.service.BoxInfoService;
import com.izhuixin.rsps.service.BoxStatusReportService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ManagerMainController {

    @Autowired
    private BoxInfoService boxInfoService;

    @Autowired
    private BoxStatusReportService boxStatusReportService;

    public ManagerMainController() {
    }

    @RequestMapping("/")
    public String portal(ModelMap modelMap, HttpServletRequest request) {

        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        List<String> permissions = userDetail.getPermissions();


        if (permissions.contains("ROLE_HOME_SHOW")) {
            return "redirect:/manager/index/";
        } else if (permissions.contains("ROLE_SYS_USER_SHOW")) {
            return "redirect:/manager/sysUser/list/show/";
        } else if (permissions.contains("ROLE_USER_SHOW")) {
            return "redirect:/manager/user/";
        } else if (permissions.contains("ROLE_LINE_SHOW")) {
            return "redirect:/manager/line/";
        } else if(permissions.contains("ROLE_ENTERPRISE_SHOW")) {
            return "redirect:/manager/enterprise/list/show";
        } else {
            return "access_denied";
        }

    }

    @RequestMapping("/index")
    public String index(ModelMap modelMap, HttpServletRequest request) {

        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        Long countBinding = boxInfoService.countBindingBoxes(userDetail.getEntCode().concat("_"));
        Long countTransporting = boxInfoService.countTransportingBoxes(userDetail.getEntCode().concat("_"));
        Long countRetention = boxInfoService.countRetentionBoxes(userDetail.getEntCode().concat("_"));
        Long countRecycle = boxInfoService.countRecycleBoxes(userDetail.getEntCode().concat("_"));
        Long countLeisure = boxInfoService.countLeisureBoxes(userDetail.getEntCode().concat("_"));

        modelMap.put("countBinding", countBinding);
        modelMap.put("countTransporting", countTransporting);
        modelMap.put("countRetention", countRetention);
        modelMap.put("countRecycle", countRecycle);
        modelMap.put("countLeisure", countLeisure);
        modelMap.put("countTotal", countBinding + countTransporting + countRetention + countRecycle + countLeisure);

        return "manager/index";
    }

    /**
     * 展示某天包装出货量信息
     * @param modelMap
     * @param request
     * @param dataIndex
     * @return
     */
    @RequestMapping("/index/shipments/show")
    public String showShipments(ModelMap modelMap,
                                HttpServletRequest request,
                                Integer dataIndex) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<String[]> resList = null;
        DateTime now = DateTime.now();
        String curDate = "";
        if (dataIndex != null) {
            DateTime dt = now.minusDays(30 - (dataIndex + 1));
            curDate = dt.toString("yyyy年MM月dd日");
            resList = boxStatusReportService.queryShipment(userDetail.getEntCode().concat("_"), dt.toString("yyyy-MM-dd HH:mm:ss"));
        }
        if (resList == null) {
            resList = Lists.newArrayList();
        }
        modelMap.put("shipments", resList);
        modelMap.put("curDate", curDate);
        return "manager/box/shipments_list";
    }

    /**
     * 导出出货量数据
     * @param request
     */
    @RequestMapping("/index/shipments/export")
    public ResponseEntity<byte[]> exportShipment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
//        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        return boxStatusReportService.exportCSV(userDetail.getEntCode().concat("_"), "出货量");
    }


}
