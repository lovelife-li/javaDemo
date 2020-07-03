package com.study.javanewspecial.jvm;

/**
 * @author ldb
 * @date 2020/04/08 10:46
 * @description 长期存活的对象进入老年代
 */
public class ObjectAgeTest {

    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *  -XX:MaxTenuringThreshold=1 -XX:+UseSerialGC
     *  -XX:+PrintTenuringDistribution jdk11无效
     *  -XX:+HeapDumpOnOutOfMemoryError 在内存溢出异常出现之后自动生成堆转储快照文件
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4]; // 什么时候进入老年代决定于XX:MaxTenuring-Threshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    public static void main(String[] args) throws InterruptedException {
        testTenuringThreshold();
        Thread.currentThread().join();
    }
}
