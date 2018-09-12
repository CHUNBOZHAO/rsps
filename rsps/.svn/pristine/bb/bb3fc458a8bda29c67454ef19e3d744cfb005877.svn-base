package com.izhuixin.rsps.controller;

import com.izhuixin.rsps.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;

@RestController
@RequestMapping("/")
public class CmdController {

    @Autowired
    private RedisKeyValueTemplate redisKeyValueTemplate;

    @ResponseBody
    @RequestMapping(value = "/cmd/period",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String cmdPeriod(@RequestParam String id, @RequestParam int period, @RequestParam String unit){
        if(unit.toUpperCase().equals("H")){
            period=60*period;
        }
        else if(unit.toUpperCase().equals("D")){
            period=60*24*period;
        }
        else{

        }

        String key=null;
        if(id.contains(":")) {
            key = id.replaceAll(":", "").toUpperCase();
        }
        else if(id.length()>=12){
            key=id.substring(id.length()-12,id.length()).toUpperCase();
        }
        else{
            key = id;
        }
        BoxCmd boxCmd = new BoxCmd();
        boxCmd.setBoxid(key);
        boxCmd.setType((byte)1);
        byte[] cmd = new byte[] {
                (byte)((period >> 8)&0xff),
                (byte) (period & 0xFF)
        };
        boxCmd.setCmd(cmd);
        BoxCmd b = redisKeyValueTemplate.findById(boxCmd.getBoxid(), BoxCmd.class);
        if(null == b) {
            redisKeyValueTemplate.insert(boxCmd.getBoxid(), boxCmd);
        }
        else{
            redisKeyValueTemplate.update(boxCmd.getBoxid(), boxCmd);
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/cmd/para",method = RequestMethod.POST, produces="application/json;charset=UTF-8")
    public String cmdPara(@RequestBody BoxPara boxPara){

        String key=null;
        String id = boxPara.getBoxid();
        if(id.contains(":")) {
            key = id.replaceAll(":", "").toUpperCase();
        }
        else if(id.length()>=12){
            key=id.substring(id.length()-12,id.length()).toUpperCase();
        }
        else{
            key = id;
        }

        BoxCfgs boxCfgs = new BoxCfgs();

        ArrayList<BoxCfg> cfgs = new ArrayList<>();

        BoxCfg cfg = null;
        if(null != boxPara.getCommunicateInterval()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 1);
            cfg.setLen((byte) 2);
            cfg.setCmd(new byte[]{
                    (byte) ((boxPara.getCommunicateInterval() >> 8) & 0xff),
                    (byte) (boxPara.getCommunicateInterval() & 0xFF)});
            cfgs.add(cfg);
        }

        if(null != boxPara.getCycleMode() && null != boxPara.getCycleTimes()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 2);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{(byte) (((boxPara.getCycleMode() & 0xf) << 4) | (boxPara.getCycleTimes() & 0xf))});
            cfgs.add(cfg);
        }

        if(null != boxPara.getBoxStatus()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 3);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getBoxStatus()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getParaUpload()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 4);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getParaUpload()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getStatusUpload()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 5);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getStatusUpload()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getBroadcastInterval()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 6);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getBroadcastInterval()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getCanConnect()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 7);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getCanConnect()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getEnableInterrupt()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 8);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getEnableInterrupt()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getBroadcastPower()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 9);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getBroadcastPower()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getTempInterval()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 10);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getTempInterval()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getVoltageInterval()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 11);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getVoltageInterval()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getRfidWriteInterval()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 12);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getRfidWriteInterval()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getOpenCheckDelay()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 13);
            cfg.setLen((byte) 1);
            cfg.setCmd(new byte[]{boxPara.getOpenCheckDelay()});
            cfgs.add(cfg);
        }

        if(null != boxPara.getVibrateThreshold()) {
            cfg = new BoxCfg();
            cfg.setType((byte) 14);
            cfg.setLen((byte) 2);
            cfg.setCmd(new byte[]{
                    (byte) ((boxPara.getVibrateThreshold() >> 8) & 0xff),
                    (byte) (boxPara.getVibrateThreshold() & 0xFF)});
            cfgs.add(cfg);
        }

        boxCfgs.setBoxCfgs(cfgs);
        boxCfgs.setBoxid(key);


        BoxCfgs b = redisKeyValueTemplate.findById(key, BoxCfgs.class);
        if(null == b) {
            redisKeyValueTemplate.insert(key, boxCfgs);
        }
        else{
            redisKeyValueTemplate.update(key, boxCfgs);
        }
        return "";
    }
}