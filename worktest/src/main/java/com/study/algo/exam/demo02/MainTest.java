package com.study.algo.exam.demo02;

import java.io.File;
import java.util.TreeMap;
import java.util.concurrent.*;

/**
 * 程序入口
 * 解题思路：
 * 1，线程池中10个线程分别绑定一个阻塞队列，将读取的数据放入队列
 * 2，消费者用一个线程去消费所有队列中的数据，放入treeMap
 * 3，因为treeMap底层是红黑树，数据是有序的。并且利于key相同，可以很方便记录最小值
 * 4，需要注意利用countDownLatch去关闭线程池
 *
 * @author ldb
 * @date 2020/09/24
 */
public class MainTest {
    public static void main(String[] args) {
        // 创建线程池,任务小于100，指定队列大小
        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        // 创建treeMap
        TreeMap<String, Data> treeMap = new TreeMap<>(
                (x1,x2)->{
                    return Long.valueOf(x1).compareTo(Long.valueOf(x2));
                }
        );
        // 创建阻塞队列数组
        LinkedBlockingQueue<Data>[] queues = new LinkedBlockingQueue[10];
        for (int i = 0; i < queues.length; i++) {
            // 可以根据实际情况指定队列大小
            queues[i] = new LinkedBlockingQueue<>(1000);
        }
        // 执行时注意修改这个路径
        String path = "D:/aaa";
        File dir = new File(path);
        File[] files = dir.listFiles();
        CountDownLatch countDownLatch = new CountDownLatch(files.length + 1);
        for (File file : files) {
            pool.execute(new ProducerTask(file, queues,countDownLatch));
        }
        // 启动消费者线程
        new Thread(new ConsumerTask(queues,treeMap,countDownLatch)).start();
        try {
            //等待生产者线程和消费者线程执行完成，关闭线程池
            countDownLatch.await();
            pool.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 打印结果
        treeMap.forEach((key,value)->{
            StringBuilder sb = new StringBuilder();
            sb.append(value.getGroupId())
                    .append(",")
                    .append(value.getId())
                    .append(",")
                    .append(value.getQuota());
            System.out.println(sb.toString());
        });

        /**
         * 根据示例数据测试结果：
         * 100,2000102,98.3
         * 101,2000106,45.3
         * 102,2000104,98.3
         */
    }
}
