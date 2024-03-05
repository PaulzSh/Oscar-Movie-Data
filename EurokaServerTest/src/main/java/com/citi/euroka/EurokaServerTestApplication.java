package com.citi.euroka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurokaServerTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurokaServerTestApplication.class, args);
	}

}
