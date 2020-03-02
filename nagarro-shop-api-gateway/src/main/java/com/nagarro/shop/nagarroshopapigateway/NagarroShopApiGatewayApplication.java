package com.nagarro.shop.nagarroshopapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NagarroShopApiGatewayApplication {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("resource", r -> r.path("/resource")
//					.filters(f -> f.filters(filterFactory.apply())
//									.removeRequestHeader("Cookie")) // Prevents cookie being sent downstream
					.uri("http://resource:9000")) // Taking advantage of docker naming
				.build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(NagarroShopApiGatewayApplication.class, args);
	}

}
