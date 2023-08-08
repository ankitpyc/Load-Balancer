package com.loadbalancer.amble;

import com.loadbalancer.amble.KeepAlive.TCPServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = "com.loadbalancer.*")
@EnableScheduling
public class AmbleApplication {
	public static void main(String[] args) {
		SpringApplication.run(AmbleApplication.class, args);
		TCPServer tcpServer = new TCPServer();
		//Start the TCP Server
		tcpServer.keepServerRunning();
	}
}
