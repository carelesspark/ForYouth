package com.jhp.foryouth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ForyouthApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForyouthApplication.class, args);
	}

}
