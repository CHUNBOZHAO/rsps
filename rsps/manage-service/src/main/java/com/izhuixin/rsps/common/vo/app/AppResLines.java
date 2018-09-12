package com.izhuixin.rsps.common.vo.app;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 请求线路回复消息体
 */
public class AppResLines extends AppResBase {

    List<AppLinesEntity> lines = Lists.newArrayList();

    public List<AppLinesEntity> getLines() {
        return lines;
    }

    public void setLines(List<AppLinesEntity> lines) {
        this.lines = lines;
    }
}
