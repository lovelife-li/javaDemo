package com.study.utils.objectpool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class DbConnectionFactory implements PooledObjectFactory<DbConnection> {
    @Override
    public PooledObject<DbConnection> makeObject() throws Exception {
        DbConnection dbConnection = new DbConnection();
        //构造一个新的连接对象
        return new DefaultPooledObject<>(dbConnection);
    }

    @Override
    public void destroyObject(PooledObject<DbConnection> pooledObject) throws Exception {
        pooledObject.getObject().setActive(false);
    }

    @Override
    public boolean validateObject(PooledObject<DbConnection> pooledObject) {
        return pooledObject.getObject().getActive();
    }

    @Override
    public void activateObject(PooledObject<DbConnection> pooledObject) throws Exception {
        pooledObject.getObject().setActive(true);
    }

    @Override
    public void passivateObject(PooledObject<DbConnection> pooledObject) throws Exception {

    }
}
