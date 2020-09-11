package com.study.thread.markword;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author ldb
 * @date 2020-03-03 11:50
 * @description ??
 */
public class ObjectHeader {
    public static void main(String[] args) throws InterruptedException {
        User user = new User();
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
        System.out.println(Integer.toBinaryString(user.hashCode()));
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
        Thread.sleep(1000);
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
        System.out.println("---------------");
        synchronized (user){
            System.out.println(ClassLayout.parseInstance(user).toPrintable());
        }

    }
}

class User {
    private String name;
    private Integer age;
    private boolean sex;
}