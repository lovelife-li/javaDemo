package com.study.utils.mq.rockeymq.demo01;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class PushConsumer {

     public static void main(String[] args) throws MQClientException {
           DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("workGroup");
           consumer.setNamesrvAddr("192.168.88.16:9876");
           consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
           consumer.subscribe("TopicTest", "*");
           consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                     System.out.printf(Thread.currentThread().getName() + "Receive New Messages :" + msgs + "%n");
                     return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
           });
           consumer.start();
     }
}