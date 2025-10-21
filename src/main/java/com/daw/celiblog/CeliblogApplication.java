package com.daw.celiblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
//@EnableJpaRepositories("com.daw.celiblog.repository")
public class CeliblogApplication {
	public static void main(String[] args) {
		SpringApplication.run(CeliblogApplication.class, args);
	}

}