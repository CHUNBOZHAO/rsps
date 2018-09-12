package com.izhuixin.rsps.controller;

import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.ManagerUserDetail;
import com.izhuixin.rsps.common.constant.SysUserType;
import com.izhuixin.rsps.common.vo.web.SysUserInfoVO;
import com.izhuixin.rsps.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RequestMapping("normal")
@Controller
public class ManagerNormalSysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 普通用户主页
     * @return
     */
    @RequestMapping("/list/show")
    public String listShow() {
        return "manager/normalSysUser/sys_user_list";
    }

    /**
     * 普通用户列表
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping("/list/content")
    public String listContent(ModelMap modelMap, HttpServletRequest request) {
        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

        List<SysUserInfoVO> infoVOS = sysUserService.getDeepSysUserInfoVOsByUserIdAndUserType(userDetail.getUserId(),SysUserType.NORMALUSER.getIndex());

        modelMap.put("sysUserInfos", infoVOS);

        return "manager/normalSysUser/sys_user_list_content";
    }


    /**
     * 显示新增、编辑页面
     * @return
     */
    @RequestMapping("/edit/show")
    public String showEdit(ModelMap modelMap, HttpServletRequest request, String userId) {
        SysUserInfoVO vo = null;
        if (StringUtils.isNotBlank(userId)) {
            vo = sysUserService.getUserInfoVOByUserId(userId);
        }

        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");

//        List<SysUserInfoVO> sysUserInfoVOS = sysUserService.getDeepSysUserInfoVOs(userDetail.getUserId());
        List<SysUserInfoVO> sysUserInfoVOS = sysUserService.getDeepSysUserInfoVOsByUserIdAndUserType(userDetail.getUserId(),SysUserType.NORMALUSER.getIndex());
        SysUserInfoVO curUser = sysUserService.getUserInfoVOByUserId(userDetail.getUserId());
        sysUserInfoVOS.add(0, curUser);

        if (vo == null) {
            vo = new SysUserInfoVO();

            modelMap.put("mode", "add");
        } else {
            modelMap.put("mode", "edit");
            vo.setUserType((byte)SysUserType.NORMALUSER.getIndex().intValue());
        }

        modelMap.put("userInfo", vo);
        modelMap.put("parentUsers", sysUserInfoVOS);

        return "manager/normalSysUser/sys_user_add";
    }

    /**
     * 保存、编辑处理
     * @return
     */
    @RequestMapping("/edit/handle")
    @ResponseBody
    public String handleEdit(HttpServletRequest request, SysUserInfoVO sysUserInfoVO) {

        JsonObject resJo = new JsonObject();
        resJo.addProperty("result", "failed");

        // 设置用户关于父节点信息
//        ManagerUserDetail userDetail = (ManagerUserDetail) request.getSession().getAttribute("user");
        SysUserInfoVO parentUserInfo = sysUserService.getUserInfoVOByUserId(sysUserInfoVO.getPid());

        sysUserInfoVO.setModifyTime(new Date());
        sysUserInfoVO.setPid(parentUserInfo.getUserId());
        sysUserInfoVO.setLevel((byte)(parentUserInfo.getLevel() + 1));

        if (StringUtils.isBlank(sysUserInfoVO.getUserId())) {
            boolean checkRes = sysUserService.checkUserName(null, sysUserInfoVO.getUserName());
            if (checkRes) {
                resJo.addProperty("msgCode", "101");
            } else {
                sysUserInfoVO.setEntId(parentUserInfo.getEntId());
                sysUserInfoVO.setUserType((byte)SysUserType.NORMALUSER.getIndex().intValue());

                boolean resSave = sysUserService.saveUserInfo(sysUserInfoVO);

                if (resSave) {
                    resJo.addProperty("result", "success");
                }
            }
        } else {
            boolean checkRes = sysUserService.checkUserName(sysUserInfoVO.getUserId(), sysUserInfoVO.getUserName());
            if (checkRes) {
                resJo.addProperty("msgCode", "101");
            } else {
                boolean resUpdate = sysUserService.updateUserInfo(sysUserInfoVO);
                if (resUpdate) {
                    resJo.addProperty("result", "success");
                }
            }
        }
        return resJo.toString();
    }

    /**
     * 删除处理
     * @return
     */
    @RequestMapping("/delete/handle/{userId}")
    @ResponseBody
    public String handleDelete(@PathVariable("userId") String userId) {
        boolean res = sysUserService.deleteUser(userId);
        return res ? "success" : "failed";
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @RequestMapping("/password/reset/{userId}")
    @ResponseBody
    public String resetPwd(@PathVariable("userId") String userId) {
        boolean res = sysUserService.resetPwd(userId);
        return res ? "success" : "failed";
    }


}
