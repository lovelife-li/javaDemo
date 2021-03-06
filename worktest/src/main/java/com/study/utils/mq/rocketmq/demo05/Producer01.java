package com.study.utils.mq.rocketmq.demo05;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import static com.study.utils.mq.rocketmq.Config.NAME_SRVER;

/**
 * Producer端发送同步消息
 * 这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
 */
public class Producer01 {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("myProducerGroup");
        // 设置NameServer的地址
        producer.setNamesrvAddr(NAME_SRVER);
        // 启动Producer实例
        producer.start();
        String[] tags = new String[]{"TagA","TagB","TagC", "TagE", "TagD"};
        for (int i = 0; i < 5; i++) {
            // 创建消息，并指定Topic，Tag和消息体,指定utf-8
            Message msg = new Message("TopicTest", tags[i],"key-"+i,
                    ("why " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            // 设置一些属性
            msg.putUserProperty("a", String.valueOf(i));
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg,30000);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);

        }


        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}