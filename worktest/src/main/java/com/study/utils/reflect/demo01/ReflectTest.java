package com.study.utils.reflect.demo01;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ldb
 * @date 2020/04/02 14:27
 * @description 反射总结
 * Java 反射机制在程序运行时，对于任意一个类，都能够知道这个类的所有属性和方法；
 * 对于任意一个对象，都能够调用它的任意一个方法和属性。并且能改变它的属性。
 * 这种 动态的获取信息 以及 动态调用对象的方法 的功能称为 java 的反射机制。
 */
public class ReflectTest {

    @Test
    public void test() throws Exception {

        Person p1 = new Person();
        Class c1 = p1.getClass();
        //2、直接通过 类名.class 的方式得到,该方法最为安全可靠，程序性能更高
        // 这说明任何一个类都有一个隐含的静态成员变量 class
        Class c2 = Person.class;
        //3、通过 Class 对象的 forName() 静态方法来获取，用的最多，
        //  但可能抛出 ClassNotFoundException 异常
//        Class c3 = Class.forName("com.ys.reflex.Person");

        //获得类完整的名字
        String className = c2.getName();
        System.out.println(className);//输出com.ys.reflex.Person
        System.out.println("----获得类的public类型的属性。");
        //获得类的public类型的属性。
        Field[] fields = c2.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());//age
        }
        System.out.println("----获得类的所有属性。包括私有的");
        //获得类的所有属性。包括私有的
        Field[] allFields = c2.getDeclaredFields();
        for (Field field : allFields) {
            System.out.println(field.getName());//name  age
        }
        System.out.println("----获得类的public类型的方法。这里包括 Object 类的一些方法");
        //获得类的public类型的方法。这里包括 Object 类的一些方法
        Method[] methods = c2.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());//work waid equls toString hashCode等
        }
        System.out.println("----获得类的所有方法。");
        //获得类的所有方法。
        Method[] allMethods = c2.getDeclaredMethods();
        for (Method method : allMethods) {
            System.out.println(method.getName());//work say
        }
        System.out.println("-----------");
        //获得指定的属性
        Field f1 = c2.getField("age");
        System.out.println(f1);
        //获得指定的私有属性
        Field f2 = c2.getDeclaredField("name");
        //启用和禁用访问安全检查的开关，值为 true，则表示反射的对象在使用时应该取消 java 语言的访问检查；反之不取消
        f2.setAccessible(true);
        System.out.println(f2);

        System.out.println("--------------");
        //创建这个类的一个对象
        Object p2 = c2.newInstance();
        //将 p2 对象的 f2 属性赋值为 Bob，f2 属性即为 私有属性 name
        f2.set(p2, "Bob");
        //使用反射机制可以打破封装性，导致了java对象的属性不安全。
        System.out.println(f2.get(p2)); //Bob
        //获取构造方法
        Constructor[] constructors = c2.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.toString());//public com.ys.reflex.Person()
        }

        System.out.println("--------------");
        System.out.println(Person.class.getSigners());
        System.out.println(Person.class.getSuperclass());
        System.out.println(Object.class.getSuperclass());
        System.out.println(Person.class.getSimpleName());
        System.out.println(Person.class.getName());
        System.out.println(Person.class.getGenericSuperclass());

    }
}
