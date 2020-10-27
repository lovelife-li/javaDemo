package com.study.algo.exam.demo03;

public class Test {
    public static void main(String[] args) {
        try {
            get();
            return;
        } catch (Exception ex) {
            System.out.println("Catch");
        } finally {
            System.out.println("Finally");
        }
        System.out.println("Exit");
    }
    public static void get() {
        System.out.println("Get");
    }
}