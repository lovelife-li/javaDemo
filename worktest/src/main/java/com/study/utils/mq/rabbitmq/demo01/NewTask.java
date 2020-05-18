package com.study.utils.mq.rabbitmq.demo01;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.MessageProperties;
  
public class NewTask  
{  
    //队列名称  
    private final static String QUEUE_NAME = "workqueue";  
  
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接和频道  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("localhost");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        // 声明队列  
        boolean durable = true;// 1、设置队列持久化 
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);  
        //发送10条消息，依次在消息后面附加1-10个点  
        for (int i = 0; i < 10; i++)
        {  
            String dots = "";  
            for (int j = 0; j <= i; j++)  
            {  
                dots += ".";  
            }  
            String message = "helloworld" + dots+dots.length();  
            //basicPublish第一个参数为交换机名称、第二个参数为队列映射的路由key、
            //第三个参数为消息的其他属性、第四个参数为发送信息的主体MessageProperties.PERSISTENT_TEXT_PLAIN
            // MessageProperties 2、设置消息持久化  
            channel.basicPublish("", QUEUE_NAME,  
                    MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");  
        }  
        //关闭频道和资源  
        channel.close();  
        connection.close();  
  
    }  
  
  
}  