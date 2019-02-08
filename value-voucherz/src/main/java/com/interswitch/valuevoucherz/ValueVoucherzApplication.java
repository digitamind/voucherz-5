package com.interswitch.valuevoucherz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ValueVoucherzApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValueVoucherzApplication.class, args);
	}

}

