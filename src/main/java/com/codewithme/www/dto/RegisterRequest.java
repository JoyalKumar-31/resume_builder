package com.codewithme.www.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
 @NoArgsConstructor
 @AllArgsConstructor
 @Builder
public class RegisterRequest {
	
	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is required")
	private String email;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@NotBlank(message = "Name is required")
	@Size(min = 2, max = 15, message = "Name must be between 2 and 15 characters")
	private String name;

	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 15, message = "Password must be between 6 and 15 characters")
	private String password;
    private String profileimage;
	
	
        
        
}
