package com.study.utils.reflect.demo04;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleTest {

    public String toString(String s){
        return "Hello"+s+"MethodHandle";
    }


    public static void main(String[] args) {
        MethodHandleTest mht = new MethodHandleTest();
        MethodHandle mh = getString();
        try {
            String o = (String) mh.invokeExact(mht, "ssss");
            System.out.println(o);
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        
        MethodHandle methodHandle = mh.bindTo(mht);
        try {
            String ssss = (String) methodHandle.invokeWithArguments("ssss");
            System.out.println(ssss);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }



    public static MethodHandle getString(){
        MethodType mt = MethodType.methodType(String.class,String.class);
        MethodHandle mh = null;
        try {
            mh = MethodHandles.lookup().findVirtual(MethodHandleTest.class,"toString",mt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mh;
    }
}
