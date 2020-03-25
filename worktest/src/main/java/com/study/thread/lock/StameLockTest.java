package com.study.thread.lock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.StampedLock;

/**
 * @author ldb
 * @date 2020-01-15 15:59
 * @description ??
 */
public class StameLockTest {
    final static StampedLock lock
            = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        Thread T1 = new Thread(() -> {
            // 获取写锁
            lock.writeLock();
            // 永远阻塞在此处，不释放写锁
            System.out.println("T1阻塞在此处，不释放写锁");
            LockSupport.park();
            System.out.println("T1");

        });
        T1.setName("T1");
        T1.start();
        // 保证 T1 获取写锁
        Thread.sleep(100);
        Thread T2 = new Thread(() ->{
            // 阻塞在悲观读锁
            System.out.println("T2阻塞在此处，阻塞在悲观读锁");
            lock.readLock();
            System.out.println("T2");
        }
        );
        T2.setName("T2");
        T2.start();
        // 保证 T2 阻塞在读锁
        Thread.sleep(100);
        // 中断线程 T2
        // 会导致线程 T2 所在 CPU 飙升
        T2.interrupt();
        T2.join();
//        lock.tryConvertToWriteLock(1);
        System.out.println("main");
//        Map map = Collections. synchronizedMap(new HashMap());
    }

}
