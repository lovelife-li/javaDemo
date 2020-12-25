package com.study.javanewspecial.java8;

import org.junit.Test;

import java.util.StringJoiner;

/**
 * 字符串操作
 *
 * @author ldb
 * @date 2020/12/25
 */
public class StringJoinerTest {

    @Test
    public void test01(){
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add("hello");
        stringJoiner.add("guys");
        stringJoiner.add("欢迎关注Java技术栈");
        System.out.println(stringJoiner.toString());

    }

    @Test
    public void test02(){
        StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        stringJoiner.add("hello");
        stringJoiner.add("guys");
        stringJoiner.add("欢迎关注Java技术栈");
        System.out.println(stringJoiner.toString());
    }

    @Test
    public void test03(){
        StringJoiner stringJoiner = new StringJoiner(",");
        System.out.println(stringJoiner.toString());

        StringJoiner stringJoiner2 = new StringJoiner(",", "[", "]");
        System.out.println(stringJoiner2.toString());
    }
}
