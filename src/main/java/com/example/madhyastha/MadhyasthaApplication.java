package com.example.madhyastha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MadhyasthaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MadhyasthaApplication.class, args);
	}
	@Bean
	public String string(){
		return new String();
	}

}
