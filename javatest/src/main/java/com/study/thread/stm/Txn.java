package com.study.thread.stm;

// 事务接口
public interface Txn {
  <T> T get(TxnRef<T> ref);
  <T> void set(TxnRef<T> ref, T value);
}