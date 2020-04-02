package com.study.utils.reflect.demo02;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ldb
 * @date 2020/04/02 14:55
 * @description ??
 */
public class Test1 {

    /**
     * java.lang.reflect 包的核心接口和类如下：
     *
     * Member 接口 - 反映关于单个成员(字段或方法)或构造函数的标识信息。
     * Field 类 - 提供一个类的域的信息以及访问类的域的接口。
     * Method 类 - 提供一个类的方法的信息以及访问类的方法的接口。
     * Constructor 类 - 提供一个类的构造函数的信息以及访问类的构造函数的接口。
     * Array 类 - 该类提供动态地生成和访问 JAVA 数组的方法。
     * Modifier 类 - 提供了 static 方法和常量，对类和成员访问修饰符进行解码。
     * Proxy 类 - 提供动态地生成代理类和类实例的静态方法。
     */

    /**
     * 获得 Class 的三种方法：
     *
     * （1）使用 Class 类的 forName 静态方法
     * （2）直接获取某一个对象的 class
     * （3）调用 Object 的 getClass 方法，"foo".getClass()
     */
    @Test
    public void tetGetClass1() throws ClassNotFoundException {
        Class c1 = Class.forName("com.study.utils.reflect.demo02.Test1");
        System.out.println(c1.getCanonicalName());

        Class c2 = java.io.PrintStream.class;
        System.out.println(c2.getCanonicalName());

        Class c = "foo".getClass();
        System.out.println(c.getCanonicalName());
    }

    /**
     * 判断是否为某个类的实例有两种方式：
     *
     * 用 instanceof 关键字
     * 用 Class 对象的 isInstance 方法（它是一个 Native 方法）
     * @throws ClassNotFoundException
     */
    @Test
    public void test2() throws ClassNotFoundException {
        ArrayList arrayList = new ArrayList();
        if (arrayList instanceof List) {
            System.out.println("ArrayList is List");
        }
        if (List.class.isInstance(arrayList)) {
            System.out.println("ArrayList is List");
        }
    }

    /**
     * 创建实例
     * 通过反射来创建实例对象主要有两种方式：
     *
     * 用 Class 对象的 newInstance 方法。
     * clazz.getDeclaredConstructor().newInstance()
     * 用 Constructor 对象的 newInstance 方法。
     * @throws ClassNotFoundException
     */
    @Test
    public void test3() throws Exception {

        Class<?> c1 = StringBuilder.class;
        StringBuilder sb = (StringBuilder) c1.newInstance();
        sb.append("aaa");
        System.out.println(sb.toString());

        //获取String所对应的Class对象
        Class<?> c2 = String.class;
        //获取String类带一个String参数的构造器
        Constructor constructor = c2.getConstructor(String.class);
        //根据构造器创建实例
        String str2 = (String) constructor.newInstance("bbb");
        System.out.println(str2);
    }




}
