package com.study;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.UUID;
import java.util.concurrent.Executors;
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


        PriorityQueue<Integer> p = new PriorityQueue<>();
        p.offer(1);
        p.offer(2);
        p.offer(2);

        System.out.println(Arrays.toString("99999999".getBytes()));


        System.out.println(Arrays.toString(intToByte(-10)));

        Integer.toBinaryString(2);

        System.out.println((-3&0xFF));
        System.out.println((128&0xFF));
        System.out.println(-127);
        System.out.println((1-0.9));

        byte[] a = new byte[10];
        a[0]= -127;
        System.out.println(a[0]);
        int c = a[0]&0xff;
        System.out.println(c);

//        Executors.newScheduledThreadPool()
//        Executors.newWorkStealingPool()
    }

    public static byte[] intToByte(int n){
        return new byte[]{
                (byte)((n>>24)&0xff),
                (byte)((n>>16)&0xff),
                (byte)((n>>8)&0xff),
                (byte)(n&0xff)
        };
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
