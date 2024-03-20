package com.diemme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories({"com.diemme.repository.mongo"})
public class DiemmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiemmeApplication.class, args);

	}

}
