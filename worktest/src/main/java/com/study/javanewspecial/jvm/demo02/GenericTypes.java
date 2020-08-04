package com.study.javanewspecial.jvm.demo02;

import java.util.ArrayList;
import java.util.List;

public class GenericTypes {
    public static String method2(List<String> list) {
        System.out.println("invoke method(List<String> list)");
        return "";
    }

    public static int method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }

    public static void main(String[] args) {
        method2(new ArrayList<String>());
        method(new ArrayList<Integer>());

        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);// true
        System.out.println(e == f);// false
        System.out.println(c == (a + b));// true
        System.out.println(c.equals(a + b));// true
        System.out.println(g == (a + b)); // true
        System.out.println(g.equals(a + b)); // false

        System.out.println("T3xgUHMBAaleVVjaq62d".length());


    }
}