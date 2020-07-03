package com.study.javanewspecial.jvm;

/**
 * @author ldb
 * @date 2020/04/08 9:56
 * @description 测试分配
 */
public class OOMTest {

    private static final int _1MB = 1024 * 1024;
    /**
     * VM参数：-verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation4;
        String[] allocation1 = new String[1 * _1MB];
        String[] allocation2= new String[1 * _1MB];
        String[] allocation3= new String[1 * _1MB];
        allocation4 = new byte[5 * _1MB]; // 出现一次Minor GC
    }

    public static void main(String[] args) {
        testAllocation();
    }
}
