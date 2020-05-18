package com.study.utils.mq.rabbitmq.demo01;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息生成者
 */
public class Producer {
    public final static String QUEUE_NAME="myrabbitMQ";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        //factory.setHost("10.112.38.7");
        factory.setHost("127.0.0.1");
//        factory.setUsername("guest");
//        factory.setPassword("guest");
//        factory.setPort(5672);
        //创建一个新的连接
        Connection connection = factory.newConnection();
        System.out.println(connection.isOpen());
        
        
        //创建一个通道
        Channel channel = connection.createChannel();
        //  声明一个队列       
        //参数1：队列名称
        //参数2：为true时server重启队列不会消失
        //参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
        //参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
        //参数5：建立队列时的其他参数
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello RabbitMQ";
        //发送消息到队列中 5 条
        for (int i = 0; i < 5; i++) {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        }
        System.out.println("Producer Send +'" + message + "'");
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}