package com.mk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.mk")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
