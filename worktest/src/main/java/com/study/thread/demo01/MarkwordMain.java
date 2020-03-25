package com.study.thread.demo01;

import org.openjdk.jol.info.ClassLayout;

public class MarkwordMain {

    private static final String SPLITE_STR = "===========================================";
    private static User USER = new User();

    private static void printf() {
        System.out.println(SPLITE_STR);
        System.out.println(ClassLayout.parseInstance(USER).toPrintable());
        System.out.println(SPLITE_STR);
    }

    private static Runnable RUNNABLE = () -> {
        while (!Thread.interrupted()) {
            synchronized (USER) {
                printf();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new Thread(RUNNABLE).start();
            Thread.sleep(1000);
        }
        Thread.sleep(Integer.MAX_VALUE);
    }
}
