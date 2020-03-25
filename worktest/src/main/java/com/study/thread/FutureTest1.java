package com.study.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author ldb
 * @date 2020-03-15 16:13
 * @dsscription
 * 这 3 个 submit() 方法之间的区别在于方法参数不同，下面我们简要介绍一下。
 * 提交 Runnable 任务 submit(Runnable task) ：
 * 这个方法的参数是一个 Runnable 接口，Runnable 接口的 run() 方法是没有返回值的，
 * 所以 submit(Runnable task) 这个方法返回的 Future 仅可以用来断言任务已经结束了，
 * 类似于 Thread.join()。
 *
 * 提交 Callable 任务 submit(Callable task)：这个方法的参数是一个 Callable 接口，
 * 它只有一个 call() 方法，并且这个方法是有返回值的，所以这个方法返回的 Future 对象可以通过
 * 调用其 get() 方法来获取任务的执行结果。
 *
 * 提交 Runnable 任务及结果引用 submit(Runnable task, T result)：这个方法很有意思，
 * 假设这个方法返回的 Future 对象是 f，f.get() 的返回值就是传给 submit() 方法的参数 result。
 */
public class FutureTest1 {


    @Test
    public void Test1() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors
                .newSingleThreadExecutor();
        Future<?> f = pool.submit(() -> {
            System.out.println("hello");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(f.get());

        System.out.println("world");
    }

    @Test
    public void Test2() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors
                .newSingleThreadExecutor();
        Future<String> f = pool.submit(() -> {
            System.out.println("hello");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello,world";
        });
        System.out.println(f.get());

        System.out.println("world");
    }

    @Test
    public void Test3() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors
                .newSingleThreadExecutor();
        final Result result = new Result();
        Future<Result> f = pool.submit(() -> {
            result.name="xiaoping";
        },result);
        System.out.println(f.get());

    }

    @Test
    public void Test4() throws ExecutionException, InterruptedException {
        // 创建 FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(()-> 1+2);
        // 创建线程池
        ExecutorService es = Executors.newCachedThreadPool();
        // 提交 FutureTask
        es.submit(futureTask);
        // 获取计算结果
        Integer result = futureTask.get();

    }
}

class Result{
    String name;
    int age;

    @Override
    public String toString() {
        return this.name;
    }
}