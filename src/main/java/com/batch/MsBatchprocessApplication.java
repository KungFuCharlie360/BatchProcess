package com.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MsBatchprocessApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(MsBatchprocessApplication.class, args);
		System.exit(SpringApplication.exit(run));
	}

}
