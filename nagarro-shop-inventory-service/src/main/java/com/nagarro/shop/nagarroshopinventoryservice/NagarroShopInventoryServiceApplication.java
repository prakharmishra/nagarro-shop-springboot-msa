package com.nagarro.shop.nagarroshopinventoryservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class NagarroShopInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NagarroShopInventoryServiceApplication.class, args);
	}

	
}

@RestController
class SomeClass {
	@Value("${demo}")
	private String demo;
	
	@GetMapping("/")
	public String getDemo() {
		return demo;
	}
}