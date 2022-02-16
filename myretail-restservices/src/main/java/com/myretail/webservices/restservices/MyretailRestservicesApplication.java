package com.myretail.webservices.restservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class MyretailRestservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyretailRestservicesApplication.class, args);
	}

}
