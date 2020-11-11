package com.study.rpc.demo02;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

public class CglibTest {

    @Test
    public void testCglib() {
        DaoProxy daoProxy = new DaoProxy();
        
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallback(daoProxy);
        
        Dao dao = (Dao)enhancer.create();
        dao.update();
        dao.select();
    }

    /**
     * 我们想对select()方法与update()方法使用不同的拦截策略
     */
    @Test
    public void testCglib2() {
        DaoProxy daoProxy = new DaoProxy();
        DaoAnotherProxy daoAnotherProxy = new DaoAnotherProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        /**
         * Callback数组中有三个callback，那么：
         *
         * 方法名为"select"的方法返回的顺序为0，即使用Callback数组中的0位callback，即DaoProxy
         * 方法名不为"select"的方法返回的顺序为1，即使用Callback数组中的1位callback，即DaoAnotherProxy
         * NoOp.INSTANCE，这表示一个空Callback，即如果不想对某个方法进行拦截，可以在DaoFilter中返回2
         */
        enhancer.setCallbacks(new Callback[]{daoProxy, daoAnotherProxy,NoOp.INSTANCE});
        enhancer.setCallbackFilter(new DaoFilter());
        enhancer.setInterceptDuringConstruction(false);
        Dao dao = (Dao)enhancer.create();
        dao.update();
        dao.select();
    }

    /**
     * 如果想要在构造函数中调用update()方法时，不拦截的话，Enhancer中有一个
     * setInterceptDuringConstruction(boolean interceptDuringConstruction)方法设置为false即可，
     * 默认为true，即构造函数中调用方法也是会拦截的。
     */
    @Test
    public void testCglib3() {
        DaoProxy daoProxy = new DaoProxy();
        DaoAnotherProxy daoAnotherProxy = new DaoAnotherProxy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Dao.class);
        enhancer.setCallbacks(new Callback[]{daoProxy, daoAnotherProxy, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new DaoFilter());
        enhancer.setInterceptDuringConstruction(false);

        Dao dao = (Dao)enhancer.create();
        dao.update();
        dao.select();
    }

}