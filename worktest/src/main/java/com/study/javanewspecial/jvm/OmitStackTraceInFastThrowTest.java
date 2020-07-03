package com.study.javanewspecial.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 大量抛出同样的异常的后，后面的异常输出将不打印堆栈。
 *
 * @author ldb
 * @date 2020/06/23
 */
@Slf4j
public class OmitStackTraceInFastThrowTest {
    public static void main(String[] args) throws InterruptedException {
        NPEThread npeThread = new NPEThread();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(npeThread);
            // 稍微sleep一下, 是为了不要让异常抛出太快, 导致控制台输出太快, 把有异常栈信息冲掉, 只留下fast throw方式抛出的异常
            Thread.sleep(2);
        }
    }
}

class NPEThread extends Thread {
    private static int count = 0;

    @Override
    public void run() {
        try {
            System.out.println(this.getClass().getSimpleName() + "--" + (++count));
            String str = null;
            // 制造空指针NPE
            System.out.println(str.length());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
