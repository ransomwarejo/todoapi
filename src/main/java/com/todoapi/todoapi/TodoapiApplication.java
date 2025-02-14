package com.todoapi.todoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoapiApplication {

	public static void main(String[] args) {

		// Affichage des variables d'environnement
		System.out.println("DB_USER: " + System.getenv("DB_USER"));
		System.out.println("DB_PASSWORD: " + System.getenv("DB_PASSWORD"));

		SpringApplication.run(TodoapiApplication.class, args);
	}


}
