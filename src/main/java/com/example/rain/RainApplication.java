package com.example.rain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class RainApplication implements CommandLineRunner {

	Environment environment;

	public RainApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(RainApplication.class, args);
	}

	@Override
	public void run(String... args) {

		log.info("Active Profile: " + environment.getActiveProfiles().toString());
	}
}
