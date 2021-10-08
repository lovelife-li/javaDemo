package com.study.business.work;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Pool;

import java.util.concurrent.*;

/**
 * todo
 *
 * @author ldb
 * @date 2021/5/17
 */
@Slf4j
public class ThreadPoolTest {
    public static void main(String[] args) {
        test01();
    }

    public static  void test01()  {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2000),
                new ThreadFactoryBuilder().setNameFormat("测试线程池-%d").setDaemon(true).build()
                , new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                // 队列满了，就等待一些时间5秒，让任务执行完
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    log.error("线程池等待时异常", e);
                }
            }
        });
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            pool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    log.info("第{}个任务执行完毕", index);
                    latch.countDown();
                }
            });
        }
        try {
            log.info("等待任务执行...");
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();

    }

}
