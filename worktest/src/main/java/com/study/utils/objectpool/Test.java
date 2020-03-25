package com.study.utils.objectpool;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author ldb
 * @date 2019-12-24 13:48
 * @description ??
 */
public class Test {

    private static DbConnection dbConnection;

    public static void main(String[] args) {
        DbConnectionFactory factory = new DbConnectionFactory();
        GenericObjectPoolConfig<DbConnection> p = new GenericObjectPoolConfig<>();
        p.setMaxIdle(20);
        p.setMaxTotal(100);
        p.setMinIdle(5);

        GenericObjectPool<DbConnection> objectPool = new GenericObjectPool<>(factory, p);
        try {
            dbConnection = objectPool.borrowObject();
            System.out.println(dbConnection.getActive());
            System.out.println(objectPool.getNumActive()+","+objectPool.getNumIdle());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (dbConnection!=null){
                objectPool.returnObject(dbConnection);
            }
        }

    }
}
