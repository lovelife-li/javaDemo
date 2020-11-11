package com.study.utils.mq.rocketmq.demo01;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import static com.study.utils.mq.rocketmq.Config.NAME_SRVER;

/**
 * Producer端发送同步消息
 * 发送消息时开启消息轨迹
 */
public class SyncProducer02 {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("myProducerGroup",true);
        // 设置NameServer的地址
        producer.setNamesrvAddr(NAME_SRVER);
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 1; i++) {
            // 创建消息，并指定Topic，Tag和消息体,指定utf-8
            Message msg = new Message("TopicTest", "TagB","OrderID189",
                    ("Hello RocketMQxxx222 " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg,3000000);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}