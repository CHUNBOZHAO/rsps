package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.ManagerUserDetail;
import com.izhuixin.rsps.common.constant.Constants;
import com.izhuixin.rsps.common.dao.PermissionInfo;
import com.izhuixin.rsps.common.vo.web.PermissionInfoVO;
import com.izhuixin.rsps.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限配置
 */
@Controller
@RequestMapping("permission")
public class ManagerPermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 权限设置主页
     * @param modelMap
     * @return
     */
    @RequestMapping("/setting/index")
    public String settingIndex(ModelMap modelMap,
                               HttpServletRequest request,
                               String pid,
                               String targetUserId,
                               String targetNickname) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        List<String> permissionInfos = userDetail.getPermissions();

        if(pid.equals(Constants.ADMIN_ID)){

            List<PermissionInfoVO> vos = permissionService.findAllPermissionsVOByTargetId(targetUserId);
            //分类别显示权限
            //①总览
            List<PermissionInfoVO> vos1 = new ArrayList<>();
            //用户管理
            List<PermissionInfoVO> vos2 = new ArrayList<>();
            //其他
            List<PermissionInfoVO> vos3 = new ArrayList<>();

            for (PermissionInfoVO permissionInfoVO:vos) {
                if("ROLE_HOME_SHOW".equals(permissionInfoVO.getName())||"ROLE_OPERATION_SHOW".equals(permissionInfoVO.getName())){
                    vos1.add(permissionInfoVO);
                }
                if("ROLE_ENTERPRISE_SHOW".equals(permissionInfoVO.getName())||"ROLE_SYS_USER_SHOW".equals(permissionInfoVO.getName())||"ROLE_USER_SHOW".equals(permissionInfoVO.getName())){
                    vos2.add(permissionInfoVO);
                }
                if("ROLE_LINE_SHOW".equals(permissionInfoVO.getName())||"ROLE_REPORT_SHOW".equals(permissionInfoVO.getName())||"ROLE_BOX_BASE_SHOW".equals(permissionInfoVO.getName())){
                    vos3.add(permissionInfoVO);
                }
            }

            modelMap.put("permissionInfos1", vos1);
            modelMap.put("permissionInfos2", vos2);
            modelMap.put("permissionInfos3", vos3);
            modelMap.put("targetNickname", targetNickname);
            modelMap.put("targetUserId",targetUserId);
        }else{
            List<PermissionInfoVO> vos = permissionService.getPermissionInfoByUser(pid, targetUserId);
            //分类别显示权限
            //①总览
            List<PermissionInfoVO> vos1 = new ArrayList<>();
            //用户管理
            List<PermissionInfoVO> vos2 = new ArrayList<>();
            //其他
            List<PermissionInfoVO> vos3 = new ArrayList<>();

            for (PermissionInfoVO permissionInfoVO:vos) {
                if("ROLE_HOME_SHOW".equals(permissionInfoVO.getName())||"ROLE_OPERATION_SHOW".equals(permissionInfoVO.getName())){
                    vos1.add(permissionInfoVO);
                }
                if("ROLE_ENTERPRISE_SHOW".equals(permissionInfoVO.getName())||"ROLE_SYS_USER_SHOW".equals(permissionInfoVO.getName())||"ROLE_USER_SHOW".equals(permissionInfoVO.getName())){
                    vos2.add(permissionInfoVO);
                }
                if("ROLE_LINE_SHOW".equals(permissionInfoVO.getName())||"ROLE_REPORT_SHOW".equals(permissionInfoVO.getName())||"ROLE_BOX_BASE_SHOW".equals(permissionInfoVO.getName())){
                    vos3.add(permissionInfoVO);
                }
            }

            modelMap.put("permissionInfos1", vos1);
            modelMap.put("permissionInfos2", vos2);
            modelMap.put("permissionInfos3", vos3);
            modelMap.put("targetNickname", targetNickname);
            modelMap.put("targetUserId", targetUserId);
        }
        return "manager/permission/setting_index";
    }

    /**
     * 设置权限
     * @param permissionIds
     * @param targetUserId
     * @return
     */
    @RequestMapping("/setting/handle")
    @ResponseBody
    public String settingHandle(String[] permissionIds, String targetUserId) {
        boolean res = permissionService.settingPermission(targetUserId, permissionIds);
        return res ? "success" : "failed";
    }
}
