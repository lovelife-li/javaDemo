package com.study.thread.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 
 *对引用变量的原子操作 note:
 *在java中对引用类型的变量，赋值是原子性的，为什么还要有atomicReference,假如要对一个引用类型进行比较，设置等多于一个的操作，还要他们保证原子性时，就要用到
 *atomicReference
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {
        // 创建两个Person对象，它们的id分别是101和102。
        Person p1 = new Person(101);
        Person p2 = new Person(102);
        // 新建AtomicReference对象，初始化它的值为p1对象
        AtomicReference<Person> ar = new AtomicReference<Person>(p1);
        // 通过CAS设置ar。如果ar的值为p1的话，则将其设置为p2。
        if (ar.compareAndSet(p1, p2)) {
            System.out.println("设置成功");
        }
        Person p3 = (Person) ar.get(); // 取出来的是p2值
        System.out.println("p3 is " + p3);
        System.out.println("p3.equals(p1)=" + p3.equals(p1));
    }
}

class Person {
    volatile long id;

    public Person(long id) {
        this.id = id;
    }

    public String toString() {
        return "id:" + id;
    }
}