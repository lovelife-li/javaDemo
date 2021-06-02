package com.study.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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

    @Test
    public void test6(){
        CompletableFuture<Integer>
                f0 = CompletableFuture.supplyAsync(()->(7/0))
                .exceptionally(e->{
                    System.out.println("捕获异常："+e.getMessage());
                    return 2;
                })
                .thenApply(r->{
                    int i = 9/0;
                    return r*10;
                })
                .exceptionally(e->{
                    System.out.println("捕获异常："+e.getMessage());
                    return 3;
                }).whenComplete((x,e)->{
                    System.out.println(x);
//                    System.out.println(e.getMessage());
                });
        System.out.println(f0.join());
    }

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
                    int t = 1;
                    sleep(t, TimeUnit.SECONDS);
                    System.out.println("+++");
                    return String.valueOf(t);
                });
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    int t = 5;
                    sleep(t, TimeUnit.SECONDS);
                    System.out.println("---");
                    return String.valueOf(t);
                });
        CompletableFuture<String> f3 =
                f1.applyToEither(f2, s -> {
                    System.out.println(s);
                    return s;
                });
        System.out.println(f3.join());
    }

    /**
     * and 汇聚关系, 依赖的任务只要有一个完成就可以执行当前任务。
     */
    @Test
    public void test5() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            sleep(1, TimeUnit.SECONDS);
            System.out.println(1);
            return "任务1";
        });
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    sleep(2, TimeUnit.SECONDS);
                    System.out.println(2);
                    return "任务2";
                });
        CompletableFuture<String> f3 = f1.thenCombine(f2,(x,y)->{
            System.out.println(x+","+y);
            sleep(5, TimeUnit.SECONDS);
            System.out.println(3);
            return x+","+y;
        });
        System.out.println(f3.join());
    }

    /**
     * 任务①②③是串行执行的，②依赖①的执行结果，③依赖②的执行结果。
     * CompletableFuture<String> f0 =
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> f =
                CompletableFuture.supplyAsync(
                        () -> {
                            System.out.println("1");
                            return "Hello World";
                        })  //①
                        .thenApply(s -> {
                            System.out.println("2");
                            return s + " java";
                        })  //②
                        .thenApply(s -> {
                            System.out.println("3");
                            return s.toUpperCase();
                        });//③
        System.out.println(f.get());
        System.out.println(f.join());
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
                f1.thenCombine(f2, (rs1, rs2) -> {
                    System.out.println(rs1);
                    System.out.println("T1: 拿到茶叶:" + rs2);
                    System.out.println("T1: 泡茶...");
                    return " 上茶:" + rs2;
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
