package com.study.utils.mq.idempotence;

import java.util.UUID;

/**
 * --IdempotenceIdGenerator(幂等号生成类)
 */
public class IdempotenceIdGenerator {
  public String generateId() {
    return UUID.randomUUID().toString();
  }
}
