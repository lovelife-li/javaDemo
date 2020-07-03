package com.study.utils.mq.idempotence;

/**
 * --IdempotenceStorage(接口：用来读写幂等号)
 */
public interface IdempotenceStorage {
  boolean saveIfAbsent(String idempotenceId);
  void delete(String idempotenceId);
}