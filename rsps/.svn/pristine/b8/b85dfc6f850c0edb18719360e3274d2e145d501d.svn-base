package com.izhuixin.rsps.common.vo.web;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 管理用户拓扑图数据结构
 */
public class SysUserTopoVO {

    /** 节点名称 */
    private String name;

    /** 节点图片 */
    private String symbol;

    /** 节点图片大小 */
    private List<Integer> symbolSize = Lists.newArrayList();

    /** 子节点信息 */
    private List<SysUserTopoVO> children = Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SysUserTopoVO> getChildren() {
        return children;
    }

    public void setChildren(List<SysUserTopoVO> children) {
        this.children = children;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<Integer> getSymbolSize() {
        return symbolSize;
    }

    public void setSymbolSize(List<Integer> symbolSize) {
        this.symbolSize = symbolSize;
    }
}
