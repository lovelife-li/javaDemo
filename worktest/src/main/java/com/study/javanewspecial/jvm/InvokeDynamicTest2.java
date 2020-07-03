package com.study.javanewspecial.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * todo
 *
 * @author ldb
 * @date 2020/06/29
 */
public class InvokeDynamicTest2 {

    public static void main(String[] args) {
        new Son().thinking();
    }
}

class GrandFather {
    void thinking() {
        System.out.println("i am grandfather");
    }
}

class Father extends GrandFather {
    void thinking() {
        System.out.println("i am father");
    }
}

class Son extends Father {
    //    void thinking() {
// 请读者在这里填入适当的代码（不能修改其他地方的代码）
// 实现调用祖父类的thinking()方法，打印"i am grandfather"
//    }
//    void thinking() {
//        try {
//            MethodType mt = MethodType.methodType(void.class);
//            MethodHandle mh = lookup().findSpecial(GrandFather.class,
//                    "thinking", mt, getClass());
//            mh.invoke(this);
//        } catch (Throwable e) {
//        }
//    }

    void thinking2() {
        try {
            MethodType mt = MethodType.methodType(void.class);
            Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            lookupImpl.setAccessible(true);
            MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null)).findSpecial(GrandFather.class,"thinking", mt, GrandFather.class);
            mh.invoke(this);
        } catch (Throwable e) {
        }
    }

    void thinking() {
        System.out.println("aaa");
        try {
            MethodType mt = MethodType.methodType(void.class,String.class);
            Field lookupImpl = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
            lookupImpl.setAccessible(true);
            MethodHandle mh = ((MethodHandles.Lookup) lookupImpl.get(null)).findVirtual(Test.class,"print",
                    mt);
            mh.invoke(new Test(),"abc");
            mh.invokeExact(new Test(),"xxx");
            mh.bindTo(new Test()).invokeExact("ggg");
            mh.bindTo(new Test()).invokeExact("ccc");
        } catch (Throwable e) {
        }
    }

}

