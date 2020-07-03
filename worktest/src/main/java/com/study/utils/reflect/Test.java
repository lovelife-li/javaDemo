package com.study.utils.reflect;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class Test {

    private final int a = 13;

    public static void main(String[] args)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field field = Test.class.getDeclaredField("a");
        Test t = new Test();
        System.out.println(field.get(t));
        field.setAccessible(true);
        field.set(t, 1);
        System.out.println(field.get(t));
    }

//    public static void main(String[] args)
//            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//        Field field = Test.class.getDeclaredField("a");
//        Test t = new Test();
//        System.out.println(field.get(t));
//        AccessController.doPrivileged(new PrivilegedAction<Object>() {
//            public Object run() {
//                field.setAccessible(true);
//                return null;
//            }
//        });
//        field.set(t, 1);
//        System.out.println(field.get(t));
//    }
}