package com.izhuixin.rsps.config;

import com.google.gson.GsonBuilder;
import com.izhuixin.rsps.common.JsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author
 */
//@Configuration
public class CustomConfiguration {

    //@Bean
    public HttpMessageConverters customConverters() {
        Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        JsonHttpMessageConverter jsonHttpMessageConverter = new JsonHttpMessageConverter();
        jsonHttpMessageConverter.setWriteGson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create());
        messageConverters.add(jsonHttpMessageConverter);
        return new HttpMessageConverters(true, messageConverters);
    }
}

