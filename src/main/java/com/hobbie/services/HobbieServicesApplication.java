package com.hobbie.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.json.JsonHttpLogFormatter;

@SpringBootApplication
public class HobbieServicesApplication {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(HobbieServicesApplication.class, args);
	}

}
