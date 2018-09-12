package com.izhuixin.rsps.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	/**
	 * @param secret 密钥
	 * @param original 原串
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String getSign(String secret,String original) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		 return byte2hex(HMacMD5.getHmacMd5Bytes(original.getBytes("utf-8"),secret.getBytes("utf-8")));
	}
	
	private static String byte2hex(byte[] data){
		StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < data.length; n++) {
            stmp = (Integer.toHexString(data[n] & 0XFF));
            if (stmp.length() == 1)
                hs.append("0").append(stmp);
            else
                hs.append(stmp);
        }
        return hs.toString().toUpperCase();
	}
	
}