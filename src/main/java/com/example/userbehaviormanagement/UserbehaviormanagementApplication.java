package com.example.userbehaviormanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserbehaviormanagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(UserbehaviormanagementApplication.class, args);
	}

}
