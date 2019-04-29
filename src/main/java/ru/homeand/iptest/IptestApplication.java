package ru.homeand.iptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories
public class IptestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IptestApplication.class, args);
	}

}

