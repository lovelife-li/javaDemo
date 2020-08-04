package com.study.javanewspecial.jvm;

import com.study.other.StaticClass;

/**
 * staticObj、instanceObj、localObj这三个变量本身（而不是它们所指向的对象）存放在哪里？
 * staticObj随着Test的类型信息存放在方法区，instanceObj随着Test的对象实
 * 例存放在Java堆，localObject则是存放在foo()方法栈帧的局部变量表中。
 * -Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 *
 * JDK 7及其以后版本的HotSpot虚拟机选择把静态变量与类型在Java语言一端的映射Class对象存放在一起，存储于Java堆之中
 */
public class JHSDB_TestCase {
    static class Test {
        int k = 2;
        static int m = 5;
        static Integer n = 333;
        String s = "ssss";
        String ss = "ssss";
        String sss = new String("ssss");
        String ssss = new String("sssss");
        String sssss = new String("sssss");
        String ssssss = "sssss";
        Integer i = Integer.valueOf(25);
        Integer i1 = Integer.valueOf(25);
        Integer i10 = Integer.valueOf(127);
        Integer i11 = Integer.valueOf(127);
        Integer i2 = Integer.valueOf(128);
        Integer i3 = Integer.valueOf(128);
        Integer i4 = Integer.valueOf(-128);
        Integer i5 = Integer.valueOf(-128);
        Integer i6 = Integer.valueOf(0);
        Integer i7 = Integer.valueOf(0);
        Integer i8 = Integer.valueOf(-129);
        Integer i9 = Integer.valueOf(-129);
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("done"); // 这里设一个断点
        }
    }

    private static class ObjectHolder {
        static int a = 1;
        int b = 2;
        static Integer c = 256;
    }

    public static void main(String[] args) {
        Test test = new JHSDB_TestCase.Test();

        test.foo();
    }
}