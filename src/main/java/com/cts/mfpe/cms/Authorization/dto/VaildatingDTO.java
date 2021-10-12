package com.cts.mfpe.cms.Authorization.dto;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VaildatingDTO {
    @Id
    @JsonProperty
    private boolean validStatus;

//	public boolean isValidStatus() {
//		return validStatus;
//	}
//
//	public void setValidStatus(boolean validStatus) {
//		this.validStatus = validStatus;
//	}
//
//	public VaildatingDTO(boolean validStatus) {
//		super();
//		this.validStatus = validStatus;
//	}
//
//	@Override
//	public String toString() {
//		return "VaildatingDTO [validStatus=" + validStatus + "]";
//	}
//        
//	public VaildatingDTO() {
//		
//	}
    
}
