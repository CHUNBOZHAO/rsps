package com.izhuixin.rsps.receiver;

import com.izhuixin.rsps.LocationService;
import com.izhuixin.rsps.message.InputMsg;
import com.izhuixin.rsps.model.AppReqPushOpLocation;
import com.izhuixin.rsps.model.CoapModel.BaseStation;
import com.izhuixin.rsps.proto.BoxBaseStationInfo;
import com.izhuixin.rsps.service.AppLoctionService;
import com.izhuixin.rsps.service.BaseStationService;
import com.izhuixin.rsps.util.MapUtil;
import com.izhuixin.rsps.util.Triangulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import java.awt.geom.Point2D;

/**
 * 绑定一个接收消息的通道
 */
@EnableBinding(InputMsg.class)
public class MsgReceiver {


    @Autowired
    private AppLoctionService appLoctionService;

    @Autowired
    private BaseStationService baseStationService;
    /**
     * 创建日志
     */
    private static Logger logger = LoggerFactory.getLogger(LocationService.class);

    @StreamListener(InputMsg.INPUT_BASESTATION)
    public void basestationMessage(Object message){


        BoxBaseStationInfo bbsInfo = (BoxBaseStationInfo)message;
        Triangulation triangulation = new Triangulation(bbsInfo.getCells().size());

        /* 查询数据库并计算定位 */
        for (int i = 0; i < bbsInfo.getCells().size(); i++) {
            /* 拼装ID */
            String baseId = String.format("%0"+4+"d", bbsInfo.getMcc()*10)
                    + String.format("%0"+2+"d", bbsInfo.getMnc())
                    + String.format("%0"+5+"d", bbsInfo.getCells().get(i).getLac())
                    + String.format("%0"+9+"d", bbsInfo.getCells().get(i).getCellid());
            //根据id从数据库中查询baseStation
            BaseStation baseStation = baseStationService.getBaseStation(baseId);
            if(null != baseStation) {
                triangulation.add(new Point2D.Double(baseStation.getLnga(), baseStation.getLata()), bbsInfo.getCells().get(i).getMciss() * -1);
                //logger.info("cell location: " + baseStation.getLnga()+", "+baseStation.getLata());
            }
        }
        Point2D result = triangulation.location();

        double[] bd = MapUtil.gaoDeToBaidu(result.getX(), result.getY());

        try {
            baseStationService.updateBoxLocation(bbsInfo.getBoxid(), bd[0], bd[1]);
        }catch (Exception e){
            logger.error("update location fail! id:  " + bbsInfo.getBoxid());
        }
        logger.info("Calculate location: " + result.getX()+", "+result.getY());

        logger.info(""+bbsInfo);
    }

    @StreamListener(InputMsg.INPUT_OPERLOCATION)
    public void operlocationMessage(Object message){
        AppReqPushOpLocation location = null;
        location = (AppReqPushOpLocation)message;
        String ack = appLoctionService.pushOperatorLocation(location);
        logger.info("Received "+location+"ack " +ack);
    }



}
