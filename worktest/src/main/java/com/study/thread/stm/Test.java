package com.study.thread.stm;

/**
 * @author ldb
 * @date 2020/03/30 14:38
 * @description ??
 */
public class Test {

    public static volatile int a = 10;

    public static void main(String[] args) {

        double a = Double.NEGATIVE_INFINITY;
        if (a == a) {
            System.out.println("ok");
        }
    }

}
