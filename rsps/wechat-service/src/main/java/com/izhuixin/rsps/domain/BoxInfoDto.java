package com.izhuixin.rsps.domain;

import com.izhuixin.rsps.common.BoxInfoVO;
import com.izhuixin.rsps.common.Paginator;

import java.util.List;

/**
 * 包装箱信息数据传输对象
 */
public class BoxInfoDto {

    private List<BoxInfoVO> boxInfos = null;

    private Paginator paginator;

    public List<BoxInfoVO> getBoxInfos() {
        return boxInfos;
    }

    public void setBoxInfos(List<BoxInfoVO> boxInfos) {
        this.boxInfos = boxInfos;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }
}
