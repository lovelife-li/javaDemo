package com.study.utils.reflect.aop;

public class Client1 {


    public static void main(String[] args) throws ClassNotFoundException {
        UserService userServiceImpl = new UserServiceImpl();

        UserService proxy = new UserServiceProxy(userServiceImpl);

        proxy.select();
        proxy.update();

        Object obj = new Client1();
        System.out.println(Client1.class.cast(obj));
        System.out.println();

        Class<?> clazz = Class.forName("io.netty.example.chapter0.Apple");
        System.out.println(clazz.getName());

        System.out.println("----------------------------------------");
        Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass("io.netty.example.chapter0.Apple");
        System.out.println(aClass.getName());


    }
}