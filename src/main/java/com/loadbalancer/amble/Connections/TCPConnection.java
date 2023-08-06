package com.loadbalancer.amble.Connections;

import java.io.IOException;
import java.net.Socket;

public class TCPConnection {

    private Socket socket;

    public TCPConnection(String host,int port) throws IOException {
        socket = new Socket(host, port);
    }

    public void close() throws IOException {
        socket.close();
    }


}
