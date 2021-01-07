package com.demosa.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2020/12/25 11:30 上午
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.demosa.domain")
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class);
    }

}
