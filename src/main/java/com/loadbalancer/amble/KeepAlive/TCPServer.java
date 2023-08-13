package com.loadbalancer.amble.KeepAlive;

import com.loadbalancer.amble.ConnectionPool.WorkerPool;
import com.loadbalancer.amble.Connections.TCPConnection;
import com.loadbalancer.amble.SchedulerPool.TCPConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


@Component
public class TCPServer {

    @Autowired
    WorkerPool workerPool;

    @Autowired
    TCPConnectionPool tcpConnectionPool;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public void keepServerRunning() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("TCP Server has started at port : {}", serverSocket.getLocalPort());
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                logger.info("Connection established with the Client : {}", clientSocket.toString());
                workerPool.getWorkerService().submit(()->{
                    TCPConnection serSocket = null;
                    try {
                        //TODO: Add logic here to get Server where this request should be forwarded.
                        serSocket = tcpConnectionPool.getConnection("127.0.0.1",8088);
                        serSocket.forwardTCPConnection(clientSocket);
                    } catch (IOException e) {
                        tcpConnectionPool.releaseConnection(serSocket);
                    }
                });
            } catch (Exception ex) {
                logger.warn("Exception received at the port {}", ex.getMessage());
            }
        }
    }
}
