package com.study;

/**
 * @author ldb
 * @date 2020/04/01 15:33
 * @description ??
 */
public class Test2 {

    public static void main(String[] args) throws InterruptedException {

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
    }

    public void test() {
        throw new RuntimeException();
    }
}
