package com.izhuixin.rsps.config;

import com.izhuixin.rsps.interceptor.SessionCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionCheckInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login/**").excludePathPatterns("/accessDenied/**");
        super.addInterceptors(registry);
    }

}
