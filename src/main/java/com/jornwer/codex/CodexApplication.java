package com.jornwer.codex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CodexApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodexApplication.class, args);
	}

}
