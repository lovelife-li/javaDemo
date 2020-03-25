package com.study.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ldb
 * @date 2020-01-16 11:27
 * @description ??
 */
public class SimulatedCAS {
      int count;

    // 返回更新之前的内存值
    synchronized int cas(int expect, int newValue) {
        int curValue = count;
        if (curValue == expect) {
            count = newValue;
        }
        return curValue;
    }


    // 更新成功，返回true
    synchronized boolean compareAndSet(int expect, int newValue) {
        return expect == cas(expect,newValue);
    }

    // 实现count+1
//    void addOne() {
//        int tmp ;
//        do {
//            tmp = count;
//        } while (tmp!= cas(tmp,tmp+1));
//    }

    void addOne() {
        int tmp ;
        do {
            tmp = count;
        } while (!compareAndSet(tmp,tmp+1));
    }




    public static void main(String[] args) throws InterruptedException {
        final SimulatedCAS test = new SimulatedCAS();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                test.addOne();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000000; i++) {
                test.addOne();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(test.count);

        int i = new AtomicInteger(2).addAndGet(1);
        int j = new AtomicInteger(2).getAndAdd(1);
        System.out.println(i+","+j);

        // 为什么没有保障原子性啊

    }

}
