package com.study.thread.demo01;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ldb
 * @date 2021/6/1
 * @dsscription
 */
@Slf4j
public class CompletionServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10), generateThreadFactory("test-%d"),
                new ThreadPoolExecutor.AbortPolicy());
        // 创建 CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        // 异步执行任务1
        cs.submit(() -> print(1));
        // 异步执行任务2
        cs.submit(() -> print(2));
        // 异步执行任务3
        cs.submit(() -> print(3));
        // 将询价结果异步保存到数据库
        for (int i = 0; i < 3; i++) {
            Integer r = cs.take().get();
            executor.execute(() -> System.out.println(r));
        }
    }

    private static Integer print(int n) throws InterruptedException {
        TimeUnit.SECONDS.sleep(n);
        log.info("{},{}",Thread.currentThread().getName(),n);
        return n;
    }
    private static ThreadFactory generateThreadFactory(String nameFormat){
        AtomicLong count =  new AtomicLong(0L);
        return (runnable) -> {
            Thread thread = new Thread(runnable);
            thread.setName(String.format(nameFormat, count.getAndIncrement()));
            return thread;
        };
    }

}
