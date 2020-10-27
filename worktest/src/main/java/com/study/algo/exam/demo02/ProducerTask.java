package com.study.algo.exam.demo02;
import java.io.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * 提交给线程池的任务
 * 功能：读取文件每行数据，放入队列
 * @author ldb
 * @date 2020/09/24
 */
public class ProducerTask implements Runnable {
    private File file;
    LinkedBlockingQueue<Data>[] queues;
    private CountDownLatch countDownLatch;

    public ProducerTask(File file, LinkedBlockingQueue<Data>[] queues, CountDownLatch countDownLatch) {
        this.file = file;
        this.queues = queues;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String content = null;
            while ((content = reader.readLine()) != null) {
                String[] arr = content.split(",");
                Data data = new Data(arr[0], arr[1], Float.valueOf(arr[2]));
                // 每个线程绑定一个队列
                // 这里利用线程id是自增的特性
                int index =(int) Thread.currentThread().getId()%queues.length;
                queues[index].put(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
        }

    }
}