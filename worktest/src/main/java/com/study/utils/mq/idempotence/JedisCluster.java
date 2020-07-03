package com.study.utils.mq.idempotence;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.Set;

/**
 * redis集成
 *
 * @author ldb
 * @date 2020/06/10
 */
public class JedisCluster {


    public JedisCluster(Set<HostAndPort> redisNodes, GenericObjectPoolConfig config) {
    }

    public Long setnx(String idempotenceId, String s) {
        return 0L;
    }


    public void del(String idempotenceId) {
    }
}
