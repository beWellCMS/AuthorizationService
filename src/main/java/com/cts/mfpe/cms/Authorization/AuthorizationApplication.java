package com.cts.mfpe.cms.Authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cts.mfpe.cms.Authorization.model.AuthenticationRequest;
import com.cts.mfpe.cms.Authorization.repository.AuthRequestRepo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@SpringBootApplication
public class AuthorizationApplication implements CommandLineRunner{

	@Autowired
	private AuthRequestRepo authRequestRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		AuthenticationRequest user1 = new AuthenticationRequest("Sahith","password1234");
		AuthenticationRequest user2 = new AuthenticationRequest("kodumuri","password1234");
		
		authRequestRepo.save(user1);
		authRequestRepo.save(user2);
	}

	
}
