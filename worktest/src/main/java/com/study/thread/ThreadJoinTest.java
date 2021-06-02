package com.study.thread;

/**
 * @author ldb
 * @date 2021/5/29
 * @dsscription
 */
public class ThreadJoinTest {
    static int x = 0;
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            x = 10;
            System.out.println("hello");
        });
        t1.start();
        t1.join();
        System.out.println(x);
    }
}
