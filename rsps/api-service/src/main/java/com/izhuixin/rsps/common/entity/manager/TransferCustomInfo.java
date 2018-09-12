package com.izhuixin.rsps.common.entity.manager;

/**
 * 客户中转信息
 */
public class TransferCustomInfo {

    private String customId;

    private String transferId;

    private String transferName;

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getTransferId() {
        return transferId;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getTransferName() {
        return transferName;
    }

    public void setTransferName(String transferName) {
        this.transferName = transferName;
    }
}
