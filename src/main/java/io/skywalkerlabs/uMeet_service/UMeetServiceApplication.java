package io.skywalkerlabs.uMeet_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@SpringBootApplication
public class UMeetServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UMeetServiceApplication.class, args);
	}

}
