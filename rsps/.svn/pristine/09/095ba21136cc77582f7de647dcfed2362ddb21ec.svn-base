package com.izhuixin.rsps.common.vo.web;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * DataTable请求数据结构
 */
public class DataTableReqDataVO {

    private Integer draw;

    private Integer start;

    private Integer length;

    private Map<Search, String> search = Maps.newHashMap();

    public enum Search {
        value,
        regex
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Map<Search, String> getSearch() {
        return search;
    }

    public void setSearch(Map<Search, String> search) {
        this.search = search;
    }
}
