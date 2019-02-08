package com.interswitch.voucherz.voucherzconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableConfigServer
@SpringBootApplication
public class VoucherzConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoucherzConfigApplication.class, args);
	}

}

