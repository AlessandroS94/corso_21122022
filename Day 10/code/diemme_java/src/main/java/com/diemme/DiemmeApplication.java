package com.diemme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.diemme")
public class DiemmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiemmeApplication.class, args);

	}

}
