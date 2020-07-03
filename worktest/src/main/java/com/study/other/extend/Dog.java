package com.study.other.extend;

/**
 * todo
 *
 * @author ldb
 * @date 2020/06/16
 */
public class Dog implements Animail {

    @Override
    public String name() {
        return "dog";
    }

    public static void main(String[] args) {
        System.out.println(new Dog().name());
    }
}
