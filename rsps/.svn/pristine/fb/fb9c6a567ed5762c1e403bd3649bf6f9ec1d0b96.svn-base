package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.common.object.JsApiTicketInfo;
import com.izhuixin.rsps.common.vo.web.WxConfigModel;
import com.izhuixin.rsps.domain.automatic.JsApiTicket;
import com.izhuixin.rsps.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/wxData/ticket")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    @RequestMapping("/check")
    public boolean checkTicket() {
        return weChatService.checkTicket();
    }

    @RequestMapping("/update")
    public boolean updateTicket(@RequestBody JsApiTicket jsApiTicket) {
        return weChatService.updateTicket(jsApiTicket);
    }

    @RequestMapping("/accessToken/generate")
    public String generateAccessToken() {
        return weChatService.generateAccessToken();
    }

    @RequestMapping("/jsApiticket/generate")
    public JsApiTicketInfo generateJsApiTicket(String accessToken) {
        return weChatService.generateJsApiTicket(accessToken);
    }

    @RequestMapping("/signature/get")
    public WxConfigModel getSignature(String url) {
        return weChatService.getSignature(url);
    }
}
