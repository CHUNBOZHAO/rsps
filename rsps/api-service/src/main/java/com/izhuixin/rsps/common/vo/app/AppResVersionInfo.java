package com.izhuixin.rsps.common.vo.app;

/**
 * App版本信息
 */
public class AppResVersionInfo extends AppResBase {

    /** 版本 */
    private String version;

    /** 备注 */
    private String remark;

    /** 下载地址 */
    private String url;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
