package com.study.thread.lock;

import java.lang.ref.WeakReference;

/**
 * @author ldb
 * @date 2020/9/19
 * @dsscription
 */
public class EntryTest {
    public static void main(String[] args) {
        Entry entry = new Entry(3,4);
        System.out.println(entry.get());

        int parallelism = 3;
        int n = (parallelism > 1) ? parallelism - 1 : 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        n = (n + 1) << 1;
        System.out.println(n);
    }
    static class Entry extends WeakReference<Integer> {
        /** The value associated with this ThreadLocal. */
        Object value;

        Entry(Integer k, Object v) {
            super(k);
            value = v;
        }
    }
}
