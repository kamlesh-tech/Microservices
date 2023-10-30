package org.tatastrive.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tatastrive.apigateway.filter.JwtAuthenticationFilter;



@Configuration
public class GatewayConfig {
	
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayConfig.class);
	
	
	@Autowired
	private JwtAuthenticationFilter filter;


	
	@Bean
	protected RouteLocator routes(RouteLocatorBuilder builder ) {
		return builder.routes()
				.route("auth", security-> security.path("/auth/**")
								.filters(f -> f.addRequestHeader("Role","1")
										.addRequestHeader("Role","2")
										.addRequestHeader("Role","3")
										.addRequestHeader("Role","4")
										.addRequestHeader("Role","5")
										.addRequestHeader("Role","6")
										.filter(filter))
										.uri("lb://Pcmc-Security-Service"))
				
				.route("shg", shgDetails-> shgDetails.path("/shg/**")
						.filters(f-> f.addRequestHeader("Role", "1")
								.addRequestHeader("Role", "2")
								.addRequestHeader("Role","3")
								.addRequestHeader("Role","4")
								.addRequestHeader("Role","5")
								.addRequestHeader("Role","6")
								.filter(filter))
								.uri("lb://Shg-Details-Service"))
				
				.route("userrolemap", userrolemap-> userrolemap.path("/userrole/**")
						.filters(f-> f.addRequestHeader("Role", "1")
								.addRequestHeader("Role", "2")
								.addRequestHeader("Role","3")
								.filter(filter))
								.uri("lb://User-Role-Details-Service"))
				
				
				.build();
	}
	
}

