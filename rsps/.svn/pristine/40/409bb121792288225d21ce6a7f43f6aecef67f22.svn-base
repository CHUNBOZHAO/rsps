package com.izhuixin.rsps.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.izhuixin.rsps.common.dba.BaseAbstractService;
import com.izhuixin.rsps.service.PositionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PositionServiceImpl extends BaseAbstractService implements PositionService {

    /** 位置服务链接地址 https://api.map.baidu.com/geocoder/v2/?address=%s&city=%s&output=json&ak=yuVfoZPpqfa0gdQuPw9s5ZeYKhiGROsg */
    @Value("${baiduGeocodingUrl}")
    private String baiduGeocodingUrl;

    private RestTemplate restTemplate = null;

    /**
     * 通过地址获取位置信息
     * @param address
     * @param city
     * @return
     */
    @Override
    public JsonObject getPositionByAddr(String address, String city) {
        JsonObject joPos = null;
        try {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(1000);// 设置超时
            requestFactory.setReadTimeout(1000);
            if (restTemplate == null) {
                restTemplate = new RestTemplate(requestFactory);
            }
            String url = String.format(baiduGeocodingUrl, address, city);
            String resJsonStr = restTemplate.getForObject(url, String.class);
            JsonObject jo = new JsonParser().parse(resJsonStr).getAsJsonObject();
            if (jo.has("result")) {
                JsonObject joResult = jo.getAsJsonObject("result");
                if (joResult.has("location")) {
                    JsonObject joLocation = joResult.getAsJsonObject("location");
                    joPos = joLocation;
                    logger.info(String.format("位置(%s)的坐标(%s, %s)", address, joPos.get("lng").getAsString(), joPos.get("lat").getAsString()));
                }
            }
        } catch (Exception e) {
            logger.error("获取指定地址的坐标出现异常", e);
        }
        return joPos;
    }

}
