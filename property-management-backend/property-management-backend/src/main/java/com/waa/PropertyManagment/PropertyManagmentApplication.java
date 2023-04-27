package com.waa.PropertyManagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class PropertyManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyManagmentApplication.class, args);
	}

}
