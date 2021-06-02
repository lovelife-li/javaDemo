package com.study.utils.dubbo.demo02;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author ldb
 * @date 2021/2/7
 * @dsscription
 */
@SPI("dubbo")
public interface Fruit {
    @Adaptive({"p"})
    String getName(String msg);
}
