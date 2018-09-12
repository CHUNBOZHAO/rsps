package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.CustomUserDetail;
import com.izhuixin.rsps.common.RecycleState;
import com.izhuixin.rsps.domain.RecycleApplyInfo;
import com.izhuixin.rsps.service.feign.CustomInfoService;
import com.izhuixin.rsps.service.feign.RecycleApplyInfoService;
import com.izhuixin.rsps.util.SessionUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 包装箱回收流程处理
 */
@Controller
@RequestMapping("box")
public class CustomBoxController {

    @Autowired
    private RecycleApplyInfoService recycleApplyInfoService;

    @Autowired
    private CustomInfoService customInfoService;


    /**
     * 申请回收操作
     * @param request
     * @return
     */
    @RequestMapping(value = "/recycle/apply", method = RequestMethod.GET)
    @ResponseBody
    public String applyRecycle(HttpServletRequest request) {
        String[] boxIds = (String[]) request.getSession().getAttribute("returnBoxes");
        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");

        boolean res = false;
        if (boxIds != null) {
            for (String boxId : boxIds) {
                RecycleApplyInfo recycleApplyInfo = new RecycleApplyInfo();
                recycleApplyInfo.setBoxId(boxId);
                recycleApplyInfo.setProposerId(userDetail.getUserId());
                recycleApplyInfo.setProposerTel(userDetail.getTel());
                recycleApplyInfo.setCreateTime(new Date());
                recycleApplyInfo.setProposerName(userDetail.getName());
                recycleApplyInfo.setState(RecycleState.UN_DO.getIndex().byteValue());
                // 回收方式 暂定
                recycleApplyInfo.setRecycleType((byte)1);
                // todo 目前简易处理，一个成功即成功
                res = recycleApplyInfoService.saveInfo(recycleApplyInfo);
            }
        }
        request.getSession().removeAttribute("returnBoxes");
        return res ? "success" : "failed";
    }

}
