package com.izhuixin.rsps.SqlProvider;


import com.izhuixin.rsps.common.vo.app.AppReqBoxes;

public class SqlProvider {
    private String getEncode(String code){
        if(code == null || code.length() <= 0){
            return "jhyt_";
        }
        return code+"_";
    }

    public String queryBoxes(AppReqBoxes req){
        StringBuffer sql = new StringBuffer("SELECT * FROM rsps_"+getEncode(req.getEntCode())+"box_info WHERE 1=1 ");

        if(req.getOperatorId() != null){
            sql.append(" and operator_id="+"\""+req.getOperatorId()+"\"");
        }
        if(req.getBoxStatus() != null && req.getBoxStatus().size() > 0){
            String valuss = org.apache.commons.lang.StringUtils.join(req.getBoxStatus().toArray(),",");
            sql.append(" and status in("+valuss+")");
        }
        return sql.toString();
    }
}
