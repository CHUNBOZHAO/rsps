package com.izhuixin.rsps.controller;

import com.google.gson.JsonObject;
import com.izhuixin.rsps.common.*;
import com.izhuixin.rsps.service.feign.*;
import com.izhuixin.rsps.util.SessionUserUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("order")
public class CustomOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BoxTypeService boxTypeService;

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private OrderPolicyService orderPolicyService;

    @Value("${rsps.domain}")
    private String domain;

    @Autowired
    private CustomInfoService customInfoService;
    /**
     * 显示订单页面
     * @return
     */
    @RequestMapping(value = "/showOrder", method = RequestMethod.GET)
    public String showOrder(ModelMap modelMap, HttpServletRequest request,
                            @RequestParam(required = false) String orderId) {
        OrderInfoVO orderInfoVO = null;
        if (StringUtils.isNotEmpty(orderId)) {
            if (!orderId.equals("init")) {
                orderInfoVO = orderService.getOrder(orderId);
            }
        } else {
            orderInfoVO = (OrderInfoVO) request.getSession().getAttribute("order");
        }
        if (orderInfoVO == null) {
            orderInfoVO = new OrderInfoVO();
        }
        request.getSession().setAttribute("order", orderInfoVO);

        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");
        request.getSession().setAttribute("user", userDetail);
        orderInfoVO.setCustomerId(userDetail.getUserId());
        orderInfoVO.setCustomer(userDetail.getName());

        // 微信jssdk参数配置
        StringBuilder sb = new StringBuilder();
        sb.append(domain);
        sb.append("/custom/order/showOrder");
        if (orderId != null) {
            sb.append("?orderId=");
            sb.append(orderId);
        }
        WxConfigModel wxConfigModel = weChatService.getSignature(sb.toString());
        modelMap.put("wxConfigModel", wxConfigModel);

        // 寄件人信息
        StringBuffer sbSender = new StringBuffer();
        sbSender.append(orderInfoVO.getSenderName() == null ? "" : orderInfoVO.getSenderName());
        sbSender.append("   ");
        sbSender.append(orderInfoVO.getSenderArea1() == null ? "" : orderInfoVO.getSenderArea1());
        sbSender.append(" ");
        sbSender.append(orderInfoVO.getSenderArea2() == null ? "" : orderInfoVO.getSenderArea2());
        sbSender.append(" ");
        sbSender.append(orderInfoVO.getSenderArea3() == null ? "" : orderInfoVO.getSenderArea3());
        sbSender.append(" ");
        sbSender.append(orderInfoVO.getSenderAddress() == null ? "" : orderInfoVO.getSenderAddress().length() > 10 ? orderInfoVO.getSenderAddress().substring(0,10) + "..." : orderInfoVO.getSenderAddress());

        // 收件人信息
        StringBuffer sbReceiver = new StringBuffer();
        sbReceiver.append(orderInfoVO.getReceiverName() == null ? "" : orderInfoVO.getReceiverName());
        sbReceiver.append("   ");
        sbReceiver.append(orderInfoVO.getReceiverArea1() == null ? "" : orderInfoVO.getReceiverArea1());
        sbReceiver.append(" ");
        sbReceiver.append(orderInfoVO.getReceiverArea2() == null ? "" : orderInfoVO.getReceiverArea2());
        sbReceiver.append(" ");
        sbReceiver.append(orderInfoVO.getReceiverArea3() == null ? "" : orderInfoVO.getReceiverArea3());
        sbReceiver.append(" ");
        sbReceiver.append(orderInfoVO.getReceiverAddress() == null ? "" : orderInfoVO.getReceiverAddress().length() > 10 ? orderInfoVO.getReceiverAddress().substring(0,10) + "..." : orderInfoVO.getReceiverAddress());

        List<BoxTypeVO> boxes = orderInfoVO.getBoxes(); // 包装箱

        String detail = orderInfoVO.getDetail(); // 包裹备注

        modelMap.put("boxes", boxes);
        modelMap.put("detail", detail);
        modelMap.put("senderInfo", sbSender.toString().trim());
        modelMap.put("receiverInfo", sbReceiver.toString().trim());

        return "custom/order/show_order";
    }

    /**
     * 不需要包装箱，清除选中的包装箱
     * @param request
     * @return
     */
    @RequestMapping(value = "/clearBoxes", method = RequestMethod.GET)
    @ResponseBody
    public String clearBoxes(HttpServletRequest request) {
        OrderInfoVO orderInfoVO = (OrderInfoVO) request.getSession().getAttribute("order");
        if (orderInfoVO == null) {
            orderInfoVO = new OrderInfoVO();
        } else {
            orderInfoVO.getBoxes().clear();
        }
        request.getSession().setAttribute("order", orderInfoVO);
        return "success";
    }

    /**
     * 显示地址簿
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/showAddressBook", method = RequestMethod.GET)
    public String showAddressBook(ModelMap modelMap,
                                  HttpServletRequest request,
                                  Integer addressType,
                                  String boxDetail) {

        OrderInfoVO orderInfoVO = (OrderInfoVO) request.getSession().getAttribute("order");
        if (orderInfoVO != null) {
            orderInfoVO.setDetail(boxDetail);
        }
        request.getSession().setAttribute("order", orderInfoVO);

        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");
        List<AddressBookVO> addresses = addressBookService.getAddressBooks(userDetail.getUserId());

        modelMap.put("addressType", addressType);
        modelMap.put("addresses", addresses);

        return "custom/order/address_book";
    }

    /**
     * 新增/编辑地址簿页面
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/showAddressAdd", method = RequestMethod.GET)
    public String showAddressAdd(ModelMap modelMap, Integer addressType) {
        modelMap.put("areaProvince", "浙江省");
        modelMap.put("areaCity", "杭州市");
        modelMap.put("areaCounty", "滨江区");
        modelMap.put("addressType", addressType);
        return "custom/order/address_add";
    }

    /**
     * 新增地址簿处理
     * @return
     */
    @RequestMapping(value = "/handleAddressAdd", method = RequestMethod.POST)
    @ResponseBody
    public String handleAddressAdd(HttpServletRequest request,
                                   AddressBookVO addressBookVO,
                                   Integer addressType) {
        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");
        JsonObject joRes = new JsonObject();
        boolean res = false;
        if (addressBookVO.getId() != null) {
            res = addressBookService.updateAddressBook(addressBookVO, userDetail.getUserId());
        } else {
            res = addressBookService.saveAddressBook(addressBookVO, userDetail.getUserId());
        }
        joRes.addProperty("result", res ? "success" : "failed");
        joRes.addProperty("addressType", addressType);
        return joRes.toString();
    }

    /**
     * 删除地址簿
     * @return
     */
    @RequestMapping(value = "/address/delete", method = RequestMethod.GET)
    @ResponseBody
    public String deleteAddress(Long addressId) {
        boolean res = addressBookService.deleteAddressBook(addressId);
        return res ? "success" : "failed";
    }

    /**
     * 设置默认地址
     * @param request
     * @param addressId
     * @return
     */
    @RequestMapping(value = "/handleAddressDefault", method = RequestMethod.GET)
    @ResponseBody
    public String handleAddressDefault(HttpServletRequest request, Long addressId) {
        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");
        boolean res = addressBookService.setDefault(addressId, userDetail.getUserId());
        return res ? "success" : "failed";
    }

    /**
     * 选择地址簿 -- 选择完成后，跳转到下单页面
     * @param request
     * @param addressId
     * @param addressType 1:寄件人 2:收件人
     * @return
     */
    @RequestMapping(value = "/selectAddressBook", method = RequestMethod.GET)
    @ResponseBody
    public String selectAddressAdd(HttpServletRequest request, Long addressId, Integer addressType) {

        OrderInfoVO orderInfoVO = (OrderInfoVO) request.getSession().getAttribute("order");
        if (orderInfoVO == null) {
            orderInfoVO = new OrderInfoVO();
        }
        AddressBookVO addressBookVO = addressBookService.getAddressBook(addressId);
        if (addressType == 1) {
            orderInfoVO.setSenderName(addressBookVO.getName());
            orderInfoVO.setSenderTel(addressBookVO.getTel());
            orderInfoVO.setSenderArea1(addressBookVO.getAreaProvince());
            orderInfoVO.setSenderArea2(addressBookVO.getAreaCity());
            orderInfoVO.setSenderArea3(addressBookVO.getAreaCounty());
            orderInfoVO.setSenderAddress(addressBookVO.getDetailAddress());
        } else if(addressType == 2) {
            orderInfoVO.setReceiverName(addressBookVO.getName());
            orderInfoVO.setReceiverTel(addressBookVO.getTel());
            orderInfoVO.setReceiverArea1(addressBookVO.getAreaProvince());
            orderInfoVO.setReceiverArea2(addressBookVO.getAreaCity());
            orderInfoVO.setReceiverArea3(addressBookVO.getAreaCounty());
            orderInfoVO.setReceiverAddress(addressBookVO.getDetailAddress());
        }
        request.getSession().setAttribute("order", orderInfoVO);

        return "success";
    }

    @RequestMapping(value = "/showInputAddress", method = RequestMethod.GET)
    public String showInputAddress(ModelMap modelMap,
                                   HttpServletRequest request,
                                   Integer addressType,
                                   String boxDetail) {

        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");
        List<AddressBookVO> addresses = addressBookService.getAddressBooks(userDetail.getUserId());

        OrderInfoVO orderInfoVO = (OrderInfoVO) request.getSession().getAttribute("order");
        if (orderInfoVO == null) {
            orderInfoVO = new OrderInfoVO();
        } else {
            orderInfoVO.setDetail(boxDetail);
        }

        if (addressType == 1) {
            modelMap.put("name", orderInfoVO.getSenderName());
            modelMap.put("tel", orderInfoVO.getSenderTel());
            modelMap.put("areaProvince", StringUtils.isEmpty(orderInfoVO.getSenderArea1()) ? "浙江省" : orderInfoVO.getSenderArea1());
            modelMap.put("areaCity", StringUtils.isEmpty(orderInfoVO.getSenderArea2()) ? "杭州市" : orderInfoVO.getSenderArea2());
            modelMap.put("areaCounty", StringUtils.isEmpty(orderInfoVO.getSenderArea3()) ? "滨江区" : orderInfoVO.getSenderArea3());
            modelMap.put("detailAddress", orderInfoVO.getSenderAddress());
        } else if (addressType == 2) {
            modelMap.put("name", orderInfoVO.getReceiverName());
            modelMap.put("tel", orderInfoVO.getReceiverTel());
            modelMap.put("areaProvince", StringUtils.isEmpty(orderInfoVO.getReceiverArea1()) ? "浙江省" : orderInfoVO.getReceiverArea1());
            modelMap.put("areaCity", StringUtils.isEmpty(orderInfoVO.getReceiverArea2()) ? "杭州市" : orderInfoVO.getReceiverArea2());
            modelMap.put("areaCounty", StringUtils.isEmpty(orderInfoVO.getReceiverArea3()) ? "滨江区" : orderInfoVO.getReceiverArea3());
            modelMap.put("detailAddress", orderInfoVO.getReceiverAddress());
        }

        modelMap.put("addresses", addresses);
        modelMap.put("addressType", addressType);
        return "custom/order/address_input";
    }

    @RequestMapping(value = "/handleInputAddress", method = RequestMethod.POST)
    @ResponseBody
    public String handleInputAddress(HttpServletRequest request,
                                     AddressBookVO addressBookVO,
                                     Integer addressType,
                                     Boolean saveFlag) {
        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");

        OrderInfoVO orderInfoVO = (OrderInfoVO) request.getSession().getAttribute("order");
        if (orderInfoVO == null) {
            orderInfoVO = new OrderInfoVO();
        }
        if (addressType == 1) {
            orderInfoVO.setSenderName(addressBookVO.getName());
            orderInfoVO.setSenderTel(addressBookVO.getTel());
            orderInfoVO.setSenderArea1(addressBookVO.getAreaProvince());
            orderInfoVO.setSenderArea2(addressBookVO.getAreaCity());
            orderInfoVO.setSenderArea3(addressBookVO.getAreaCounty());
            orderInfoVO.setSenderAddress(addressBookVO.getDetailAddress());
        } else if(addressType == 2) {
            orderInfoVO.setReceiverName(addressBookVO.getName());
            orderInfoVO.setReceiverTel(addressBookVO.getTel());
            orderInfoVO.setReceiverArea1(addressBookVO.getAreaProvince());
            orderInfoVO.setReceiverArea2(addressBookVO.getAreaCity());
            orderInfoVO.setReceiverArea3(addressBookVO.getAreaCounty());
            orderInfoVO.setReceiverAddress(addressBookVO.getDetailAddress());
        }
        request.getSession().setAttribute("order", orderInfoVO);

        if (saveFlag != null && saveFlag) {
            addressBookService.saveAddressBook(addressBookVO, userDetail.getUserId());
        }

        return "success";
    }

    /**
     * 显示所有包装箱类型
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/showBoxType", method = RequestMethod.GET)
    public String shwBoxType(ModelMap modelMap, HttpServletRequest request, String boxDetail) {

        OrderInfoVO orderInfoVO = (OrderInfoVO) request.getSession().getAttribute("order");
        if (orderInfoVO != null) {
            orderInfoVO.setDetail(boxDetail);
        }
        request.getSession().setAttribute("order", orderInfoVO);

        List<BoxTypeVO> boxTypeVOS = boxTypeService.getBoxTypes();

        modelMap.put("boxes", boxTypeVOS);

        return "custom/order/box_type_list";
    }

    /**
     * 选择包装箱类型 -- 选择完成后，跳转到下单页面
     * @param request
     * @param boxTypes
     * @return
     */
    @RequestMapping(value = "/selectBoxType", method = RequestMethod.POST)
    @ResponseBody
    public String selectBoxType(HttpServletRequest request, @RequestBody List<BoxTypeVO> boxTypes) {

        OrderInfoVO orderInfoVO = (OrderInfoVO) request.getSession().getAttribute("order");
        if (orderInfoVO == null) {
            orderInfoVO = new OrderInfoVO();
        }
        orderInfoVO.setBoxes(boxTypes);
        request.getSession().setAttribute("order", orderInfoVO);

        return "success";
    }

    /**
     * 创建/修改订单信息
     * @param longitude
     * @param latitude
     * @param request
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    @ResponseBody
    public String createOrder(Double longitude,
                              Double latitude,
                              HttpServletRequest request) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("result", "failed");
        jsonObject.addProperty("reason", "");

        OrderInfoVO orderInfoVO = (OrderInfoVO) request.getSession().getAttribute("order");
        if (orderInfoVO == null) {
            orderInfoVO = new OrderInfoVO();
        }

        CustomUserDetail userDetail = SessionUserUtil.getUserDetail(customInfoService, request);//(CustomUserDetail) request.getSession().getAttribute("user");
        orderInfoVO.setCustomerId(userDetail.getUserId());
        orderInfoVO.setCustomer(userDetail.getUsername());

//        // todo 测试数据
//        longitude = 120.213788;
//        latitude = 30.195393;

        String orderId = orderInfoVO.getOrderId();
        if (StringUtils.isNotEmpty(orderId)) {
            byte orderState = orderService.getOrderState(orderId);
            if (orderState == OrderState.WAITING_ASSIGN.getIndex().byteValue() || orderState == OrderState.WAITING_ASSIGN_FAILED.getIndex().byteValue()) {
                boolean updateRes = orderService.updateOrder(orderInfoVO);
                if (updateRes) {
                    jsonObject.addProperty("result", "update_success");
                    request.getSession().removeAttribute("order");
                }
                jsonObject.addProperty("reason", "");
            } else {
                jsonObject.addProperty("reason", "state_error");
            }
        } else {
            String newOrderId = orderService.createOrder(orderInfoVO);
            if (StringUtils.isNotEmpty(newOrderId)) {
//                orderService.handleTakeIn(newOrderId, longitude, latitude);  // 后台自动分配派货人员
                orderPolicyService.obtainOrder(newOrderId, longitude, latitude);
                request.getSession().removeAttribute("order");
                jsonObject.addProperty("result", "save_success");
            }
        }
        return jsonObject.toString();
    }


    /**
     * 下单失败，重试
     * @param longitude
     * @param latitude
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/retryOrder", method = RequestMethod.GET)
    @ResponseBody
    public String retryOrder(Double longitude,
                             Double latitude,
                             String orderId) {
        boolean res = false;
        if (StringUtils.isNotEmpty(orderId) && longitude != null && latitude != null) {
            orderService.handleTakeIn(orderId, longitude, latitude);  // 后台自动分配派货人员
            res = true;
        }
        return res ? "success" : "failed";
    }

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
    @ResponseBody
    public String deleteOrder(String orderId) {
        boolean res = orderService.deleteOrder(orderId);
        return res ? "success" : "failed";
    }


}
