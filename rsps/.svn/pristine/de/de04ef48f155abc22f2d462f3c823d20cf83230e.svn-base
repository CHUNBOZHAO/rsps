package com.izhuixin.rsps.task;

import com.izhuixin.rsps.common.object.JsApiTicketInfo;
import com.izhuixin.rsps.domain.automatic.JsApiTicket;
import com.izhuixin.rsps.service.WeChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * 微信js调用相关
 */
@Component
@EnableScheduling
public class JsApiTicketTask {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WeChatService weChatService;

    /**
     * 生成微信js访问相关信息
     */
    @PostConstruct
    @Scheduled(cron = "0 0/30 * * * ?")
    public void generateJspApiInfo() {
        try {
            if (weChatService.checkTicket()) {
                String accessToken = weChatService.generateAccessToken();
                logger.info(String.format("accessToken is %s", accessToken));
                JsApiTicketInfo jsApiTicketInfo = weChatService.generateJsApiTicket(accessToken);
                logger.info(String.format("jsapi_ticket is %s", jsApiTicketInfo.getTicket()));
                JsApiTicket jsApiTicket = new JsApiTicket();
                jsApiTicket.setExpiresIn(7200);
                jsApiTicket.setJsapiTicket(jsApiTicketInfo.getTicket());
                jsApiTicket.setUpdateTime(new Date());
                weChatService.updateTicket(jsApiTicket);
            } else {
                logger.info("Do Not Update!");
            }
        } catch (Exception e) {
            logger.error("获取jsapi_ticket出现异常", e);
        }

    }

}
