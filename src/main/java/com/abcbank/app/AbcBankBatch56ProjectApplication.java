 package com.abcbank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.primesoftinc.message.service.CoreService;
import com.primesoftinc.message.service.CoreServiceImpl;

@SpringBootApplication
@ComponentScan("com.abcbank")
@EntityScan("com.abcbank.model")
@EnableJpaRepositories("com.abcbank.repository")
public class AbcBankBatch56ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcBankBatch56ProjectApplication.class, args);
	}

	@Bean
	public CoreService coreService() {
		return new CoreServiceImpl();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
