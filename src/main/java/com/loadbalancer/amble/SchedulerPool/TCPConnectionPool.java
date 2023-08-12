package com.loadbalancer.amble.SchedulerPool;

import com.loadbalancer.amble.Connections.TCPConnection;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


@Component
public class TCPConnectionPool {

    private Queue<TCPConnection> connectionPool;

    private final int maxPoolSize;

    private final int currSize = 0;
    private int availableConnections;

    TCPConnectionPool(int size){
        connectionPool = new LinkedList<>();
        this.maxPoolSize = size;
        this.availableConnections = size;
    }

    public synchronized TCPConnection getConnection() throws IOException {
        if(availableConnections - 1 == 0){
            connectionPool.add(new TCPConnection("127.0.0.1",8080));
        }
        return connectionPool.poll();
    }

    public synchronized void releaseConnection(TCPConnection connection){
        connectionPool.offer(connection);
    }

}
