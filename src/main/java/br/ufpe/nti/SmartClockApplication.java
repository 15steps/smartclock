package br.ufpe.nti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "br.ufpe.nti.model")
@EnableJpaRepositories(basePackages = "br.ufpe.nti.controller.repository")
@SpringBootApplication(scanBasePackages = "br.ufpe.nti")
public class SmartClockApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartClockApplication.class, args);
	}
}
