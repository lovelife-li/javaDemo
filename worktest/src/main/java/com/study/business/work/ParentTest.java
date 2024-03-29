package com.study.business.work;

import java.util.concurrent.TimeUnit;

/**
 * java虚拟机(相当于进程)退出的时机是：虚拟机中所有存活的线程都是守护线程。只要还有存活的非守护线程虚拟机就不会退出，
 * 而是等待非守护线程执行完毕；反之，如果虚拟机中的线程都是守护线程，那么不管这些线程的死活java虚拟机都会退出。
 *
 * 默认线程是非守护线程，main线程是非守护线程。
 *
 * 用户线程和守护线程的区别：
 *
 * 1. 主线程结束后用户线程还会继续运行,JVM存活；主线程结束后守护线程和JVM的状态又下面第2条确定
 * 2.如果没有用户线程，都是守护线程，那么JVM结束（随之而来的是所有的一切烟消云散，包括所有的守护线程）。
 *
 * 定义：守护线程--也称“服务线程”，在没有用户线程可服务时会自动离开。
 * 优先级：守护线程的优先级比较低，用于为系统中的其它对象和线程提供服务。
 * 设置：通过setDaemon(true)来设置线程为“守护线程”；将一个用户线程设置为守护线程的方式是在线程启动用线程对象的setDaemon方法。
 *
 * example: 垃圾回收线程就是一个经典的守护线程，当我们的程序中不再有任何运行的Thread,程序就不会再产生垃圾，垃圾回收器也就无事可做，所以当垃圾回收线程是JVM上仅剩的线程时，垃圾回收线程会自动离开。它始终在低级别的状态中运行，用于实时监控和管理系统中的可回收资源。
 *
 * 生命周期：守护进程（Daemon）是运行在后台的一种特殊进程。它独立于控制终端并且周期性地执行某种任务或等待处理某些发生的事件。也就是说守护线程不依赖于终端，但是依赖于系统，与系统“同生共死”。那Java的守护线程是什么样子的呢。当JVM中所有的线程都是守护线程的时候，JVM就可以退出了；如果还有一个或以上的非守护线程则JVM不会退出。
 *
 * @author ldb
 * @date 2021/5/17
 */
public class ParentTest {
    public static void main(String[] args) {
        System.out.println("parent thread begin ");
        ChildThread t1 = new ChildThread("thread1");
        ChildThread t2 = new ChildThread("thread2");
//        t1.setDaemon(true);
//        t2.setDaemon(true);
        t1.start();
        t2.start();


        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 9 / 0;
        System.out.println("parent thread over ");

    }
}

class ChildThread extends Thread {
    private String name = null;
    public ChildThread(String name) {
        this.name = name;
    }

    @Override

    public void run() {
        System.out.println(this.name + "--child thead begin");
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println(this.name + "--child thead over");
    }

}