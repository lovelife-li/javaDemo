package com.study.utils.hutool.core;

import cn.hutool.core.clone.CloneRuntimeException;
import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.clone.Cloneable;
/**
 * @author ldb
 * @date 2020/04/28
 * @description 克隆
 * 要想实现深克隆，请使用：ObjectUtil.cloneByStream(obj).前提是对象必须实现Serializable接口。
 */
public class CloneTest {

    public static void main(String[] args) {
        Cat cat = new Cat();
        System.out.println(cat.age);

        Cat cat1 = cat.clone();
        cat1.age = 20;
        System.out.println(cat1.age);

        Dog dog = new Dog();
        Dog dog1 = dog.clone();
        System.out.println(dog1.name);

    }

    private static class Cat implements Cloneable<Cat>{
        private String name = "miaomiao";
        private int age = 2;

        @Override
        public Cat clone() {
            try {
                return (Cat) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new CloneRuntimeException(e);
            }
        }
    }

    private static class Dog extends CloneSupport<Dog> {
        private String name = "wangwang";
        private int age = 3;
    }
}
