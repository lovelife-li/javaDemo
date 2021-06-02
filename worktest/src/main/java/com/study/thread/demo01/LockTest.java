package com.study.thread.demo01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ldb
 * @date 2021/5/30
 * @dsscription
 */
public class LockTest {
    private final Lock rtl = new ReentrantLock();
    int value;
    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value+=1;
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        lockTest.addOne();

        Collections.synchronizedList(new ArrayList());
    }
}
