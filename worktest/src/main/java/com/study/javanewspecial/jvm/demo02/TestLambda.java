package com.study.javanewspecial.jvm.demo02;

/**
 * todo
 *
 * @author ldb
 * @date 2020/07/17
 */
public class TestLambda {

    public static void main(String[] args) {
        System.out.println(strHandler("abcdef",x->{
            return x.substring(1);
        }));
    }

    //需求：用于处理字符串
    public static String strHandler(String str, MyFunction mf){
        return mf.getValue(str);
    }
}
@FunctionalInterface
interface MyFunction {

    String getValue(String str);

}