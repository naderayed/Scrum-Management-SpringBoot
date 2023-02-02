package com.nader.scrum.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SrumManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrumManagementApplication.class, args);
	}

}
