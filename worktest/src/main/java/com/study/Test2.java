package com.study;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ldb
 * @date 2020/04/01 15:33
 * @description ??
 */
public class Test2 {

    public static void main(String[] args) {

        if (2 != 2 || 3 == 3 && 1 != 1) {
            System.out.println("hello");
        }

        System.out.println(-20>>2); // -5
        System.out.println(-20>>>2); // 1073741819

        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(2));
        System.out.println(Integer.toBinaryString(-1<<14));
        System.out.println(-1<<14);

        System.out.println(UUID.randomUUID());
        System.out.println(test2());

        System.out.println(0x0100);



    }

    public void test() {
        throw new RuntimeException();
    }

    public static int test2(){

        ReentrantLock lock = new ReentrantLock(true);
        lock.tryLock();

        try {
            return 1;
        }finally {
            System.out.println("222");
        }
    }
}
