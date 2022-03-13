package com.cyrto.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CyrtoOrderCryptoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyrtoOrderCryptoAppApplication.class, args);
	}

}
