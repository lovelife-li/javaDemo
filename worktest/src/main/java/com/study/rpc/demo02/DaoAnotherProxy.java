package com.study.rpc.demo02;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 我们想对select()方法与update()方法使用不同的拦截策略
 */
public class DaoAnotherProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object object, Method method, Object[] objects, MethodProxy proxy) throws Throwable {
        
//        System.out.println("StartTime=[" + System.currentTimeMillis() + "]");
        System.out.println("aaa");
        method.invoke(object, objects);
        System.out.println("bbb");
//        System.out.println("EndTime=[" + System.currentTimeMillis() + "]");
        return object;
    }
    
}