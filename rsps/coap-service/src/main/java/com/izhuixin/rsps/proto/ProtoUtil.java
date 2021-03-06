package com.izhuixin.rsps.proto;

import com.izhuixin.rsps.model.BoxCfgs;
import com.izhuixin.rsps.model.BoxCmd;
import com.izhuixin.rsps.util.Util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class ProtoUtil {
    static public CellsInfo getCellsInfo(byte[] data){
        InputStream in = new ByteArrayInputStream(data);
        DataInputStream a = new DataInputStream(in);
        CellsInfo cellsInfo = new CellsInfo();
        try {
            cellsInfo.mcc = a.readShort();
            cellsInfo.mnc = a.readByte();
            cellsInfo.type = a.readByte();
            byte num = a.readByte();
            for(int i = 0; i < num; i++){
                Cell cell = new Cell();
                cell.lac=a.readShort()&0xffff;
                cell.cellid=a.readInt();
                cell.mciss=(short)(a.readByte()&0xff);
                cellsInfo.cells.add(cell);
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return cellsInfo;
    }

    static public String getDevId(byte[] data){
        return Util.bytesToHexString(data, "").toUpperCase();
    }

    static public String getDevId(byte[] data, String s){
        return Util.bytesToHexString(data, s).toUpperCase();
    }

    static public Status getStatus(byte[] data){
        InputStream in = new ByteArrayInputStream(data);
        DataInputStream a = new DataInputStream(in);
        Status status = new Status();
        try{
            status.opentimeLongest = a.readShort();
            status.closetimeLongest = a.readShort();
            status.opentimeLast = a.readShort();
            status.closetimeLast = a.readShort();
            status.vibrationCount = a.readShort();
            status.voltage = a.readShort();
            status.temperature = a.readByte();
            status.errcode = a.readByte();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return status;
    }

    static public Param getParam(byte[] data){
        InputStream in = new ByteArrayInputStream(data);
        DataInputStream a = new DataInputStream(in);
        Param param = new Param();
        try{
            param.brcycle = a.readByte();
            byte tmp = a.readByte();
            param.canconnect = (byte)((tmp>>7)&0x1);
            param.caninterrupt = (byte)((tmp>>6)&0x1);
            param.brpower = (byte)(tmp&0x3f);
            param.tempcycle = a.readByte();
            param.volcycle = a.readByte();
            param.rfidcycle = a.readByte();
            param.opencheckdelay = a.readByte();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return param;
    }

    static public byte[] setCmd(BoxCmd boxCmd){
        byte[] cmd = new byte[]{boxCmd.getType(), (byte)boxCmd.getCmd().length};
        cmd = Util.combineByteArray(cmd, boxCmd.getCmd());
        return cmd;
    }

    static public byte[] setCfgs(BoxCfgs boxCfgs){
        int len = 0;
        for(int i = 0; i < boxCfgs.getBoxCfgs().size(); i++){
            len += (boxCfgs.getBoxCfgs().get(i).getLen() + 2);
        }
        byte[] cmd = new byte[len];
        int countLength = 0;
        for(int i = 0; i < boxCfgs.getBoxCfgs().size(); i++){
            byte[] b = new byte[]{boxCfgs.getBoxCfgs().get(i).getType(), boxCfgs.getBoxCfgs().get(i).getLen()};
            b = Util.combineByteArray(b, boxCfgs.getBoxCfgs().get(i).getCmd());
            System.arraycopy(b, 0, cmd, countLength, b.length);
            countLength+=(b.length);
        }
        return cmd;
    }
}
