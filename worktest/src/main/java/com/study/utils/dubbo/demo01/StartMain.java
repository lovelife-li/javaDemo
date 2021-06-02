package com.study.utils.dubbo.demo01;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;

/**
 * @author ldb
 * @date 2021/2/6
 * @dsscription
 */
public class StartMain {

    @Test
    public void test1() {
        ExtensionLoader<Protocol> loader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol protocol = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test");
        System.out.println(protocol.print("msg", url));
    }

    @Test
    public void test2() {
        ExtensionLoader<Protocol> loader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol protocol = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test?protocol=cloud");
        System.out.println(protocol.print("msg", url));
    }

    @Test
    public void test3() {
        ExtensionLoader<Protocol> loader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol protocol = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test?protocol=cloud");
        System.out.println(protocol.print("msg", url));
    }

    @Test
    public void test4() {
        ExtensionLoader<Protocol> loader = ExtensionLoader.getExtensionLoader(Protocol.class);
        Protocol protocol = loader.getAdaptiveExtension();
        URL url = URL.valueOf("test://localhost/test?p=cloud");
        System.out.println(protocol.print("msg", url));
    }



}
