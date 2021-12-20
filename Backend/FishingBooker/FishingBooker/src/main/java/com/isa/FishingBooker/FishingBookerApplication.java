package com.isa.FishingBooker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@org.springframework.data.jpa.repository.config.EnableJpaRepositories
@SpringBootApplication
public class FishingBookerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishingBookerApplication.class, args);
	}

}
