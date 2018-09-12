package com.izhuixin.rsps.controller;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.izhuixin.rsps.common.*;
import com.izhuixin.rsps.domain.BoxInfoDto;
import com.izhuixin.rsps.domain.BoxTypeInfo;
import com.izhuixin.rsps.service.feign.*;
import com.izhuixin.rsps.util.SessionUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CustomMainController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BoxLocationRecordService boxLocationRecordService;

    @Autowired
    private BoxInfoService boxInfoService;

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private BoxTypeService boxTypeService;

    @Autowired
    private OrderDriverService orderDriverService;

    @Autowired
    private CustomInfoService customInfoService;

    //@Value("#{configProperties['domain']}")
    @Value("${rsps.domain}")
    private String domain;

    /**
     * 主页显示客户当前订单、历史订单信息
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap modelMap,
                        HttpServletRequest request,
                        @RequestParam(required = false) Integer pageIndex) {

        // 微信jssdk参数配置
        StringBuilder sb = new StringBuilder();
        sb.append(domain);
        sb.append("/custom/");
        if (pageIndex != null) {
            sb.append("?pageIndex=");
            sb.append(pageIndex);
        }
        WxConfigModel wxConfigModel = weChatService.getSignature(sb.toString());
        modelMap.put("wxConfigModel", wxConfigModel);

        if (pageIndex == null) {
            pageIndex = 1;
        }

        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", userDetail);
        Integer myBoxesCount = orderService.getMyBoxesCount(userDetail.getUserId(), userDetail.getTel() == null ? "" : userDetail.getTel());
        modelMap.put("myBoxesCount", myBoxesCount);

        modelMap.put("pageIndex", pageIndex);

        return "custom/index";
    }

    /**
     * 展示当前订单
     * @return
     */
    @RequestMapping(value = "/order/boxes/current", method = RequestMethod.GET)
    public String showCurrentBoxes(ModelMap modelMap, HttpServletRequest request, Paginator paginator) {
        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");

        // C端当前订单包装箱信息
        List<BoxInfoVO> cBoxes = orderService.getCustomerCurrentBoxes(userDetail.getUserId(), userDetail.getTel() == null ? "" : userDetail.getTel(), "");
        // B端当前订单包装箱信息
        BoxInfoDto bBoxes = orderService.getCustomCurrentBoxes(userDetail.getUserId(), paginator);

        cBoxes.addAll(bBoxes.getBoxInfos());

        int dataStatus = 0;
        if (bBoxes.getPaginator().getItems() > 0) {
            if (bBoxes.getPaginator().getPage() < bBoxes.getPaginator().getPages()) {  //  继续查询
                dataStatus = 1;
            } else {  // 已经没有待查询数据了
                dataStatus = 2;
            }
        }

        modelMap.put("boxes", cBoxes);
        modelMap.put("paginator", bBoxes.getPaginator());
        modelMap.put("dataStatus", dataStatus);
        return "custom/box/current_boxes";
    }

    /**
     * 展示历史订单
     * @return
     */
    @RequestMapping(value = "/order/boxes/history", method = RequestMethod.GET)
    public String showHistoryBoxes(ModelMap modelMap, HttpServletRequest request, Paginator paginator) {
        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");

        // C端历史订单包装箱信息
        List<BoxInfoVO> cBoxes = orderService.getCustomerHistoryBoxes(userDetail.getUserId(), userDetail.getTel() == null ? "" : userDetail.getTel(), "");
        // B端历史订单包装箱信息
        BoxInfoDto bBoxes = orderService.getCustomHistoryBoxes(userDetail.getUserId(), paginator);

        cBoxes.addAll(bBoxes.getBoxInfos());

        int dataStatus = 0;
        if (bBoxes.getPaginator().getItems() > 0) {
            if (bBoxes.getPaginator().getPage() < bBoxes.getPaginator().getPages()) {  //  继续查询
                dataStatus = 1;
            } else {  // 已经没有待查询数据了
                dataStatus = 2;
            }
        }

        modelMap.put("boxes", cBoxes);
        modelMap.put("paginator", bBoxes.getPaginator());
        modelMap.put("dataStatus", dataStatus);
        return "custom/box/history_boxes";
    }

    /**
     * 显示包装箱详细信息,包括药品清单
     * @param modelMap
     * @param orderId
     * @param orderType
     * @return
     */
    @RequestMapping(value = "/order/box/detail", method = RequestMethod.GET)
    public String showBoxDetail(ModelMap modelMap,
                                HttpServletRequest request,
                                String boxId,
                                String orderId,
                                Byte orderType,
                                Byte orderState,
                                Double longitude,
                                Double latitude) {
        // 保存位置信息，提供给详情页面使用
        request.getSession().setAttribute("longitude", longitude);
        request.getSession().setAttribute("latitude", latitude);

        // 包装箱流转信息
        List<BoxFlowRecordVO> flowRecordVOS = orderService.getBoxFlowInfo(boxId, orderId);
        BoxFlowRecordVO boxFlowRecord = orderDriverService.getFlowRecord(orderId);  // 获取揽货的记录
        if (boxFlowRecord != null) {
            flowRecordVOS.add(boxFlowRecord);
        }
        modelMap.put("flows", flowRecordVOS);

        // 包装箱清单
        List<DrugInfoVO> drugInfos = orderService.getBoxDetail(boxId, orderId, orderType);
        modelMap.put("drugs", drugInfos);

        // 包装箱信息
        List<BoxTypeVO> boxTypeVOS = Lists.newArrayList();
        if (orderState != null) {
            if (orderState.byteValue() == OrderState.WAITING_TAKE_IN.getIndex().byteValue()) {  // 等待揽件， 即未绑定包装箱的订单
                List<BoxTypeInfo> boxTypeInfos = boxInfoService.getBoxTypes(orderId);
                if (boxTypeInfos != null) {
                    BoxTypeVO boxTypeVO = null;
                    for (BoxTypeInfo boxTypeInfo : boxTypeInfos) {
                        boxTypeVO = new BoxTypeVO();
                        boxTypeVO.setTypeId(boxTypeInfo.getTypeId());
                        boxTypeVO.setSize(boxTypeInfo.getSize());
                        boxTypeVO.setName(boxTypeInfo.getName());
                        boxTypeVO.setCount(boxTypeInfo.getCount());
                        boxTypeVO.setColor(boxTypeInfo.getColor());
                        boxTypeVOS.add(boxTypeVO);
                    }
                }
            } else { // 其他状态，即已绑定的包装箱订单
                BoxTypeVO boxTypeVO = boxTypeService.getBoxType(boxId);
                if (boxTypeVO != null) {
                    boxTypeVOS.add(boxTypeVO);
                }
            }
        }

        // 订单信息
        OrderInfoVO orderInfoVO = orderService.getOrder(orderId);

        modelMap.put("boxId", boxId);
        modelMap.put("orderId", orderId);
        modelMap.put("orderType", orderType);
        modelMap.put("orderState", orderState);
        modelMap.put("boxes", boxTypeVOS);
        modelMap.put("orderInfo", orderInfoVO);

        return "custom/box/box_detail";
    }

    /**
     * 订单信息查询页面
     * @return
     */
    @RequestMapping(value = "/order/query", method = RequestMethod.GET)
    public String showOrderQuery() {
        return "custom/box/order_query";
    }

    /**
     * 查询订单信息
     * @return
     */
    @RequestMapping(value = "/order/handleQuery", method = RequestMethod.GET)
    public String handleOrderQuery(ModelMap modelMap, HttpServletRequest request, Paginator paginator, String orderId) {
        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");

        List<BoxInfoVO> cCurrentBoxes = orderService.getCustomerCurrentBoxes(userDetail.getUserId(), userDetail.getTel() == null ? "" : userDetail.getTel(), orderId);
        List<BoxInfoVO> cHistoryBoxes = orderService.getCustomerHistoryBoxes(userDetail.getUserId(), userDetail.getTel() == null ? "" : userDetail.getTel(), orderId);

        List<BoxInfoVO> currentBoxes = orderService.getCustomCurrentBoxesWithOrderId(userDetail.getUserId(), orderId);
        List<BoxInfoVO> historyBoxes = orderService.getCustomHistoryBoxesWithOrderId(userDetail.getUserId(), orderId);

        cCurrentBoxes.addAll(currentBoxes);
        cHistoryBoxes.addAll(historyBoxes);

        boolean isEmpty = false;
        if (cCurrentBoxes.isEmpty() && cHistoryBoxes.isEmpty()) {
            isEmpty = true;
        }

        modelMap.put("currentBoxes", cCurrentBoxes);
        modelMap.put("historyBoxes", cHistoryBoxes);
        modelMap.put("isEmpty", isEmpty);

        return "custom/box/query_boxes";
    }

    /**
     * 获取指定包装箱轨迹
     * @param boxId -- 包装箱ID即RFID
     * @param orderId -- 订单ID
     * @return
     */
    @RequestMapping(value = "/order/queryTrack", method = RequestMethod.GET)
    @ResponseBody
    public String queryBoxTrack(HttpServletRequest request,
                                String boxId,
                                String orderId) {
        Double longitude = (Double) request.getSession().getAttribute("longitude");
        Double latitude = (Double) request.getSession().getAttribute("latitude");

        String boxTrack = boxLocationRecordService.queryBoxTrack(boxId, orderId);

        if ("[]".equals(boxTrack) && longitude != null && latitude != null) {
            JsonArray ja = new JsonArray();
            JsonArray jaItem = new JsonArray();
            double[] pos = FinalPositionUtils.transBMapPosition(latitude, longitude);
            jaItem.add(pos[1]);
            jaItem.add(pos[0]);
            ja.add(jaItem);
            boxTrack = ja.toString();
        }

        return boxTrack;
    }

    /**
     * 获取我的包装箱
     * @param modelMap
     * @param request
     * @return
     */
    @RequestMapping(value = "/order/queryMyBoxes", method = RequestMethod.GET)
    public String queryMyBoxes(ModelMap modelMap, HttpServletRequest request) {
        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");

        List<BoxInfoVO> boxInfos = orderService.getMyBoxes(userDetail.getUserId(), userDetail.getTel() == null ? "" : userDetail.getTel());

        modelMap.put("boxInfos", boxInfos);
        return "custom/box/my_boxes";
    }

    /**
     * 获取我的包装箱
     * @param request
     * @return
     */
    @RequestMapping(value = "/order/myBoxes/count/query", method = RequestMethod.GET)
    @ResponseBody
    public String queryMyBoxesCount(HttpServletRequest request) {
        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");
        Integer myBoxesCount = orderService.getMyBoxesCount(userDetail.getUserId(), userDetail.getTel() == null ? "" : userDetail.getTel());
        return String.valueOf(myBoxesCount);
    }

    /**
     * 归还包装箱
     * @param request
     * @param boxIds
     * @return
     */
    @RequestMapping(value = "/order/showBoxReturn", method = RequestMethod.GET)
    public String showBoxReturn(HttpServletRequest request,
                                String[] boxIds) {
        request.getSession().setAttribute("returnBoxes", boxIds);
        return "custom/box/box_return";
    }

//    /**
//     * 处理归还包装箱
//     * @param request
//     * @return
//     */
//    @RequestMapping("/order/handleReturnBoxes")
//    @ResponseBody
//    public String handleReturnBoxes(HttpServletRequest request) {
////        String[] returnBoxes = (String[]) request.getSession().getAttribute("returnBoxes");
//        request.getSession().removeAttribute("returnBoxes");
//        return "success";
//    }

}
