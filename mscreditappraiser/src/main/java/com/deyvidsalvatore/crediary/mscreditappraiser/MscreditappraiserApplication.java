package com.deyvidsalvatore.crediary.mscreditappraiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MscreditappraiserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscreditappraiserApplication.class, args);
	}

}
