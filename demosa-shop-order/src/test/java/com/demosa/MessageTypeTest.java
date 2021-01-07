package com.demosa;

import com.demosa.order.OrderApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2021/1/7 8:11 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {

    @Autowired
    private RocketMQTemplate mqTemplate;


    /**
     * 同步消息 实时响应结果
     */
    @Test
    public void syncSend() {
        //1.tocpic 2.消息体 3.超时时间
        SendResult result = mqTemplate.syncSend("test-sync:tag", "同步消息", 10000);
        System.out.println(result);
    }


    /**
     * 异步消息  消息结果通过回调函数响应
     */
    @Test
    public void asyncSend() throws Exception {
        //1 主题:后面是tag 2:消息体  3 回调函数
        mqTemplate.asyncSend("test-async:tag", "异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.out.println(throwable);
            }
        });
        System.out.println("主线程");
        Thread.sleep(3000000);
    }


    /**
     * 单向发送 不管结果
     */
    @Test
    public void oneWay() {
        for (int i = 0; i < 10; i++) {
            mqTemplate.sendOneWay("test-one", "单向发送" + i);
        }
    }


    /**
     * 单向顺序消息  保证发送到同一个
     */
    @Test
    public void oneOrderWay() {
        //第三个参数 自定义任意值  保证唯一  根据这个hashkey
        for (int i = 0; i < 10; i++) {
            mqTemplate.sendOneWayOrderly("test-one", "单向发送" + i, "hashKey");
        }
    }
}
