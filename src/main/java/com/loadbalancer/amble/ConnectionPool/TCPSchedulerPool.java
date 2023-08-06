package com.loadbalancer.amble.ConnectionPool;

import com.loadbalancer.amble.Connections.TCPConnection;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class TCPSchedulerPool extends BasePooledObjectFactory<TCPConnection> {

    public TCPConnection create() throws Exception {
        // Customize this based on your needs (e.g., read host and port from config)
        return new TCPConnection("localhost", 8888);
    }

    @Override
    public PooledObject<TCPConnection> wrap(TCPConnection connection) {
        return new DefaultPooledObject<>(connection);
    }

    @Override
    public void destroyObject(PooledObject<TCPConnection> pooledObject) throws Exception {
        pooledObject.getObject().close();
    }
}
