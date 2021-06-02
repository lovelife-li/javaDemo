package com.study.other;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @author ldb
 * @date 2020/06/28
 */
public class Test2 {

    private int age;
    private String name;

    public Test2(int age) {
        this(age, "name");
        say(age, name);
        this.age = age;
    }

    public Test2(int age, String name) {
        this.name = name;
    }

    public void say(int age, String name) {

    }

    public static void register(){
        throw new RuntimeException("xx");
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        System.out.println(list.get(0));

        register();

    }
}
