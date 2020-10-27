package com.study.rpc.demo03;

public class CglibService {
    public CglibService() {
        System.out.println("CglibDao 构造方法");
    }

    /**
     * 该方法不能被子类覆盖,Cglib是无法代理final修饰的方法的
     * @param name
     * @return
     */
    final public String sayOthers(String name){
        System.out.println("CglibDao final sayOthers:"+name);
        return null;
    }

    public void sayHello(){
        System.out.println("CglibDao:sayHello");
    }
}
