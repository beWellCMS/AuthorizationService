package com.cts.mfpe.cms.Authorization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mfpe.cms.Authorization.dto.AuthenticationRequestDTO;
import com.cts.mfpe.cms.Authorization.dto.VaildatingDTO;
import com.cts.mfpe.cms.Authorization.exception.LoginException;
import com.cts.mfpe.cms.Authorization.model.AuthenticationRequest;
import com.cts.mfpe.cms.Authorization.model.AuthenticationResponse;
import com.cts.mfpe.cms.Authorization.service.AppUserDetailsService;
import com.cts.mfpe.cms.Authorization.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Api(value = "Login and Validation endpoints for Authorization Service")
public class AuthorizationController {

	//private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AuthorizationController.class);
	
	@Autowired
	private AppUserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtTokenUtil;
	

	private VaildatingDTO vaildatingDTO = new VaildatingDTO();

	@PostMapping("/login")
	@ApiOperation(value = "customerLogin", notes = "takes customer credentials and generates the unique JWT for each customer", httpMethod = "POST", response = ResponseEntity.class)
	public ResponseEntity<Object> createAuthorizationToken(
			@ApiParam(name = "customerLoginCredentials", value = "Login credentials of the Customer") @RequestBody AuthenticationRequestDTO authenticationRequestDTO)
			throws LoginException {
		
		AuthenticationRequest authenticationRequest = new AuthenticationRequest();
		authenticationRequest.setUsername(authenticationRequestDTO.getUsername());
		authenticationRequest.setPassword(authenticationRequestDTO.getPassword());
		
		log.info("BEGIN - [login(customerLoginCredentials)]");
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		log.debug("{}", userDetails);
		
		if (userDetails.getPassword().equals(authenticationRequest.getPassword())) {
			log.info("END - [login(customerLoginCredentials)]");
			return new ResponseEntity<Object>(new AuthenticationResponse(authenticationRequest.getUsername(),
					jwtTokenUtil.generateToken(userDetails), jwtTokenUtil.getCurrentTime(), jwtTokenUtil.getExpirationTime()), HttpStatus.OK);
		} 
		
		log.info("END - [login(customerLoginCredentials)]");
		throw new LoginException("Invalid Username or Password");
	}
	

	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "tokenValidation", notes = "returns boolean after validating JWT", httpMethod = "GET", response = ResponseEntity.class)
	public ResponseEntity<VaildatingDTO> validatingAuthorizationToken(
			@ApiParam(name = "Authorization", value = "JWT generated for current customer present") @RequestHeader(name = "Authorization") String tokenDup) {
		
		log.info("BEGIN - [validatingAuthorizationToken(JWT-token)]");
		String token = tokenDup.substring(7);
		try {
			UserDetails user = userDetailsService.loadUserByUsername(jwtTokenUtil.extractUsername(token));
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, user))) {
				log.debug("Token matched is Valid");
				log.info("Token matched is Valid");
				log.info("END - validate()");
				vaildatingDTO.setValidStatus(true);
				return new ResponseEntity<>(vaildatingDTO, HttpStatus.OK);
			} else {
				throw new LoginException("Invalid Token");
			}
		} catch (Exception e) {
			log.debug("Invalid token - Bad Credentials Exception");
			log.info("END Exception - validatingAuthorizationToken()");
			vaildatingDTO.setValidStatus(false);
			return new ResponseEntity<>(vaildatingDTO, HttpStatus.BAD_REQUEST);
		}
		
	}

	@GetMapping(path = "/health-check")
	public ResponseEntity<String> healthCheck() {
		
		log.info("Authorization Microservice is Up and Running....");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
}