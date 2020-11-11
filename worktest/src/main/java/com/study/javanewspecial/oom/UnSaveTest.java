package com.study.javanewspecial.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

/**
 * @author ldb
 * @date 2020/04/02 14:01
 * @description unsafe 用法
 */
public class UnSaveTest {
    public static void main(String[] args) {

        PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>() {
            @Override
            public Unsafe run() throws Exception {
                Field field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                return Unsafe.class.cast(field.get(null));
            }
        };
        Unsafe unsafe = null;
        try {
            unsafe = AccessController.doPrivileged(action);
            unsafe.allocateMemory(1<<10);
        } catch (PrivilegedActionException e) {
            e.printStackTrace();
        }

    }

}
