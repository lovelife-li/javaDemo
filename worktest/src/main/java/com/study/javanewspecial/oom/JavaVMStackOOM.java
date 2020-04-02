package com.study.javanewspecial.oom;

/**
 * -Xss2M
 * 栈溢出原因
 * 1,在单个线程下，栈帧太大，或者虚拟机栈容量太小，当内存无法分配的时候，虚拟机抛出StackOverflowError 异常。
 * 2,不断地建立线程的方式会导致内存溢出
 */
public class JavaVMStackOOM {
    private synchronized void dontStop() throws InterruptedException {
        Thread.sleep(100000);
        System.out.println("end...");
    }
    public void stackLeakByThread(){
        while(true){
            Thread thread = new Thread(new Runnable(){
                public void run() {
                    try {
                        dontStop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();}
    }
    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
