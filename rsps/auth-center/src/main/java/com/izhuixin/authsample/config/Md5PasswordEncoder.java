package com.izhuixin.authsample.config;

import com.izhuixin.authsample.util.PasswordUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;
import java.util.regex.Pattern;

public class Md5PasswordEncoder implements PasswordEncoder {
    private final Log logger;

    public Md5PasswordEncoder() {
        this.logger = LogFactory.getLog(this.getClass());
    }


    @Override
    public String encode(CharSequence rawPassword) {

        return PasswordUtils.md5(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword != null && encodedPassword.length() != 0) {
            return PasswordUtils.md5(rawPassword.toString()).equals(encodedPassword);
        } else {
            this.logger.warn("Empty encoded password");
            return false;
        }
    }
}
