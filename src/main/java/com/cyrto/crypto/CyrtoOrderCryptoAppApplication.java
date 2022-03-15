package com.cyrto.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


/*
 * If this is a customer facing application then there might be a possibility of multiple customers using this application same time.
 * If underlying server is configured to accept 2000 http incoming request and suppose the services which we have written are highly CPU intensive 
 * or IO intensive then adding Async will process them as Non blocking operations. This way the latency will be increased   
 * 
 */
@SpringBootApplication
@EnableAsync 
public class CyrtoOrderCryptoAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyrtoOrderCryptoAppApplication.class, args);
	}

}
