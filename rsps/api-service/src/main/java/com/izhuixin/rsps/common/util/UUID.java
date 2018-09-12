package com.izhuixin.rsps.common.util;

/**
 * UUID工具类
 */
public final class UUID {

    public static String geneate() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(geneate());
    }
}
