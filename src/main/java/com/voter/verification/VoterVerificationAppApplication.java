package com.voter.verification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

/**
 * Consumer class which recieves the XML message
 * @author rjosula
 *
 */
@SpringBootApplication
@EnableIntegration
@ImportResource({"classpath*:/voter-app-configuration.xml"})
public class VoterVerificationAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoterVerificationAppApplication.class, args);
	}

}
