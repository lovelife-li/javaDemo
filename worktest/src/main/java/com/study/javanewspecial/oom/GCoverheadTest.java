package com.study.javanewspecial.oom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * JVm参数 -Xmx8m -Xms8m
 */
public class GCoverheadTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    //do nothing
                }
            });
        }
    }
}

