package com.study.utils.dubbo.demo02;

import org.apache.dubbo.common.extension.Adaptive;

/**
 * @author ldb
 * @date 2021/2/7
 * @dsscription
 */
@Adaptive
public class Banana implements Fruit{
    @Override
    public String getName(String msg) {
        System.out.println("Banana");
        return "Banana";
    }
}
