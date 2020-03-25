package com.study.thread.lock;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author ldb
 * @date 2020-03-13 8:45
 * @description ??
 */
public class MyCas {

    int count;

    volatile int age;

    private static AtomicIntegerFieldUpdater<MyCas>
            update = AtomicIntegerFieldUpdater.newUpdater(MyCas.class, "age");

    private static AtomicReferenceFieldUpdater
            dogUpdate = AtomicReferenceFieldUpdater.newUpdater(Dog.class,String.class,"name");

    void addOne() {
        int tmp;
        do {
            tmp = count;
        } while (!compareAndSet(tmp, tmp+1)) ;

    }

    synchronized boolean compareAndSet(int except, int newValue) {
        int curVal = count;
        if (curVal == except) {
            count = newValue;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        MyCas cas = new MyCas();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                cas.addOne();
                update.addAndGet(cas,1);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                cas.addOne();
                update.addAndGet(cas,1);
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(cas.count);
        System.out.println(cas.age);

        Dog dog1=new Dog();
        System.out.println(dogUpdate.compareAndSet(dog1,"dog1","compareAndSet"));
        System.out.println(dog1.name);
        System.out.println(dogUpdate.getAndSet(dog1, "getAndSet"));
        System.out.println(dog1.name);
    }
}
