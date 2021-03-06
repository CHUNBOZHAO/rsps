package com.izhuixin.authsample.service.impl;


import com.izhuixin.authsample.entity.*;
import com.izhuixin.authsample.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mr.Yangxiufeng on 2017/12/27.
 * Time:16:37
 * ProjectName:Mirco-Service-Skeleton
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private OperatorInfoService operatorInfoService;

    @Autowired
    private CustomInfoService customInfoService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] aUser = username.split(" ");
        if(aUser.length != 2){
            throw new UsernameNotFoundException("用户名:" + username + ",格式错误!");
        }

        String client = aUser[0];
        String user_name = aUser[1];

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        // 可用性 :true:可用 false:不可用
        boolean enabled = true;
        // 过期性 :true:没过期 false:过期
        boolean accountNonExpired = true;
        // 有效性 :true:凭证有效 false:凭证无效
        boolean credentialsNonExpired = true;
        // 锁定性 :true:未锁定 false:已锁定
        boolean accountNonLocked = true;
        String password="";

        if(client.equals("webApp")) {
            SysUserEntity userEntity = sysUserService.findByUsername(user_name);
            if (userEntity == null) {
                throw new UsernameNotFoundException("用户:" + username + ",不存在!");
            }
            List<SysPermissionEntity> permissions = sysPermissionService.getPermissionsByUserId(userEntity.getUser_id());
            for (SysPermissionEntity p : permissions
                    ) {
                GrantedAuthority authority = new SimpleGrantedAuthority(p.getUrl());
                grantedAuthorities.add(authority);
            }
            GrantedAuthority authority = new SimpleGrantedAuthority("/manager/");
            grantedAuthorities.add(authority);
            user_name = userEntity.getUsername();
            password = userEntity.getUser_pwd();
        }
        else if(client.equals("app")){
            OperatorInfoEntity operatorEntity = operatorInfoService.findByUsername(user_name);
            if (operatorEntity == null) {
                throw new UsernameNotFoundException("用户:" + username + ",不存在!");
            }
            GrantedAuthority authority = new SimpleGrantedAuthority("/api/");
            grantedAuthorities.add(authority);
            GrantedAuthority authority1 = new SimpleGrantedAuthority("/app/");
            grantedAuthorities.add(authority1);
            password=operatorEntity.getUser_pwd();
        }
        else if(client.equals("wxApp")){
            CustomInfoEntity customEntity = customInfoService.findByCustomid(user_name);
            if (customEntity == null) {
                throw new UsernameNotFoundException("用户:" + username + ",不存在!");
            }
            GrantedAuthority authority = new SimpleGrantedAuthority("/custom/");
            grantedAuthorities.add(authority);
            password=customEntity.getCustom_pwd();
        }

        User user = new User(user_name, password,
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
        return user;
    }
}
