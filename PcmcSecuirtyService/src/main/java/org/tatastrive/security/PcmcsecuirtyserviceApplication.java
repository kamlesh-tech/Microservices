package org.tatastrive.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class PcmcsecuirtyserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PcmcsecuirtyserviceApplication.class, args);
	}

}
