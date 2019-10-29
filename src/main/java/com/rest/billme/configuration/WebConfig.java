package com.rest.billme.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rest.billme.interceptors.RequestLoggerInterceptor;
import com.rest.billme.repository.RequestLogRepository;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    RequestLogRepository requestLogRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLoggerInterceptor(requestLogRepository));
    }

}
