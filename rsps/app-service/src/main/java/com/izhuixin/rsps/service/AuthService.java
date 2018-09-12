package com.izhuixin.rsps.service;

import com.izhuixin.rsps.model.AppReqLoginUser;
import com.izhuixin.rsps.model.AppResOperator;

public interface AuthService {

     /**
      * 用户登录
      * @param loginUser
      * @return
      */
     AppResOperator handleLogin(AppReqLoginUser loginUser);
}
