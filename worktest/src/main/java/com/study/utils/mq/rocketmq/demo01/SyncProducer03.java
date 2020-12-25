package com.study.utils.mq.rocketmq.demo01;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Producer端发送同步消息
 * 发送消息时开启消息轨迹
 */
public class SyncProducer03 {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("myProducerGroup",true);
        // 设置NameServer的地址
        producer.setNamesrvAddr("10.2.2.231:9876");
//        producer.setNamesrvAddr("127.0.0.1:9876");

        // 启动Producer实例
        producer.start();
        // 创建消息，并指定Topic，Tag和消息体,指定utf-8
        // todo
        Message msg = new Message("transbill_order_sysn",
                getMsg().getBytes(RemotingHelper.DEFAULT_CHARSET)
        );

//        Message msg = new Message("transbill_order_cancel",
//                getMsg2().getBytes(RemotingHelper.DEFAULT_CHARSET)
//        );
        // 发送消息到一个Broker
        SendResult sendResult = producer.send(msg,3000000);
        // 通过sendResult返回消息是否成功送达
        System.out.printf("%s%n", sendResult);
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

    public static String getMsg2(){
        return "{\n" +
                "\t\"cancelWhole\": 1,\n" +
                "\t\"transBillOrder\": 2020122100003000005,\n" +
                "\t\"cityId\": 3,\n" +
                "\t\"oiList\": [\n" +
                "\t\t253283281310,\n" +
                "\t\t253283281311\n" +
                "\t]\n" +
                "}";
    }

    public static String getMsg(){
        return "{\n" +
                "\t\"beginningPlaceId\": 1954,\n" +
                "\t\"cT\": 1608518017,\n" +
                "\t\"cityId\": 3,\n" +
                "\t\"cityName\": \"成都市\",\n" +
                "\t\"deliveryBatch\": 24,\n" +
                "\t\"deliveryBatchName\": \"下午送（标品）\",\n" +
                "\t\"deliveryDate\": 1608566400,\n" +
                "\t\"destinationId\": 3,\n" +
                "\t\"oiList\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"expectPackNum\": 7,\n" +
                "\t\t\t\"expectSkuNum\": 7,\n" +
                "\t\t\t\"expectSsuNum\": 7,\n" +
                "\t\t\t\"expectWeight\": 7.0,\n" +
                // todo
                "\t\t\t\"oiid\": 253283281318,\n" +
                "\t\t\t\"orderId\": 1222887701,\n" +
                "\t\t\t\"orderItemId\": 2532832815,\n" +
                "\t\t\t\"ownerId\": 1,\n" +
                "\t\t\t\"ownerName\": \"\",\n" +
                "\t\t\t\"packId\": 5,\n" +
                "\t\t\t\"packName\": \"包名字\",\n" +
                "\t\t\t\"receivableAmount\": 232.0,\n" +
                "\t\t\t\"skuFormat\": \"箱(10Kg)\",\n" +
                "\t\t\t\"skuId\": 4383730,\n" +
                "\t\t\t\"skuName\": \"11 冻鸡琵琶腿 150g以上 冷冻 直采 箱(10Kg)\",\n" +
                "\t\t\t\"skuPriceUnit\": \"箱\",\n" +
                "\t\t\t\"ssuFormat\": \"箱(10Kg)\",\n" +
                "\t\t\t\"skuUnit\":\"箱(10Kg)\",\n" +
                "\t\t\t\"ssuId\": 5317294,\n" +
                "\t\t\t\"ssuName\": \"[44]冻鸡琵琶腿 150g以上 冷冻 直采 箱(10Kg)\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"expectPackNum\": 6,\n" +
                "\t\t\t\"expectSkuNum\": 6,\n" +
                "\t\t\t\"expectSsuNum\": 6,\n" +
                "\t\t\t\"expectWeight\": 6.0,\n" +
                // todo
                "\t\t\t\"oiid\": 253283281319,\n" +
                "\t\t\t\"orderId\": 1222887701,\n" +
                "\t\t\t\"orderItemId\": 2532832814,\n" +
                "\t\t\t\"ownerId\": 5,\n" +
                "\t\t\t\"ownerName\": \"\",\n" +
                "\t\t\t\"packId\": 5,\n" +
                "\t\t\t\"packName\": \"包名字\",\n" +
                "\t\t\t\"receivableAmount\": 232.0,\n" +
                "\t\t\t\"skuFormat\": \"箱(10Kg)\",\n" +
                "\t\t\t\"skuId\": 4383730,\n" +
                "\t\t\t\"skuName\": \"44 冻鸡琵琶腿 150g以上 冷冻 直采 箱(10Kg)\",\n" +
                "\t\t\t\"skuPriceUnit\": \"箱\",\n" +
                "\t\t\t\"ssuFormat\": \"箱(10Kg)\",\n" +
                "\t\t\t\"skuUnit\":\"箱(10Kg)\",\n" +
                "\t\t\t\"ssuId\": 5317294,\n" +
                "\t\t\t\"ssuName\": \"[44]冻鸡琵琶腿 150g以上 冷冻 直采 箱(10Kg)\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"supplierId\": 306877,\n" +
                "\t\"transBillOrder\": 2020122100003000009\n" +
                "}";
    }
}