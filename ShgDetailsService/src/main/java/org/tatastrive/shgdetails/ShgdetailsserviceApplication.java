package org.tatastrive.shgdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ShgdetailsserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShgdetailsserviceApplication.class, args);
	}

}
