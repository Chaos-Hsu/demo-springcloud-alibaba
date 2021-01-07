package com.demosa.user.service;

import com.demosa.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2021/1/7 6:49 下午
 */
@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "shop-user", topic = "shop-order")
public class SmsService implements RocketMQListener<Order> {


    @Override
    public void onMessage(Order order) {
        log.info("接收了订单信息:order{}", order);
    }
}
