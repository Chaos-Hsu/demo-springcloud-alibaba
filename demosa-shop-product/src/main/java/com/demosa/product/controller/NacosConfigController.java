package com.demosa.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2021/1/12 8:12 下午
 */
@RestController
@RefreshScope//动态刷新
public class NacosConfigController {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Value("${config.appName}")
    private String appName;

    @Value("${config.env}")
    private String env;


    @RequestMapping("/test-config1")
    public String testConfig1(){
        String property = applicationContext.getEnvironment().getProperty("config.appName");
        return property;
    }

    @RequestMapping("/test-config2")
    public String testConfig2(){
        return appName;
    }

    @RequestMapping("/test-config3")
    public String testConfig3(){
        return env;
    }
}
