package com.main.recipebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RecipebookApplication {

	public static void main(String[] args) {

		SpringApplication.run(RecipebookApplication.class, args);
	}

}
