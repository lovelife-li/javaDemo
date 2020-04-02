package com.study.utils.reflect.demo02;

import java.lang.reflect.Array;

public class ReflectArrayDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> cls = Class.forName("java.lang.String");
        Object array = Array.newInstance(cls, 25);
        //往数组里添加内容
        Array.set(array, 0, "Scala");
        Array.set(array, 1, "Java");
        Array.set(array, 2, "Groovy");
        Array.set(array, 3, "Scala");
        Array.set(array, 4, "Clojure");
        //获取某一项的内容
        System.out.println(Array.get(array, 3));
    }
}
//Output:
//Scala
