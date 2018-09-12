package com.izhuixin.rsps.service;


import com.izhuixin.rsps.model.CoapModel.BaseStation;

public interface BaseStationService {

   /**
    * 获取基站信息
    * @param ID
    * @return
    */
   BaseStation getBaseStation(String ID);

   void updateBoxLocation(String boxid,Double lng, Double lat);
}
