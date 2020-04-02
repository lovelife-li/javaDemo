package com.study.javanewspecial.oom;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * 直接内存溢出
 * -Xmx256m -XX:MaxDirectMemorySize=100M
 */
public class DirectByteBufferTest {
    public static void main(String[] args) throws InterruptedException{
        //分配128MB直接内存
        ByteBuffer bb = ByteBuffer.allocateDirect(1024*1024*128);

        TimeUnit.SECONDS.sleep(10);
        System.out.println("ok");
    }
}
