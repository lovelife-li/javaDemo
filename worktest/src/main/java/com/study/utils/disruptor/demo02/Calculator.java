package com.study.utils.disruptor.demo02;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }
    
    public static int addStatic(int a, int b) {
        return a + b;
    }
    
    private int addPrivate(int a, int b) {
        return a + b;
    }
    
    private static int addStaticPrivate(int a, int b) {
        return a + b;
    }

}