package com.study.utils.reflect.demo01;

public class Person extends Parent{
    //私有属性
    private String name = "Tom";

    protected int hobby;

    String address;

    //公有属性
    public int age = 18;

    //构造方法
    public Person() {
    }

    //私有方法
    private void say() {
        System.out.println("private say()...");
    }

    //公有方法
    public void work() {
        System.out.println("public work()...");
    }
}

