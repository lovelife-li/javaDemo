package com.study.thread.lock;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author ldb
 * @date 2020-03-13 15:58
 * @description ??
 */
public class CompletionServiceTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        // 创建 CompletionService
        CompletionService<Integer> cs = new
                ExecutorCompletionService<>(executor);
        // 异步向电商 S1 询价
        cs.submit(()->{
            TimeUnit.SECONDS.sleep(1);
            return 20;
        });
        // 异步向电商 S2 询价
        cs.submit(()->25);
        // 异步向电商 S3 询价
        cs.submit(()->56);
        // 将询价结果异步保存到数据库
        for (int i=0; i<3; i++) {
            Integer r = cs.take().get();
            executor.execute(()-> System.out.println(r));
        }
    }
}
