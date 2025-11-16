package com.juandavyc.evaluations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EvaluationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluationsApplication.class, args);
	}

}
