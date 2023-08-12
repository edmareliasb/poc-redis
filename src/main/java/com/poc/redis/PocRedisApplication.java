package com.poc.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableRedisRepositories//(basePackages = "com.poc.redis.repository")
@EnableCaching
public class PocRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocRedisApplication.class, args);
	}

}
