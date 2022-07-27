package com.retail.Retail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@Slf4j
@SpringBootApplication
public class RetailApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(RetailApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RetailApplication.class);
	}
}
