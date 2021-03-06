package com.izhuixin.rsps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

/**
 * Created by Mr.Yangxiufeng on 2017/12/29.
 * Time:10:08
 * ProjectName:Mirco-Service-Skeleton
 */
@Configuration
//@EnableOAuth2Sso
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().mvcMatchers("/v2/api-docs ").permitAll();
        http.authorizeRequests().antMatchers("/uaa/*.html").permitAll();
        http.authorizeRequests().antMatchers("/uaa/oauth/token").permitAll();
        http.csrf().disable();
    }*/

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/uaa/**").permitAll();
        http.authorizeRequests().antMatchers("/api/**").permitAll();
        http.authorizeRequests().antMatchers("/app/**").permitAll();
        http.authorizeRequests().antMatchers("/manager/**").permitAll();
        http.authorizeRequests().antMatchers("/custom/**").permitAll();
        http.authorizeRequests().antMatchers("/assets/**").permitAll();
        http.authorizeRequests().antMatchers("/enterprise/**").permitAll();
        http.antMatcher("/**").authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().antMatchers("/","/anon").permitAll();
        http.csrf().disable().logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/login");
    }

    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //@formatter:off
        //super.configure(web);
        web.httpFirewall(defaultHttpFirewall());
    }
}
