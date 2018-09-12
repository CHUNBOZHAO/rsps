package com.izhuixin.rsps.service;

import com.izhuixin.rsps.common.object.JsApiTicketInfo;
import com.izhuixin.rsps.common.vo.web.WxConfigModel;
import com.izhuixin.rsps.domain.automatic.JsApiTicket;

public interface WeChatService {

    boolean checkTicket();

    boolean updateTicket(JsApiTicket jsApiTicket);

    String generateAccessToken();

    JsApiTicketInfo generateJsApiTicket(String accessToken);

    WxConfigModel getSignature(String url);
}
