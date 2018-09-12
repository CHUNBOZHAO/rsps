package com.izhuixin.rsps.receiver;


import com.izhuixin.rsps.domain.BoxDetailInfo;
import com.izhuixin.rsps.message.InputMsg;

import com.izhuixin.rsps.proto.BoxData;
import com.izhuixin.rsps.service.BoxBaseService;
import com.izhuixin.rsps.service.BoxDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.util.Date;

/**
 * 绑定一个接收消息的通道
 */
@EnableBinding(InputMsg.class)
public class MsgReceiver {
    @Autowired
    private BoxBaseService boxBaseService;
    @Autowired
    private BoxDetailService boxDetailService;

    /**
     * 创建日志
     */
    private static Logger logger = LoggerFactory.getLogger(MsgReceiver.class);

    @StreamListener(InputMsg.INPUT_BOXDATA)
    public void boxdataMessage(Object message){

        BoxData bbsInfo = (BoxData)message;

        System.err.println(new Date());
        logger.info("recv: "+bbsInfo);

        BoxDetailInfo boxDetailInfo = new BoxDetailInfo();

        if(bbsInfo.getStatus()!=null){
            boxDetailInfo.setErrcode(bbsInfo.getStatus().getErrcode());
            boxDetailInfo.setTemperature(bbsInfo.getStatus().getTemperature());

            float a1 = ((float)(bbsInfo.getStatus().getVoltage()&0xff))/256;
            float a = ((bbsInfo.getStatus().getVoltage()>>8)&0xff)+a1;
            boxDetailInfo.setVoltage((short) a);
            boxDetailInfo.setVibrationCount(bbsInfo.getStatus().getVibrationCount());
            boxDetailInfo.setClosetimeLast(bbsInfo.getStatus().getClosetimeLast());
            boxDetailInfo.setOpentimeLast(bbsInfo.getStatus().getOpentimeLast());
            boxDetailInfo.setClosetimeLongest(bbsInfo.getStatus().getClosetimeLongest());
            boxDetailInfo.setOpentimeLongest(bbsInfo.getStatus().getOpentimeLongest());
        }

        if(bbsInfo.getParam()!=null){
            boxDetailInfo.setOpencheckdelay(bbsInfo.getParam().getOpencheckdelay());
            boxDetailInfo.setRfidcycle(bbsInfo.getParam().getRfidcycle());
            boxDetailInfo.setVolcycle(bbsInfo.getParam().getVolcycle());
            boxDetailInfo.setTempcycle(bbsInfo.getParam().getTempcycle());
            boxDetailInfo.setBrpower(bbsInfo.getParam().getBrpower());
            boxDetailInfo.setCaninterrupt(bbsInfo.getParam().getCaninterrupt());
            boxDetailInfo.setCanconnect(bbsInfo.getParam().getCanconnect());
            boxDetailInfo.setBrcycle(bbsInfo.getParam().getBrcycle());
        }

        boxDetailInfo.setUuid(bbsInfo.getBoxid());
        boxDetailInfo.setCreateTime(new Date());
//        boxDetailInfo.setCommunicateCount((short) 1);

        boolean b1 = boxDetailService.saveBoxDetail(boxDetailInfo);
        //        logger.info("保存失败"+b1);

        boolean b = boxBaseService.updateCommunicateNum(bbsInfo.getBoxid());

    }

}
