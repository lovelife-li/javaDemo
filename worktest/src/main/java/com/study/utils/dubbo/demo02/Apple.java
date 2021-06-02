package com.study.utils.dubbo.demo02;

/**
 * @author ldb
 * @date 2021/2/7
 * @dsscription
 */
public class Apple implements Fruit{
    @Override
    public String getName(String msg) {
        System.out.println("apple");
        return "apple";
    }
}
