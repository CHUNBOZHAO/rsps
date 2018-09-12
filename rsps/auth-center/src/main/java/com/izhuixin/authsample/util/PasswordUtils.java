package com.izhuixin.authsample.util;



import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * wd
 */
public class PasswordUtils {

    public static String md5(String password){
        try {
            return MD5.getSign("rsps-izhuixin-md5", "-i-z-h-u-i-x-i-n-" + password
                    + "-r-s-p-s-r-s-p-s-");
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("md5 password error");
    }

    public static boolean compare(String dbpassword,String inputpassword){
        checkArgument(isNotBlank(inputpassword) && isNotBlank(dbpassword),"password can not be blank.");
        return dbpassword.equals(md5(inputpassword));
    }

}
