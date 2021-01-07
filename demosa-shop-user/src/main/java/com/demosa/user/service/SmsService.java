package com.demosa.user.service;

import com.demosa.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
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
@RocketMQMessageListener(
        consumerGroup = "shop-user", //消费的组名
        topic = "order-topic",//消费的主题
        consumeMode = ConsumeMode.CONCURRENTLY,//消息模式
        // ORDERLY顺序消费
        // CONCURRENTLY(同步,默认的是无序的)
        messageModel = MessageModel.CLUSTERING//消费模式
        // BROADCASTING(广播模式 每个消费者实例都会接受到)
        //CLUSTERING 集群模式 一条消息只能被一个消费者实例消费(默认是集群)
)
public class SmsService implements RocketMQListener<Order> {


    @Override
    public void onMessage(Order order) {
        log.info("接收了订单信息:order{}", order);
    }
}
