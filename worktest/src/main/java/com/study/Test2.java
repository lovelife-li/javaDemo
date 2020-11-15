package com.study;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ldb
 * @date 2020/04/01 15:33
 * @description ??
 */
public class Test2 {

    int a = 2;

    public Test2() {
        System.out.println(a);

    }

    public static void main(String[] args) {
        ArrayList<Integer> b = new ArrayList<>();
        b.add(new Integer(788));
        b.add(new Integer(789));
        System.out.println("xx:" + b.contains(new Integer(789)));


        Test2 ax = new Test2();
        Object obj1 = new Object();
        Object obj2 = new Object();


        ArrayList<Integer> al = new ArrayList<>();
        al.add(1);
        Integer[] arr = al.toArray(new Integer[0]);
        System.out.println(Arrays.toString(arr));


//        if (2 != 2 || 3 == 3 && 1 != 1) {
//            System.out.println("hello");
//        }
//
//        System.out.println(-20>>2); // -5
//        System.out.println(-20>>>2); // 1073741819
//
//        System.out.println(Integer.toBinaryString(-1));
//        System.out.println(Integer.toBinaryString(1));
//        System.out.println(Integer.toBinaryString(2));
//        System.out.println(Integer.toBinaryString(-1<<14));
//        System.out.println(-1<<14);

        HashMap<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < 64; i++) {
            map.put(i, i);
        }

        System.out.println(UUID.randomUUID());
        System.out.println(test2());

        System.out.println(0x0100);

        System.out.println("n:");
        int n = 4;
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
        n = (n + 1) << 1;
        System.out.println(n);

        PriorityQueue<Integer> p = new PriorityQueue<>();
        p.offer(1);
        p.offer(2);
        p.offer(2);

        System.out.println(Arrays.toString("99999999".getBytes()));


        System.out.println(Arrays.toString(intToByte(-10)));

        Integer.toBinaryString(2);

        System.out.println((-3 & 0xFF));
        System.out.println((128 & 0xFF));
        System.out.println(-127);
        System.out.println((1 - 0.9));

        byte[] a = new byte[10];
        a[0] = -127;
        System.out.println(a[0]);
        int c = a[0] & 0xff;
        System.out.println(c);

//        Executors.newScheduledThreadPool()
//        Executors.newWorkStealingPool()
    }

    public static byte[] intToByte(int n) {
        return new byte[]{
                (byte) ((n >> 24) & 0xff),
                (byte) ((n >> 16) & 0xff),
                (byte) ((n >> 8) & 0xff),
                (byte) (n & 0xff)
        };
    }

    public void test() {
        throw new RuntimeException();
    }

    public static int test2() {

        ReentrantLock lock = new ReentrantLock(true);
        lock.tryLock();

        try {
            return 1;
        } finally {
            System.out.println("222");
        }
    }
}
