package com.study.utils.dubbo.demo01;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI("dubbo")
public interface Protocol {
    @Adaptive({"p"})
    String print(String msg, URL url);
}