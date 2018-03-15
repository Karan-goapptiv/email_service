package com.example.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = {"com.goapptiv.services","com.example.email","email"})
@EnableAutoConfiguration
@EntityScan("com.goapptiv.entities")
@EnableJpaRepositories(basePackages="com.goapptiv.services")
public class EmailApplication {
	public static void main(String[] args) { SpringApplication.run(EmailApplication.class, args); }
}
