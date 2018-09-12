package com.izhuixin.rsps.common.util;

/**
 * 企业唯一ID生成
 */
public class EntIdBuilder {

    public static String geneate() {
        return String.valueOf(SnowflakeIdWorker.getInstance().nextId());
    }

}
