package com.study.rpc.demo03;

import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * cglib 动态代理测试
 */
public class cglibAgentTest {

    @Test
    public void testCglibAgent(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibService.class);
        // 设置enhancer的回调对象
        enhancer.setCallback(new MyMethodInterceptor());
        // 创建代理对象
        CglibService proxy= (CglibService)enhancer.create();
        // 通过代理对象调用目标方法
        proxy.sayHello();
        proxy.sayOthers("小明");
    }
}
