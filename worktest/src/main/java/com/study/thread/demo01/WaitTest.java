package com.study.thread.demo01;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author ldb
 * @date 2021/5/29
 * @dsscription
 */
public class WaitTest {
    public static void main(String[] args) {
        Allocator allocator = new Allocator();
        allocator.apply("A", "B");
        new Thread(() -> {
            allocator.apply("C", "D");
        }).start();

    }

}
