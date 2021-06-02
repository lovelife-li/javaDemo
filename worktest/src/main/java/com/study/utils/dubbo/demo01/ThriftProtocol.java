package com.study.utils.dubbo.demo01;

import org.apache.dubbo.common.URL;

//@Adaptive
public class ThriftProtocol implements Protocol {

    @Override
    public String print(String msg, URL url) {
        return "ThriftProtocol";
    }
}