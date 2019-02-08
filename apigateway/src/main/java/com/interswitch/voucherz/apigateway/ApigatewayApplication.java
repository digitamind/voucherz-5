package com.interswitch.voucherz.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ApigatewayApplication {

	//TODO: add filter to intercept
	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}


}

