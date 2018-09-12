package com.izhuixin.rsps.service;

import com.google.gson.JsonObject;

public interface PositionService {
    JsonObject getPositionByAddr(String address, String city);
}
