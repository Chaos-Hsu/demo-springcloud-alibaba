package com.demosa.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/29 11:00 上午
 */
//@Configuration
public class FilterConfig {


    //@Bean
    public FilterRegistrationBean sentinelFilterRegist() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CommonFilter());
        registrationBean.setName("sentinelFilter");
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
        return registrationBean;
    }

}
