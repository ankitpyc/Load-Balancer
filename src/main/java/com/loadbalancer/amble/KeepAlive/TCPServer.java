package com.loadbalancer.amble.KeepAlive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


@Component
public class TCPServer {
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

            } catch (Exception ex) {
                logger.warn("Exception received at the port {}", ex.getMessage());
            }
        }
    }
}
