package com.study.javanewspecial.java8;

import java.sql.SQLOutput;

/**
 * @author ldb
 * @date 2020-03-15 13:08
 * @dsscription
 */
public class Test {
    /**
     * 最大的容量为2的30次方
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    public static void main(String[] args) {
        System.out.println(2^20);

        System.out.println(tableSizeFor(23));




    }

    static final int tableSizeFor(int cap) {
        // 扩容门槛为传入的初始容量往上取最近的2的n次方
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
