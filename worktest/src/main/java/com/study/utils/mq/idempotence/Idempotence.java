package com.study.utils.mq.idempotence;
// 代码目录结构

/**
 *  --Idempotence
 */

// 每个类的代码实现
public class Idempotence {
  private IdempotenceStorage storage;

  public Idempotence(IdempotenceStorage storage) {
    this.storage = storage;
  }

  public boolean saveIfAbsent(String idempotenceId) {
    return storage.saveIfAbsent(idempotenceId);
  }

  public void delete(String idempotenceId) {
    storage.delete(idempotenceId);
  }
}




