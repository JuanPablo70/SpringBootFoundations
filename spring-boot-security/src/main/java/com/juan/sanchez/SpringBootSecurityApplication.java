package com.juan.sanchez;

import com.juan.sanchez.utils.SpringFrameworkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@SpringBootApplication
public class SpringBootSecurityApplication {

	private static Logger logger = LoggerFactory.getLogger(SpringBootSecurityApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

	@Bean
	@Order(1)
	CommandLineRunner greeting() {
		return args -> logger.info("Hello from Spring Boot with Spring Security");
	}

	@Bean
	@Order(2)
	CommandLineRunner reportProfilesAndBeansDefinitions(ApplicationContext ctx) {
		return args -> SpringFrameworkUtils.profilesAndBeansDefinitionsReport(ctx);
	}

}
