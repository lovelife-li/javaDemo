package com.study.utils.dubbo.demo02;

import com.study.utils.dubbo.demo01.Protocol;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * @author ldb
 * @date 2021/2/7
 * @dsscription
 */
public class StartMain {
    @Test
    public void test() {
        ExtensionLoader<Fruit> loader = ExtensionLoader.getExtensionLoader(Fruit.class);
        Fruit fruit = loader.getAdaptiveExtension();
        System.out.println(fruit.getName("aaa"));
    }
}
