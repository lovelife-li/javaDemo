package com.study.other.extend.demo01;

class A {
    public A() {
        System.out.print("A gouzhao");
    }

    private static A a = new A();

    static {
        System.out.print("static");
    }

    {
        System.out.print("A1");
    }
}
// 静态变量从上往下执行
// 类加载就会执行静态变量，静态块
// A1A gouzhaostatic
public class B extends A {
    public B() {
        System.out.print("B");
    }

    public static void main(String[] args) {
        System.out.println("0000");
        B b = new B();
    }
}