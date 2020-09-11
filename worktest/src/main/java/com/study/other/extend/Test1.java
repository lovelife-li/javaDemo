package com.study.other.extend;

public class Test1 {

    static class Father {
        int a = 1;

    }

    static class Son extends Father {
        int a = 2;
    }

    public static void main(String[] args) {
        Son s = new Son();
        System.out.println(s.a);
        new Test();

//        new Test();
    }
}

