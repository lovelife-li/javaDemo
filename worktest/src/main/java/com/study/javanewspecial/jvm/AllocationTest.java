package com.study.javanewspecial.jvm;

/**
 * @author ldb
 * @date 2020/04/08 9:56
 * @description 测试分配
 */
public class AllocationTest {

    private static final int _1MB = 1024 * 1024;
    /**
     * VM参数：-verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
    }

    public static void main(String[] args) {
        testAllocation();
    }
}
