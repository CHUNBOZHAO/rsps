package com.izhuixin.rsps.common.vo.app;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * APP推送箱子状态更新信息
 */
public class AppReqPushBoxInfo extends AppReqBase {

    /**
     * 推送的包装箱数据
     */
    private List<AppPushBoxInfo> data = Lists.newArrayList();

    public List<AppPushBoxInfo> getData() {
        return data;
    }

    public void setData(List<AppPushBoxInfo> data) {
        this.data = data;
    }
}
