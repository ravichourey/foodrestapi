package com.myone.foodiesapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.myone.foodiesapi.repository")

public class FoodiesapiApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("MONGODB_URL", dotenv.get("MONGODB_URL"));
		System.setProperty("JWT_SECRET_KEY", dotenv.get("JWT_SECRET_KEY"));
		System.setProperty("RAZORPAY_KEY", dotenv.get("RAZORPAY_KEY"));
		System.setProperty("RAZORPAY_SECRET_KEY", dotenv.get("RAZORPAY_SECRET_KEY"));
		SpringApplication.run(FoodiesapiApplication.class, args);
	}
}

