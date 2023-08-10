package com.loadbalancer.amble.Connections;

import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Builder
@Data
public class TCPConnection {

    private Socket socket;

    public TCPConnection(String host,int port) throws IOException {
        socket = new Socket(host, port);
    }

    public void close() throws IOException {
        socket.close();
    }

    public void forwardTCPConnection(Socket clientSocket) throws IOException {
        byte[] buffer = new byte[4096];
        int bytesRead;
        InputStream input = clientSocket.getInputStream();
        OutputStream output = this.socket.getOutputStream();
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
            output.flush();
        }
    }
}
