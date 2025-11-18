package com.juandavyc.ranking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class RankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RankingApplication.class, args);
	}

}
