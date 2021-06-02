package com.study.utils.dubbo.demo01;

import org.apache.dubbo.common.URL;

public class SpringCloudProtocol implements Protocol {

    @Override
    public String print(String msg, URL url) {
        return "SpringCloudProtocol";
    }
}