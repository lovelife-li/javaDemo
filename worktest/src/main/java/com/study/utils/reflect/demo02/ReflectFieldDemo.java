package com.study.utils.reflect.demo02;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class ReflectFieldDemo {
    class FieldSpy<T> {
        public boolean[][] b = {{false, false}, {true, true}};
        public String name = "Alice";
        public List<Integer> list;
        public T val;
        private String address;
        protected int age;
        String hobby;

    }

    public static void main(String[] args) throws Exception {
        Field f1 = FieldSpy.class.getField("b");
        System.out.println(f1.getType());
//        System.out.println(f1.get(null));

        // 只有pulic 包括父类
        Field[] f2 = FieldSpy.class.getFields();
        Arrays.asList(f2).stream().forEach(
                x->{
                    System.out.println(x.getName());
                }
        );

        // 加declared表示当前类，所以字段
        System.out.println("-----------------");
        Field[] f3 = FieldSpy.class.getDeclaredFields();
        Arrays.asList(f3).stream().forEach(
                x->{
                    System.out.println(x.getName());
                }
        );

    }
}
