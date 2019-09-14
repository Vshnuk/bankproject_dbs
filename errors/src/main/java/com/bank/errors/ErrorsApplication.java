package com.bank.errors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.bank.errors.controller.LoginController;

@SpringBootApplication(scanBasePackages= {"com.bank.errors"})
public class ErrorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErrorsApplication.class, args);
	}

}
