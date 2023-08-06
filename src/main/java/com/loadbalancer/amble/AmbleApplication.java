package com.loadbalancer.amble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AmbleApplication {

	public static void main(String[] args) {
		System.out.println("Cache Application started");
		SpringApplication.run(AmbleApplication.class, args);
	}

}
