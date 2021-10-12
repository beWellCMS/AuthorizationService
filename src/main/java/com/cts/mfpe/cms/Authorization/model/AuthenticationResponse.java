package com.cts.mfpe.cms.Authorization.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@ApiModel(description = "Creating model class for generating authorization token when any customer logs in")

public class AuthenticationResponse {

    @ApiModelProperty(value = "Username of the Customer doing login")
    private String username;
    
    @ApiModelProperty(value = "Authorization token of the customer")
    private String jwtAuthToken;
    
    private long serverCurrentTime;

    private long tokenExpirationTime;

//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getJwtAuthToken() {
//		return jwtAuthToken;
//	}
//
//	public void setJwtAuthToken(String jwtAuthToken) {
//		this.jwtAuthToken = jwtAuthToken;
//	}
//
//	public long getServerCurrentTime() {
//		return serverCurrentTime;
//	}
//
//	public void setServerCurrentTime(long serverCurrentTime) {
//		this.serverCurrentTime = serverCurrentTime;
//	}
//
//	public long getTokenExpirationTime() {
//		return tokenExpirationTime;
//	}
//
//	public void setTokenExpirationTime(long tokenExpirationTime) {
//		this.tokenExpirationTime = tokenExpirationTime;
//	}
//
//	public AuthenticationResponse(String username, String jwtAuthToken, long serverCurrentTime,
//			long tokenExpirationTime) {
//		super();
//		this.username = username;
//		this.jwtAuthToken = jwtAuthToken;
//		this.serverCurrentTime = serverCurrentTime;
//		this.tokenExpirationTime = tokenExpirationTime;
//	}
//    
//    public AuthenticationResponse() {
//    	
//    }
//
//	@Override
//	public String toString() {
//		return "AuthenticationResponse [username=" + username + ", jwtAuthToken=" + jwtAuthToken
//				+ ", serverCurrentTime=" + serverCurrentTime + ", tokenExpirationTime=" + tokenExpirationTime + "]";
//	}
//    
    
    
}
