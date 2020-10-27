package com.study.thread.heima;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication {

    /**
     * @param args
     */
    public static void main(String[] args) {

        final Business business = new Business();
        new Thread(
                new Runnable() {

                    @Override
                    public void run() {

                        for (int i = 1; i <= 5; i++) {
                            business.sub(i);
                        }

                    }
                }
        ).start();

        for (int i = 1; i <= 5; i++) {
            business.main(i);
        }

    }

    static class Business {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        private boolean bShouldSub = true;

        public void sub(int i) {
            lock.lock();
            try {
                while (!bShouldSub) {
                    System.out.println("subxxxxx");
                    try {
                        condition.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 3; j++) {
                    System.out.println("sub thread sequence of " + j + ",loop of " + i);
                }
                bShouldSub = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void main(int i) {
            lock.lock();
            try {
                while (bShouldSub) {
                    System.out.println("mainxxx");
                    try {
                        condition.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 3; j++) {
                    System.out.println("main thread sequence of " + j + ",loop of " + i);
                }
                bShouldSub = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

    }
}
