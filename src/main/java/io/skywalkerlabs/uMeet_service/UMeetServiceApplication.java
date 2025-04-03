package io.skywalkerlabs.uMeet_service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan
@SpringBootApplication
public class UMeetServiceApplication {

	public static void main(String[] args) throws FirebaseAuthException {

		SpringApplication.run(UMeetServiceApplication.class, args);

		String uid = "e70QDKVC8yT8PTcrEa7lAvgHOFh2";

		UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);
		String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
		System.out.println(customToken);
	}
}
