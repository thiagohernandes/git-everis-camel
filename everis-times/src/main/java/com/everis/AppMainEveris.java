package com.everis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.everis" })
public class AppMainEveris {

	public static void main(String[] args) {
		SpringApplication.run(AppMainEveris.class, args);
	}

}
