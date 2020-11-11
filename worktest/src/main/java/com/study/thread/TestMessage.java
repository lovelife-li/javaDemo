package com.study.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * todo
 *
 * @author ldb
 * @date 2020/10/21
 */
@Slf4j
public class TestMessage {
    private static ExecutorService executorService = new ThreadPoolExecutor(2, 10, 60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000),
            r -> {
                Thread thread = new Thread(r);
                thread.setName("SendMessage");
                return thread;
            });

    public static void main(String[] args) {
        Future<Boolean> future = executorService.submit(new Task());
        try {
            Boolean res = future.get(3000,TimeUnit.MILLISECONDS);
            if (res) {
                log.info("缓存redisMessage：{}同步完成", "x");
            } else {
                log.warn("缓存redisMessage：{}未同步完成", "redisMessage");
            }
        } catch (InterruptedException e) {
            log.error("线程中断：", e);
        } catch (ExecutionException e) {
            log.error("线程执行异常:", e);
        } catch (TimeoutException e) {
            log.error("线程执行超时异常:", e);
        }
    }

    /**
     * 重试任务
     */
    public static class Task implements Callable<Boolean> {

        // 重发策略
        private int[] timeArr = {1, 1, 2, 4, 8, 10, 12, 14, 16, 18, 20, 25, 26, 28, 30, 35};

        public Task() {

        }

        @Override
        public Boolean call() {
            return checkMsgStatus();
        }

        private Boolean checkMsgStatus() {
            int n = 0;
            while (n++ < timeArr.length) {
                try {
                    // 处理5秒
                    TimeUnit.MILLISECONDS.sleep(5000);
                } catch (InterruptedException e) {
                    log.warn("等待处理失败", e);
                    return false;
                }
            }
            return true;

        }
    }
}
