package com.izhuixin.rsps.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.izhuixin.rsps.constant.EfficiencyType;
import com.izhuixin.rsps.dao.BoxDao;
import com.izhuixin.rsps.dao.DataDao;
import com.izhuixin.rsps.dao.EnterpriseDao;
import com.izhuixin.rsps.domain.*;
import com.netflix.discovery.converters.Auto;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class DataEfficiencyServiceImpl implements DataEfficiencyService {

    @Autowired
    private DataDao dataDao;

    @Autowired
    private EnterpriseDao enterpriseDao;
    @Autowired
    private BoxDao boxDao;



    @Override
    public List<DataEfficiency> getEfficiency(String entId, Integer type, String beginTime, String endTime) {
        return dataDao.getEfficiency(entId,type,beginTime,endTime);
    }

    @Override
    public EfficiencyVO getEfficiencyVO(String entId) {
        EfficiencyVO efficiencyVO = new EfficiencyVO();
        try{
            DateTime nowTime = DateTime.now();
            List<DataEfficiency> efficiencyCurrentMonth = dataDao.getEfficiency(entId, 1, nowTime.minusDays(30).toString("yyyy-MM-dd HH:mm:ss"), nowTime.toString("yyyy-MM-dd HH:mm:ss"));
            List<DataEfficiency> efficiencyCurrentYear = dataDao.getEfficiency(entId, 3, nowTime.minusMonths(12).toString("yyyy-MM-dd HH:mm:ss"), nowTime.toString("yyyy-MM-dd HH:mm:ss"));
            List<DataEfficiency> efficiencyYear = dataDao.getEfficiency(entId, 4, nowTime.minusYears(1).toString("yyyy-MM-dd HH:mm:ss"), nowTime.toString("yyyy-MM-dd HH:mm:ss"));
            List<DataEfficiency> efficiencyMonth = dataDao.getEfficiency(entId, 3, nowTime.minusMonths(1).toString("yyyy-MM-dd HH:mm:ss"), nowTime.toString("yyyy-MM-dd HH:mm:ss"));

            //本月闲置率
            float idleRateTotal = 0;
            float turnRateTotal = 0;
            float loseRateTotal = 0;
            float overdueRateTotal = 0;
            for (DataEfficiency dataEfficiency: efficiencyCurrentMonth) {
                float idleRate = (float) dataEfficiency.getIdleRate();
                float turnRate = (float)dataEfficiency.getTurnoverRate();
                float loseRate = (float)dataEfficiency.getLoseRate();
                float overdueRate = (float)dataEfficiency.getOverdueRate();
                idleRateTotal += idleRate;
                turnRateTotal += turnRate;
                loseRateTotal += loseRate;
                overdueRateTotal +=overdueRate;
            }
            float idleRateTotalYear = 0;
            float turnRateTotalYear = 0;
            float loseRateTotalYear = 0;
            float overdueRateTotalYear = 0;
            //本年闲置率
            for (DataEfficiency dataEfficiency1:efficiencyCurrentYear) {
                float idleRateYear = (float) dataEfficiency1.getIdleRate();
                float turnRateYear = (float)dataEfficiency1.getTurnoverRate();
                float loseRateYear = (float)dataEfficiency1.getLoseRate();
                float overdueRateYear = (float)dataEfficiency1.getOverdueRate();

                idleRateTotalYear += idleRateYear;
                turnRateTotalYear += turnRateYear;
                loseRateTotalYear += loseRateYear;
                overdueRateTotalYear +=overdueRateYear;

            }
            float idleCompareYear = 0;
            float idleCompareMonth = 0;
            float turnCompareYear=0;
            float turnCompareMonth=0;
            float loseCompareYear=0;
            float loseCompareMonth=0;
            float overdueCompareYear=0;
            float overdueCompareMonth=0;
            if(efficiencyYear.size()!=0&&efficiencyMonth.size()!=0){
                //同比，环比
                idleCompareYear   = (float) (idleRateTotalYear / efficiencyCurrentYear.size() - efficiencyYear.get(0).getIdleRate()) / (idleRateTotalYear / efficiencyCurrentYear.size());
                idleCompareMonth  = (float)(idleRateTotal/efficiencyCurrentMonth.size() - efficiencyMonth.get(0).getIdleRate()) / (idleRateTotal/efficiencyCurrentMonth.size());

                turnCompareYear  = (float)(turnRateTotalYear / efficiencyCurrentYear.size() - efficiencyYear.get(0).getTurnoverRate()) / (turnRateTotalYear / efficiencyCurrentYear.size());
                turnCompareMonth  = (float)(turnRateTotal/efficiencyCurrentMonth.size() - efficiencyMonth.get(0).getTurnoverRate()) / (turnRateTotal/efficiencyCurrentMonth.size());

                loseCompareYear  = (float)(loseRateTotalYear / efficiencyCurrentYear.size() - efficiencyYear.get(0).getLoseRate()) / (loseRateTotalYear / efficiencyCurrentYear.size());
                loseCompareMonth = (float)(loseRateTotal/efficiencyCurrentMonth.size() - efficiencyMonth.get(0).getLoseRate()) / (loseRateTotal/efficiencyCurrentMonth.size());

                overdueCompareYear   = (float)(overdueRateTotalYear / efficiencyCurrentYear.size() - efficiencyYear.get(0).getOverdueRate()) / (overdueRateTotalYear / efficiencyCurrentYear.size());
                overdueCompareMonth  = (float)(overdueRateTotal/efficiencyCurrentMonth.size() - efficiencyMonth.get(0).getOverdueRate()) / (overdueRateTotal/efficiencyCurrentMonth.size());
            }


            //数量


            //闲置率，周转率，遗失率，过期率
            DecimalFormat format = new DecimalFormat("0.00%");
            efficiencyVO.setIdleRate(format.format(idleRateTotal/efficiencyCurrentMonth.size()));
            efficiencyVO.setIdleCompareYear(format.format(idleCompareYear));
            efficiencyVO.setIdleCompareMonth(format.format(idleCompareMonth));

            efficiencyVO.setTurnRate(format.format(turnRateTotal/efficiencyCurrentMonth.size()));
            efficiencyVO.setTurnCompareYear(format.format(turnCompareYear));
            efficiencyVO.setTurnCompareMonth(format.format(turnCompareMonth));

            efficiencyVO.setLoseRate(format.format(loseRateTotal/efficiencyCurrentMonth.size()));
            efficiencyVO.setLoseCompareYear(format.format(loseCompareYear));
            efficiencyVO.setLoseCompareMonth(format.format(loseCompareMonth));

            efficiencyVO.setOverdueRate(format.format(overdueRateTotal/efficiencyCurrentMonth.size()));
            efficiencyVO.setOverdueCompareYear(format.format(overdueCompareYear));
            efficiencyVO.setOverdueCompareMonth(format.format(overdueCompareMonth));



        }catch (Exception e){
            e.printStackTrace();
        }


        return efficiencyVO;
    }

    @Override
    public List<EntRankingVO> getEntRanking(Integer effiType,String entId,String type) {

        List<EntRankingVO> rankingVOS = new ArrayList<>();

        List<EnterpriseInfo> enterpriseInfo = enterpriseDao.getAllEnterprises();

        List<DataEfficiency> efficiencyEnt = new ArrayList<>();

        if("0".equals(entId)){

            for (EnterpriseInfo enterpriseAndUserInfo1 : enterpriseInfo) {

                if(type!=null){
                    if(type.equals("1")){
                        efficiencyEnt = dataDao.getEfficiency(enterpriseAndUserInfo1.getEntId(), Integer.parseInt(type), new DateTime().minusDays(30).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
                    }else if(type.equals("2")){
                        efficiencyEnt = dataDao.getEfficiency(enterpriseAndUserInfo1.getEntId(), Integer.parseInt(type), new DateTime().minusMonths(3).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
                    }else if(type.equals("3")){
                        efficiencyEnt = dataDao.getEfficiency(enterpriseAndUserInfo1.getEntId(), Integer.parseInt(type), new DateTime().minusMonths(12).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
                    }else if(type.equals("4")){
                        efficiencyEnt = dataDao.getEfficiency(enterpriseAndUserInfo1.getEntId(), Integer.parseInt(type), new DateTime().minusYears(4).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
                    }
                }

                double idletotal = 0;
                double turntotal = 0;
                double losetotal = 0;
                double overduetotal = 0;

                for (DataEfficiency dataEfficiencyEnt: efficiencyEnt) {

                    double m = dataEfficiencyEnt.getIdleRate();
                    double n = dataEfficiencyEnt.getTurnoverRate();
                    double j = dataEfficiencyEnt.getLoseRate();
                    double i = dataEfficiencyEnt.getOverdueRate();

                    idletotal+=m;
                    turntotal+=n;
                    losetotal+=j;
                    overduetotal+=i;
                }

                EntRankingVO entRankingVO = new EntRankingVO();
                if(EfficiencyType.IDLE_RATE.getIndex().equals(effiType)){
                    DecimalFormat format = new DecimalFormat("0.00%");
                    entRankingVO.setEntId(enterpriseAndUserInfo1.getEntId());
                    entRankingVO.setEntName(enterpriseAndUserInfo1.getEntName());
                    entRankingVO.setRate(idletotal/efficiencyEnt.size());
                    entRankingVO.setRateVO(format.format(idletotal/efficiencyEnt.size()));

                }else if(EfficiencyType.TURN_RATE.getIndex().equals(effiType)){
                    DecimalFormat format = new DecimalFormat("0.00%");
                    entRankingVO.setEntId(enterpriseAndUserInfo1.getEntId());
                    entRankingVO.setEntName(enterpriseAndUserInfo1.getEntName());
                    entRankingVO.setRate(turntotal/efficiencyEnt.size());
                    entRankingVO.setRateVO(format.format(turntotal/efficiencyEnt.size()));

                }else if(EfficiencyType.LOSE_RATE.getIndex().equals(effiType)){
                    DecimalFormat format = new DecimalFormat("0.00%");
                    entRankingVO.setEntId(enterpriseAndUserInfo1.getEntId());
                    entRankingVO.setEntName(enterpriseAndUserInfo1.getEntName());
                    entRankingVO.setRate(losetotal/efficiencyEnt.size());
                    entRankingVO.setRateVO(format.format(losetotal/efficiencyEnt.size()));

                }else if(EfficiencyType.OVERDUE_RATE.getIndex().equals(effiType)){
                    DecimalFormat format = new DecimalFormat("0.00%");
                    entRankingVO.setEntId(enterpriseAndUserInfo1.getEntId());
                    entRankingVO.setEntName(enterpriseAndUserInfo1.getEntName());
                    entRankingVO.setRate(overduetotal/efficiencyEnt.size());
                    entRankingVO.setRateVO(format.format(overduetotal/efficiencyEnt.size()));
                }else {
                    DecimalFormat format = new DecimalFormat("0.00%");
                    entRankingVO.setEntId(enterpriseAndUserInfo1.getEntId());
                    entRankingVO.setEntName(enterpriseAndUserInfo1.getEntName());
                    entRankingVO.setRate(idletotal/efficiencyEnt.size());
                    entRankingVO.setRateVO(format.format(idletotal/efficiencyEnt.size()));
                }

                rankingVOS.add(entRankingVO);

            }

        }else {
            if(type!=null){
                if(type.equals("1")){
                    efficiencyEnt = dataDao.getEfficiency(entId, Integer.parseInt(type), new DateTime().minusDays(30).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
                }else if(type.equals("2")){
                    efficiencyEnt = dataDao.getEfficiency(entId, Integer.parseInt(type), new DateTime().minusMonths(3).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
                }else if(type.equals("3")){
                    efficiencyEnt = dataDao.getEfficiency(entId, Integer.parseInt(type), new DateTime().minusMonths(12).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
                }else if(type.equals("4")){
                    efficiencyEnt = dataDao.getEfficiency(entId, Integer.parseInt(type), new DateTime().minusYears(4).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
                }
            }

            double idletotal = 0;
            double turntotal = 0;
            double losetotal = 0;
            double overduetotal = 0;

            for (DataEfficiency dataEfficiencyEnt: efficiencyEnt) {

                double m = dataEfficiencyEnt.getIdleRate();
                double n = dataEfficiencyEnt.getTurnoverRate();
                double j = dataEfficiencyEnt.getLoseRate();
                double i = dataEfficiencyEnt.getOverdueRate();

                idletotal+=m;
                turntotal+=n;
                losetotal+=j;
                overduetotal+=i;
            }

            EntRankingVO entRankingVO = new EntRankingVO();
            if(EfficiencyType.IDLE_RATE.getIndex().equals(effiType)){
                DecimalFormat format = new DecimalFormat("0.00%");
                entRankingVO.setEntId(entId);
                entRankingVO.setEntName(enterpriseDao.getEnterpriseInfoById(entId).getEntName());
                entRankingVO.setRate(idletotal/efficiencyEnt.size());
                entRankingVO.setRateVO(format.format(idletotal/efficiencyEnt.size()));

            }else if(EfficiencyType.TURN_RATE.getIndex().equals(effiType)){
                DecimalFormat format = new DecimalFormat("0.00%");
                entRankingVO.setEntId(entId);
                entRankingVO.setEntName(enterpriseDao.getEnterpriseInfoById(entId).getEntName());
                entRankingVO.setRate(turntotal/efficiencyEnt.size());
                entRankingVO.setRateVO(format.format(turntotal/efficiencyEnt.size()));

            }else if(EfficiencyType.LOSE_RATE.getIndex().equals(effiType)){
                DecimalFormat format = new DecimalFormat("0.00%");
                entRankingVO.setEntId(entId);
                entRankingVO.setEntName(enterpriseDao.getEnterpriseInfoById(entId).getEntName());
                entRankingVO.setRate(losetotal/efficiencyEnt.size());
                entRankingVO.setRateVO(format.format(losetotal/efficiencyEnt.size()));

            }else if(EfficiencyType.OVERDUE_RATE.getIndex().equals(effiType)){
                DecimalFormat format = new DecimalFormat("0.00%");
                entRankingVO.setEntId(entId);
                entRankingVO.setEntName(enterpriseDao.getEnterpriseInfoById(entId).getEntName());
                entRankingVO.setRate(overduetotal/efficiencyEnt.size());
                entRankingVO.setRateVO(format.format(overduetotal/efficiencyEnt.size()));
            }else {
                DecimalFormat format = new DecimalFormat("0.00%");
                entRankingVO.setEntId(entId);
                entRankingVO.setEntName(enterpriseDao.getEnterpriseInfoById(entId).getEntName());
                entRankingVO.setRate(idletotal/efficiencyEnt.size());
                entRankingVO.setRateVO(format.format(idletotal/efficiencyEnt.size()));
            }

            rankingVOS.add(entRankingVO);

        }

        Collections.sort(rankingVOS, new Comparator<EntRankingVO>(){

            public int compare(EntRankingVO o1, EntRankingVO o2) {

                if(o1.getRate()>o2.getRate()){
                    return -1;
                }
                if(o1.getRate() == o2.getRate()){
                    return 0;
                }
                return 1;
            }
        });

        return rankingVOS;
    }

    @Override
    public String getChartData(Integer efficType,String entId,String type) {

        JsonObject jo = new JsonObject();

        JsonArray xAxisDataJa = new JsonArray();
        JsonArray seriesDataJa = new JsonArray();
        JsonArray titleDataJa = new JsonArray();
        JsonArray yAxisDataJa = new JsonArray();

        JsonArray idleJa = new JsonArray();
        JsonArray turnJa = new JsonArray();
        JsonArray loseJa = new JsonArray();
        JsonArray overdueJa = new JsonArray();

        int time;
        List<DataEfficiency> efficiency = null;
        if(type!=null){
            if(type.equals("1")){
                efficiency = dataDao.getEfficiency(entId, Integer.parseInt(type), new DateTime().minusDays(30).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
            }else if(type.equals("2")){
                efficiency = dataDao.getEfficiency(entId, Integer.parseInt(type), new DateTime().minusMonths(3).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
            }else if(type.equals("3")){
                efficiency = dataDao.getEfficiency(entId, Integer.parseInt(type), new DateTime().minusMonths(12).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
            }else if(type.equals("4")){
                efficiency = dataDao.getEfficiency(entId, Integer.parseInt(type), new DateTime().minusYears(4).toString("yyyy-MM-dd"), new DateTime().toString("yyyy-MM-dd"));
            }
        }


        if(efficiency!=null){

            for (DataEfficiency dataEfficiency:efficiency) {
                double idleRate = dataEfficiency.getIdleRate();
                double turnRate = dataEfficiency.getTurnoverRate();
                double loseRate = dataEfficiency.getLoseRate();
                double overdueRate = dataEfficiency.getOverdueRate();


                xAxisDataJa.add(new DateTime(dataEfficiency.getGenerateDate()).toString("MM-dd"));
                idleJa.add((float)idleRate*100);
                turnJa.add((float)turnRate*100);
                loseJa.add((float)loseRate*100);
                overdueJa.add((float)overdueRate *100);
            }
        }


//        // 平均值对象
//        JsonObject averageDataJo = new JsonObject();
//        JsonArray averageJaItem = new JsonArray();
//        JsonObject averageJoItem = new JsonObject();
//        averageJoItem.addProperty("type", "average");
//        averageJoItem.addProperty("name", "平均值");
//        averageJaItem.add(averageJoItem);
//        averageDataJo.add("data", averageJaItem);

        // 闲置
        JsonObject seriesIdleDataJo = new JsonObject();
        seriesIdleDataJo.addProperty("name", "闲置率");
        seriesIdleDataJo.addProperty("type", "bar");
        seriesIdleDataJo.addProperty("barWidth", "45");
        seriesIdleDataJo.add("data", idleJa);
//        seriesIdleDataJo.add("markLine", averageDataJo);

        //周转率
        JsonObject seriesTurnDataJo = new JsonObject();
        seriesTurnDataJo.addProperty("name", "周转率");
        seriesTurnDataJo.addProperty("type", "bar");
        seriesTurnDataJo.addProperty("barWidth", "45");
        seriesTurnDataJo.add("data", turnJa);
//        seriesTurnDataJo.add("markLine", averageDataJo);

        // 遗失率
        JsonObject seriesLoseDataJo = new JsonObject();
        seriesLoseDataJo.addProperty("name", "遗失率");
        seriesLoseDataJo.addProperty("type", "bar");
        seriesLoseDataJo.addProperty("barWidth", "45");
        seriesLoseDataJo.add("data", loseJa);
//        seriesLoseDataJo.add("markLine", averageDataJo);

        // 过期率
        JsonObject seriesOverdueJo = new JsonObject();
        seriesOverdueJo.addProperty("name", "过期率");
        seriesOverdueJo.addProperty("type", "bar");
        seriesOverdueJo.addProperty("barWidth", "45");
        seriesOverdueJo.add("data", overdueJa);
//        seriesOverdueJo.add("markLine", averageDataJo);

        if(EfficiencyType.IDLE_RATE.getIndex()==efficType){
            seriesDataJa.add(seriesIdleDataJo);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("text","包装箱闲置率");
            jsonObject.addProperty("x","center");
            titleDataJa.add(jsonObject);

            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("name","闲置率");
            yAxisDataJa.add(jsonObject1);

        }else if(EfficiencyType.TURN_RATE.getIndex()==efficType){
            seriesDataJa.add(seriesTurnDataJo);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("text","包装箱周转率");
            jsonObject.addProperty("x","center");
            titleDataJa.add(jsonObject);

            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("name","周转率");
            yAxisDataJa.add(jsonObject1);

        }else if(EfficiencyType.LOSE_RATE.getIndex()==efficType){
            seriesDataJa.add(seriesLoseDataJo);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("text","包装箱遗失率");
            jsonObject.addProperty("x","center");
            titleDataJa.add(jsonObject);

            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("name","遗失率");
            yAxisDataJa.add(jsonObject1);

        }else if(EfficiencyType.OVERDUE_RATE.getIndex()==efficType){
            seriesDataJa.add(seriesOverdueJo);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("text","包装箱过期率");
            jsonObject.addProperty("x","center");
            titleDataJa.add(jsonObject);

            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("name","过期率");
            yAxisDataJa.add(jsonObject1);

        }else{
            seriesDataJa.add(seriesIdleDataJo);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("text","包装箱闲置率");
            jsonObject.addProperty("x","center");
            titleDataJa.add(jsonObject);

            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.addProperty("name","闲置率");
            yAxisDataJa.add(jsonObject1);
        }

        jo.add("xAxisData", xAxisDataJa);
        jo.add("seriesData", seriesDataJa);
        jo.add("titleData",titleDataJa);
        jo.add("yAxisData",yAxisDataJa);

        return jo.toString();
    }

    @Override
    public String getChartDataByTime(Integer effiType, String entId, String type, String beginTime, String endTime) {
        JsonObject jo = new JsonObject();

        JsonArray xAxisDataJa = new JsonArray();
        JsonArray seriesDataJa = new JsonArray();

        JsonArray idleJa = new JsonArray();
        JsonArray turnJa = new JsonArray();
        JsonArray loseJa = new JsonArray();
        JsonArray overdueJa = new JsonArray();

        int time;
        List<DataEfficiency> efficiency = null;
        if(type!=null){
            if(type.equals("1")){
                efficiency = dataDao.getEfficiency(entId, Integer.parseInt(type), beginTime, endTime);
            }else if(type.equals("2")){
                efficiency = dataDao.getEfficiency(entId, Integer.parseInt(type), beginTime, endTime);
            }else if(type.equals("3")){
                efficiency = dataDao.getEfficiency(entId, Integer.parseInt(type), beginTime, endTime);
            }else if(type.equals("4")){
                efficiency = dataDao.getEfficiency(entId, Integer.parseInt(type), beginTime, endTime);
            }
        }


        if(efficiency!=null){

            for (DataEfficiency dataEfficiency:efficiency) {
                double idleRate = dataEfficiency.getIdleRate();
                double turnRate = dataEfficiency.getTurnoverRate();
                double loseRate = dataEfficiency.getLoseRate();
                double overdueRate = dataEfficiency.getOverdueRate();


                xAxisDataJa.add(new DateTime(dataEfficiency.getGenerateDate()).toString("MM-dd"));
                idleJa.add(idleRate*100);
                turnJa.add(turnRate*100);
                loseJa.add(loseRate*100);
                overdueJa.add(overdueRate *100);
            }
        }


        // 平均值对象
        JsonObject averageDataJo = new JsonObject();
        JsonArray averageJaItem = new JsonArray();
        JsonObject averageJoItem = new JsonObject();
        averageJoItem.addProperty("type", "average");
        averageJoItem.addProperty("name", "平均值");
        averageJaItem.add(averageJoItem);
        averageDataJo.add("data", averageJaItem);

        // 闲置
        JsonObject seriesIdleDataJo = new JsonObject();
        seriesIdleDataJo.addProperty("name", "闲置率");
        seriesIdleDataJo.addProperty("type", "bar");
        seriesIdleDataJo.add("data", idleJa);
        seriesIdleDataJo.add("markLine", averageDataJo);

        //周转率
        JsonObject seriesTurnDataJo = new JsonObject();
        seriesTurnDataJo.addProperty("name", "周转率");
        seriesTurnDataJo.addProperty("type", "bar");
        seriesTurnDataJo.add("data", turnJa);
        seriesTurnDataJo.add("markLine", averageDataJo);

        // 遗失率
        JsonObject seriesLoseDataJo = new JsonObject();
        seriesLoseDataJo.addProperty("name", "遗失率");
        seriesLoseDataJo.addProperty("type", "bar");
        seriesLoseDataJo.add("data", loseJa);
        seriesLoseDataJo.add("markLine", averageDataJo);

        // 过期率
        JsonObject seriesOverdueJo = new JsonObject();
        seriesOverdueJo.addProperty("name", "过期率");
        seriesOverdueJo.addProperty("type", "bar");
        seriesOverdueJo.add("data", overdueJa);
        seriesOverdueJo.add("markLine", averageDataJo);

        if(EfficiencyType.IDLE_RATE.getIndex()==effiType){
            seriesDataJa.add(seriesIdleDataJo);
        }else if(EfficiencyType.TURN_RATE.getIndex()==effiType){
            seriesDataJa.add(seriesTurnDataJo);
        }else if(EfficiencyType.LOSE_RATE.getIndex()==effiType){
            seriesDataJa.add(seriesLoseDataJo);
        }else if(EfficiencyType.OVERDUE_RATE.getIndex()==effiType){
            seriesDataJa.add(seriesOverdueJo);
        }else{
            seriesDataJa.add(seriesIdleDataJo);
        }

        jo.add("xAxisData", xAxisDataJa);
        jo.add("seriesData", seriesDataJa);

        return jo.toString();
    }
}
