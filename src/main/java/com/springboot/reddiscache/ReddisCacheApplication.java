package com.springboot.reddiscache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ReddisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReddisCacheApplication.class, args);
	}

}
