package com.skyz.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SoupApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoupApiApplication.class, args);
	}

}
