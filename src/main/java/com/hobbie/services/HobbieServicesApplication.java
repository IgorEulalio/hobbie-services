package com.hobbie.services;

import com.hobbie.services.config.infrastructure.LoggableDispatcherServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class HobbieServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(HobbieServicesApplication.class, args);
	}

}
