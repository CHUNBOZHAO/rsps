package com.izhuixin.rsps.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.EnterpriseService;
import com.izhuixin.rsps.constants.CommonOrderRefuseReason;
import com.izhuixin.rsps.constants.CommonOrderStatus;
import com.izhuixin.rsps.constants.CommonSendAckStatus;
import com.izhuixin.rsps.domain.Order;
import com.izhuixin.rsps.service.feign.OrderFeignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Date;

@RestController
public class EnterpriseController {


    @Autowired
    private OrderFeignService orderFeignService;
    private Logger logger = LoggerFactory.getLogger(EnterpriseService.class);

    @RequestMapping("/order/trucking/{entCode}")
    public String truckingOrder(HttpServletRequest request, @PathVariable("entCode") String entCode)throws Exception{


        JsonObject jsonObject = new JsonObject();
        if(entCode.equals("zx")||entCode.equals("jhyt")||entCode.equals("zm")){
            String content = request.getParameter("content");
            // 解码
            String content1 = URLDecoder.decode(content,"UTF-8");
            //json字符串转对象
            Gson gson = new Gson();
            Order order = gson.fromJson(content1,Order.class);
            order.setCreateTime(new Date());
            order.setEntCode(entCode);
            order.setOrderStatus(CommonOrderStatus.UNSIGN.getIndex());
            order.setSendAckStatus(CommonSendAckStatus.SUCCESS.getIndex());
            order.setRefusedCount("0");
            order.setRefusedReason(String.valueOf(CommonOrderRefuseReason.NONE.getIndex()));

            boolean flag = orderFeignService.saveOrder(order);
            if(flag){
                jsonObject.addProperty("Success",true);
                jsonObject.addProperty("Jobno",order.getJobno());
            }else{
                jsonObject.addProperty("Success",false);
                jsonObject.addProperty("Error","保存失败");
            }
        }
        else if(entCode.equals("yt")){
            jsonObject.addProperty("status",0);
            jsonObject.addProperty("message","成功");


        }
        else {
            jsonObject.addProperty("Success",false);
            jsonObject.addProperty("Error","保存失败");
        }
        return jsonObject.toString();
    }
}
