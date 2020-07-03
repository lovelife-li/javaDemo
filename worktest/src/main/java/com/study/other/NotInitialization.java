package com.study.other;

/**
 * 非主动使用类字段演示
 **/
public class NotInitialization {
    public static void main(String[] args) {
//        System.out.println(SubClass.value);
//        SuperClass[] sca = new SuperClass[10];
        System.out.println(ConstClass.HELLOWORLD);
    }
}