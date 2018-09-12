package com.izhuixin.rsps.service;

import com.izhuixin.rsps.proto.BoxBaseStationInfo;
import com.izhuixin.rsps.proto.BoxData;

public interface BoxUploadService {

   void sendBaseStation(BoxBaseStationInfo boxBaseStationInfo);

   void sendBoxData(BoxData boxData);
}
