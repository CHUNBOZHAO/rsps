package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.message.OutputMsg;
import com.izhuixin.rsps.model.AppReqPushOpLocation;
import com.izhuixin.rsps.model.AppResBase;
import com.izhuixin.rsps.model.ErrorCode;
import com.izhuixin.rsps.service.AppRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
/**
 * 绑定发送消息的通道
 */
@EnableBinding(OutputMsg.class)
public class AppRestServiceImpl implements AppRestService {

    /**
     *创建一个新的消息通道发送消息
     */
    @Autowired
    @Qualifier(OutputMsg.OUTPUT_OPERLOCATION)
    MessageChannel output_operlocation;

    /**
     * 推送操作人员位置
     * @param appReqPushOpLocation
     * @return
     */
    @Override
    public AppResBase pushOperatorLocation(final AppReqPushOpLocation appReqPushOpLocation){

        AppResBase appResBase = new AppResBase();
        appResBase.setStatus(ErrorCode.OK.getIndex());
        appResBase.setMessage(ErrorCode.OK.getDescr());

        /**
         * 发送消息
         */
        boolean tt = output_operlocation.send(MessageBuilder.withPayload(appReqPushOpLocation).build());

        return appResBase;
    }



}
