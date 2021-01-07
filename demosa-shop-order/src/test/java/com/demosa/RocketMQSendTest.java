package com.demosa;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.RPCHook;

import java.nio.charset.StandardCharsets;

/**
 * 描述 :
 * 作者 : 徐起超
 * 时间 : 2021/1/6 6:52 下午
 */
public class RocketMQSendTest {


    public static void main(String[] args)throws Exception {
        //1.创建生产者,设置组名
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("test-producer-group", getAclRPCHook());

        //2 设置nameservice地址
        defaultMQProducer.setNamesrvAddr("182.254.210.150:9876");

        defaultMQProducer.setInstanceName("testProducer");



        //3 启动生产者
        defaultMQProducer.start();

        //4 构建消息  主题 标签 内容
        Message message = new Message("testTopic", "testTag", ("测试生产者发送2").getBytes());

        //5 发送消息
        defaultMQProducer.send(message);

        //6 关闭
        defaultMQProducer.shutdown();

    }

    static RPCHook getAclRPCHook() {
        return new AclClientRPCHook(new SessionCredentials("root","83bjHb!@"));
    }
}
