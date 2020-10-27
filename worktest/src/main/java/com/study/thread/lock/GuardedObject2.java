package com.study.thread.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

class GuardedObject2<T> {
    // 受保护的对象
    T obj;
    final Lock lock = new ReentrantLock();
    final Condition done = lock.newCondition();
    final int timeout = 2;
    // 保存所有 GuardedObject
    final static Map<Object, GuardedObject2> gos = new ConcurrentHashMap<>();
    // 静态方法创建 GuardedObject
    static <K> GuardedObject2 create(K key) {
        GuardedObject2 go = new GuardedObject2();
        gos.put(key, go);
        return go;
    }
    static <K, T> void fireEvent(K key, T obj) {
        GuardedObject2 go = gos.remove(key);
        if (go != null) {
            go.onChanged(obj);
        }
    }
    // 获取受保护对象
    T get(Predicate<T> p) {
        lock.lock();
        try {
            //MESA 管程推荐写法
            while (!p.test(obj)) {
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        // 返回非空的受保护对象
        return obj;
    }
    // 事件通知方法
    void onChanged(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }
}