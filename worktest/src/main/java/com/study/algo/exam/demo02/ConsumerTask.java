package com.study.algo.exam.demo02;

import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 消费者需要消费的任务
 * 功能：依次从每个队列取数据，消费。利用treeMap底层是红黑树的特性，key有序。排列数据。
 * @author ldb
 * @date 2020/09/24
 */
public class ConsumerTask implements Runnable {

    LinkedBlockingQueue<Data>[] queues;
    private TreeMap<String, Data> treeMap;
    private CountDownLatch countDownLatch;

    public ConsumerTask(LinkedBlockingQueue<Data>[] queues, TreeMap<String, Data> treeMap, CountDownLatch countDownLatch) {
        this.queues = queues;
        this.treeMap = treeMap;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            int n = 0;
            while (true) {
                // 依次从每个队列取数据
                for (int i = 0; i < queues.length; i++) {
                    if (!queues[i].isEmpty()) {
                        Data data = queues[i].take();
                        Data tmpData = treeMap.get(data.getGroupId());
                        if (tmpData == null) {
                            // treeMap不存在数据，直接放入
                            treeMap.put(data.getGroupId(), data);
                        } else {
                            // treeMap存在数据，比较最小值
                            if (data.getQuota() < tmpData.getQuota()) {
                                treeMap.put(data.getGroupId(), data);
                            }
                        }
                    } else {
                        // 记录队列为空的数量
                        ++n;
                    }
                }
                if (n == queues.length && countDownLatch.getCount() == 1) {
                    countDownLatch.countDown();
                    break;
                } else {
                    // 还原n , 有些队列可能一开始就是空
                    n = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}