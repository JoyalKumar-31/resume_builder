 package com.codewithme.www.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
 @NoArgsConstructor
 @AllArgsConstructor
 @Builder
public class Authresponse {
	private int id;	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfileimage() {
		return profileimage;
	}
	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}
	public String getSubscriptionPlan() {
		return subscriptionPlan;
	}
	public void setSubscriptionPlan(String subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}
	public boolean isEmailverified() {
		return emailverified;
	}
	public void setEmailverified(boolean emailverified) {
		this.emailverified = emailverified;
	}
	public String getVerificationToken() {
		return verificationToken;
	}
	public void setVerificationToken(String verificationToken) {
		this.verificationToken = verificationToken;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public LocalDateTime getUpdateAT() {
		return updateAT;
	}
	public void setUpdateAT(LocalDateTime updateAT) {
		this.updateAT = updateAT;
	}
	private String name;
	private String email;
	private String password;
	private String profileimage;
	private String subscriptionPlan;
	private boolean emailverified ;
	private String verificationToken;
	private LocalDateTime createAt;
	private LocalDateTime updateAT;
	// added the token here 
	   private String token;

	    public Authresponse(String token) {
	        this.token = token;
	    }

	    public String getToken() {
	        return token;
	    }

	    public void setToken(String token) {
	        this.token = token;
	    }
	
}
	
