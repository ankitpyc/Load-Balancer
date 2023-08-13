package com.loadbalancer.amble.SchedulerPool;

import com.loadbalancer.amble.Connections.TCPConnection;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;
import java.util.*;


@Component
public class TCPConnectionPool {

    private Map<String, Queue<TCPConnection>> connectionPool;

    private final int maxPoolSize;

    private final int currSize = 0;
    private int availableConnections;

    TCPConnectionPool(int size){
        connectionPool = new HashMap<>();
        this.maxPoolSize = size;
        this.availableConnections = size;
    }

    public TCPConnection getConnection(String host,int port) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        java.lang.String key = stringBuilder.append(host).append(":").append(port).toString();
        synchronized(TCPConnection.class) {
            if (connectionPool.get(key).size() == 0) {
                connectionPool.get(key).add(new TCPConnection(host, port));
            }
            return connectionPool.get(key).poll();
        }
    }

    public synchronized void releaseConnection(TCPConnection connection){
        Socket socket = connection.getSocket();
        String key = socket.getInetAddress().getHostName() + ":" + socket.getPort();
        connectionPool.get(key).offer(connection);
    }

}
