package com.izhuixin.rsps.service.impl;

import com.izhuixin.rsps.message.OutputMsg;
import com.izhuixin.rsps.proto.BoxBaseStationInfo;
import com.izhuixin.rsps.proto.BoxData;
import com.izhuixin.rsps.service.BoxUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@EnableBinding(OutputMsg.class)
public class BoxUploadServiceImp implements BoxUploadService {

    @Autowired
    @Qualifier(OutputMsg.OUTPUT_BASESTATION)
    MessageChannel output_basestation;

    @Autowired
    @Qualifier(OutputMsg.OUTPUT_BOXDATA)
    MessageChannel output_boxdata;

    @Override
    public void sendBaseStation(BoxBaseStationInfo boxBaseStationInfo) {


        output_basestation.send(MessageBuilder.withPayload(boxBaseStationInfo).build());
    }

    @Override
    public void sendBoxData(BoxData boxData){
        output_boxdata.send(MessageBuilder.withPayload(boxData).build());
    }
}
