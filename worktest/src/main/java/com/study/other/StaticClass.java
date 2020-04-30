package com.study.other;

import java.util.concurrent.TimeUnit;
/**
 * @author ldb;
 * @date 2020/4/14
 * @description 静态内部类和非静态内部类一样，都不会因为外部内的加载而加载，同时静态内部类的加载不需要依附外部类，在使用时才加载，
 * 不过在加载静态内部类的过程中也会加载外部类
 */
public class StaticClass {

    public static long OUTER_DATE = System.currentTimeMillis();

    static {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("外部类静态块加载时间：" + System.currentTimeMillis());

    }

    public StaticClass() {
        System.out.println("外部类构造函数时间：" + System.currentTimeMillis());
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class InnerStaticClass {
        public static long INNER_STATIC_DATE = System.currentTimeMillis();

        static {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("静态内部类静态块加载时间：" + System.currentTimeMillis());

        }
    }

    class InnerClass {
        public long INNER_DATE = 0;

        public InnerClass() {
            INNER_DATE = System.currentTimeMillis();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("外部类静态变量加载时间：" + StaticClass.OUTER_DATE);
        StaticClass outer = new StaticClass();
        System.out.println("非静态内部类加载时间"+outer.new InnerClass().INNER_DATE);
        System.out.println("静态内部类加载时间："+InnerStaticClass.INNER_STATIC_DATE);
    }
}