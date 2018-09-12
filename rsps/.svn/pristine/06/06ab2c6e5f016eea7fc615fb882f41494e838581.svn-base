package com.izhuixin.rsps.util;

import com.google.common.collect.Maps;
import org.joda.time.DateTime;

import java.util.Map;

public class LocationCache {

    private static Map<String,String> locationCache = Maps.newHashMap();

    private static DateTime lastClearTime = DateTime.now();

    /**
     * 检查是否有重复
     * @param orderId
     * @param boxId
     * @param la
     * @param lo
     * @return
     */
    public static boolean checkRepeat(String orderId, String boxId, Double la, Double lo) {
        if (DateTime.now().getMillis() - lastClearTime.getMillis() > 1000 * 60 * 60 * 24) { // 大于24小时，清理
            locationCache.clear();
            lastClearTime = DateTime.now();
        }
        String key = orderId + boxId;
        String value = String.valueOf(la) + String.valueOf(lo);
        if (locationCache.containsKey(key)) {
            if (locationCache.get(key).equals(value)) {  // 重复
                return true;
            } else {
                locationCache.put(key, value);
            }
        } else {
            locationCache.put(key, value);
        }
        return false;
    }

}
