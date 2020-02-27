package com.nagarro.shop.nagarroshopconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class NagarroShopConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NagarroShopConfigServerApplication.class, args);
	}

}
