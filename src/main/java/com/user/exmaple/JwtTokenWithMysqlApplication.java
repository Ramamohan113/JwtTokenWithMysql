package com.user.exmaple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtTokenWithMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTokenWithMysqlApplication.class, args);
		System.out.println("HELLO JWT");
	}

}
