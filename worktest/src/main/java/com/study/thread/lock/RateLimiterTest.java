package com.study.thread.lock;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Math.max;

/**
 * @author ldb
 * @date 2020-03-16 15:35
 * @description ??
 */
public class RateLimiterTest {

    @Test
    public void test1() {
        // 限流器流速：2 个请求 / 秒
        RateLimiter limiter = RateLimiter.create(2.0);
        // 执行任务的线程池
        ExecutorService es = Executors.newFixedThreadPool(1);
        // 记录上一次执行时间
        AtomicLong prev = new AtomicLong(System.nanoTime());
        // 测试执行 20 次
        for (int i = 0; i < 20; i++) {
            // 限流器限流
            limiter.acquire();
            // 提交任务异步执行
            es.execute(() -> {
                long cur = System.nanoTime();
                // 打印时间间隔：毫秒
                System.out.println(
                        (cur - prev.get()) / 1000_000);
                prev.set(cur);
            });
        }
    }

}

class SimpleLimiter {
    // 下一令牌产生时间
    long next = System.nanoTime();
    // 发放令牌间隔：纳秒
    long interval = 1000_000_000;
    // 预占令牌，返回能够获取令牌的时间
    synchronized long reserve(long now){
        // 请求时间在下一令牌产生时间之后
        // 重新计算下一令牌产生时间
        if (now > next){
            // 将下一令牌产生时间重置为当前时间
            next = now;
        }
        // 能够获取令牌的时间
        long at=next;
        // 设置下一令牌产生时间
        next += interval;
        // 返回线程需要等待的时间
        return max(at, 0L);
    }
    // 申请令牌
    void acquire() {
        // 申请令牌时的时间
        long now = System.nanoTime();
        // 预占令牌
        long at=reserve(now);
        long waitTime=max(at-now, 0);
        // 按照条件等待
        if(waitTime > 0) {
            try {
                TimeUnit.NANOSECONDS
                        .sleep(waitTime);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}