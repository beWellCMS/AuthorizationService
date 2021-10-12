package com.cts.mfpe.cms.Authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.mfpe.cms.Authorization.model.AuthenticationRequest;

@Repository
public interface AuthRequestRepo extends JpaRepository<AuthenticationRequest, String> {
	
}
 