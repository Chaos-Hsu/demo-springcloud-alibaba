package com.demosa;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.RPCHook;

import java.util.List;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2021/1/6 6:52 下午
 */
public class RocketMQReceiveTest {


    public static void main(String[] args) throws Exception {
        //1.创建消费者,设置组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-consumer-group", getAclRPCHook(), new AllocateMessageQueueAveragely());

        //2 设置nameservice地址
        consumer.setNamesrvAddr("182.254.210.150:9876");

        //3 订阅主题
        consumer.subscribe("testTopic", "*");

        //4.处理
        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                System.out.println("消息:=" + list);
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });


        //5 启动
        consumer.start();

    }

    static RPCHook getAclRPCHook() {
        return new AclClientRPCHook(new SessionCredentials("root", "83bjHb!@"));
    }
}
