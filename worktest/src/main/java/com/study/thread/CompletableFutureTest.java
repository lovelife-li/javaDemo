package com.study.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author ldb
 * @date 2020-03-27 15:01
 * @description CompletableFuture 测试
 */
@Slf4j
public class CompletableFutureTest {

    private static ForkJoinPool pool = new ForkJoinPool();

    /**
     * 异常处理
     */
    @Test
    public void test4() {
        CompletableFuture<Integer>
                f = CompletableFuture.supplyAsync(() -> {
            sleep(0, TimeUnit.SECONDS);
            System.out.println("1");
            return 2;
        }).thenApplyAsync(r -> {

            sleep(0, TimeUnit.SECONDS);
            System.out.println("2");
            int a = 4 / 0;
            return r * 10;
        }).thenApplyAsync(x -> {
            System.out.println("3");
            return x + 4;
        }).exceptionally(x->{
            log.info(x.getMessage());
            return 2;
        }).whenComplete((x1,x2)->{
            log.info("{},{}",x1,x2);
        });
        System.out.println(f.join());
    }

    /**
     * OR 汇聚关系, 依赖的任务只要有一个完成就可以执行当前任务。
     */
    @Test
    public void test3() {
        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(() -> {
                    int t = 2;
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    int t = 1;
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });
        CompletableFuture<String> f3 =
                f1.applyToEither(f2, s -> s);
        System.out.println(f3.join());
    }

    /**
     * 任务①②③是串行执行的，②依赖①的执行结果，③依赖②的执行结果。
     * CompletableFuture<String> f0 =
     */
    @Test
    public void test2() {
        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                        () -> {
                            System.out.println("1");
                            sleep(2, TimeUnit.SECONDS);
                            return "Hello World";
                        })      //①
                        .thenApply(s -> {
                            System.out.println("2");
                            sleep(2, TimeUnit.SECONDS);
                            return s + " QQ";
                        })  //②
                        .thenApply(s -> {
                            System.out.println("3");
                            return s.toUpperCase();
                        });//③
        System.out.println(f0.join());
        // 输出结果  HELLO WORLD QQ

    }

    @Test
    public void test1() {
        // 任务 1：洗水壶 -> 烧开水
        CompletableFuture<Void> f1 =
                CompletableFuture.runAsync(() -> {
                    System.out.println("T1: 洗水壶...");
                    sleep(1, TimeUnit.SECONDS);
                    System.out.println("T1: 烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
        // 任务 2：洗茶壶 -> 洗茶杯 -> 拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    System.out.println("T2: 洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);
                    System.out.println("T2: 洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);
                    System.out.println("T2: 拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return " 龙井 ";
                });
        // 任务 3：任务 1 和任务 2 完成后执行：泡茶
        CompletableFuture<String> f3 =
                f1.thenCombine(f2, (__, tf) -> {
                    System.out.println("T1: 拿到茶叶:" + tf);
                    System.out.println("T1: 泡茶...");
                    return " 上茶:" + tf;
                });
        // 等待任务 3 执行结果
        System.out.println(f3.join());

    }

    void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }
}
