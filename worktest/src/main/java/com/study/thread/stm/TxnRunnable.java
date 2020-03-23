package com.study.thread.stm;

@FunctionalInterface
public interface TxnRunnable {
    void run(Txn txn);
}