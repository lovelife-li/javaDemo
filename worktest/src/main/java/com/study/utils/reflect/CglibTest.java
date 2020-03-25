package com.study.utils.reflect;

import net.sf.cglib.proxy.Enhancer;

public class CglibTest {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserDao.class);  // 设置超类，cglib是通过继承来实现的
        enhancer.setCallback(new LogInterceptor());

        UserDao dao = (UserDao)enhancer.create();   // 创建代理类
        dao.update();
        dao.select();
    }
}
