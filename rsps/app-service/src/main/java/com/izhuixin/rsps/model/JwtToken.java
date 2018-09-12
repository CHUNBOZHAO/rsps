package com.izhuixin.rsps.model;

public class JwtToken {
    String access_token;
    String refresh_token;
    String token_type;

    public String getAccessToken(){
        return access_token;
    }

    public String getRefreshToken(){
        return refresh_token;
    }

    public String getTokenType(){
        return token_type;
    }
}
