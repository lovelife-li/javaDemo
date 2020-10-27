package com.study.rpc.demo02;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Callback数组中有三个callback，那么：
 *
 * 方法名为"select"的方法返回的顺序为0，即使用Callback数组中的0位callback，即DaoProxy
 * 方法名不为"select"的方法返回的顺序为1，即使用Callback数组中的1位callback，即DaoAnotherProxy
 */
public class DaoFilter implements CallbackFilter {

    @Override
    public int accept(Method method) {
        if ("select".equals(method.getName())) {
            return 1;
        }else if ("update".equals(method.getName())){
            return 0;
        }
        return 2;
    }
    
}