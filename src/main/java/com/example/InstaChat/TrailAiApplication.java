package com.example.InstaChat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TrailAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailAiApplication.class, args);
	}

}
