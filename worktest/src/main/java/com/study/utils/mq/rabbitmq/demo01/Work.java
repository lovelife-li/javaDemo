package com.study.utils.mq.rabbitmq.demo01;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 先运行3个工作者（Work.java）实例，然后运行NewTask.java
 */
public class Work  
{  
    //队列名称  
    private final static String QUEUE_NAME = "workqueue";  
  
    public static void main(String[] argv) throws java.io.IOException,
            InterruptedException, TimeoutException {
        //区分不同工作进程的输出  
        int hashCode = Work.class.hashCode();  
        //创建连接和频道  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("localhost");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        //声明队列  
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);  
        System.out.println(hashCode  
                + " [*] Waiting for messages. To exit press CTRL+C");  
        //设置最大服务转发消息数量  
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer Received '" + message + "'");
                System.out.println(hashCode + " [x] Received '" + message + "'");
                try {
                    doWork(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(hashCode + " [x] Done");
            }
        };
        // 指定消费队列  
        boolean ack = false; // 打开应答机制
        channel.basicConsume(QUEUE_NAME, ack, consumer);
        channel.basicConsume(QUEUE_NAME, ack, consumer);
        channel.basicConsume(QUEUE_NAME, ack, consumer);
    }  
  
    /** 
     * 每个点耗时1s 
     * @param task 
     * @throws InterruptedException 
     */  
    private static void doWork(String task) throws InterruptedException  
    {  
        for (char ch : task.toCharArray())  
        {  
            if (ch == '.')  {
                Thread.sleep(500);
            }

        }  
    }  
}  