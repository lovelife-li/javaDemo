package com.study.utils.reflect.type;

import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

/**
 * @author ldb
 * @date 2020-03-05 14:36
 * @description ??
 */
@Data
public class Son implements A {

    public static void main(String[] args) throws NoSuchMethodException {
        System.out.println(A.class.isInterface());
        Type[] types = A.class.getGenericInterfaces();
        Type[] types1 = Son.class.getGenericInterfaces();
        System.out.println(types1[0].getTypeName()+","+types1[1].getTypeName());

        System.out.println("----------------");

        Constructor<Son> declaredConstructor = Son.class.getDeclaredConstructor(int.class);
        System.out.println(declaredConstructor.getName());
        System.out.println(declaredConstructor.getModifiers());
        System.out.println(declaredConstructor.getDeclaringClass().getName());

        Class<?> clazz = Son.class;
//        clazz.

    }

    private String name;
    int age;
    public String addr;

    public Son() {
        System.out.println("son()");
    }

    public Son(int age) {
        this.age = age;
    }

    public Son(int age, String name) {
        this.age = age;
        this.name = name;
    }


}
