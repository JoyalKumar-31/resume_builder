package com.codewithme.www.service;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithme.www.dto.Authresponse;
import com.codewithme.www.dto.LoginRequest;
import com.codewithme.www.dto.RegisterRequest;
import com.codewithme.www.model.user;
import com.codewithme.www.repostiory.UserRepo;
import com.codewithme.www.util.JwtUtil;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Value("${app.base.url}")
    private String baseUrl;

    // Constructor injection (replacement for @RequiredArgsConstructor)
    public AuthService(UserRepo userRepo, EmailService emailService,PasswordEncoder passwordEncoder,JwtUtil jwtUtil) {
        this.userRepo = userRepo;
        this.emailService = emailService;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;
    }


    public Authresponse register(RegisterRequest request) {


        // Check existing user
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User already exists");
        }


        // Create new user object
        user newUser = new user();


        // Setting values using your user class methods
        
        newUser.setName(request.getName());

        newUser.setEmail(request.getEmail());

        newUser.setPassword(passwordEncoder.encode(request.getPassword()));

        newUser.setProfileimage(request.getProfileimage());

        newUser.setSubscriptionPlan("Basic");

        newUser.setEmailverified(false);

        newUser.setVerificationToken(
                UUID.randomUUID().toString()
        
        );
        newUser.setVerificationExpires(LocalDateTime.now().plusHours(24));
        newUser.setCreateAt(LocalDateTime.now());
        newUser.setUpdateAT(LocalDateTime.now());

        
    // Save user into database
        userRepo.save(newUser);
        sendVerificationEmail(newUser);
          return toregister(newUser);
    }
    private void sendVerificationEmail(user newUser) {
		try {
			   String link = baseUrl+"/api/auth/verify-email?token="+newUser.getVerificationToken();
			   String html =
		                "<div style='font-family:sans-serif'>" +
		                "<h2>Verify your email</h2>" +
		                "<p>Hi " + newUser.getName() + ", please confirm your email.</p>" +
		                "<a href='" + link + "'>Verify Email</a>" +
		                "<p>Or copy this link: " + link + "</p>" +
		                "<p>This link expires in 24 hours.</p>" +
		                "</div>";

		        emailService.sendHtmlEmail(
		                newUser.getEmail(),
		                "Verify Your Email",
		                html
		        );

		    } catch (Exception e) {
		        throw new RuntimeException(
		                "Failed to send verification email: " + e.getMessage()
		        );
		    }
		
	}


	public Authresponse toregister(user newuser) {
    	Authresponse response = new Authresponse();
        response.setId(newuser.getId());
        response.setName(newuser.getName());
        response.setEmail(newuser.getEmail());
        response.setProfileimage(newuser.getProfileimage());
        response.setEmailverified(newuser.isEmailverified());
        response.setSubscriptionPlan(newuser.getSubscriptionPlan());
        response.setCreateAt(newuser.getCreateAt());
        response.setUpdateAT(newuser.getUpdateAT());
        return response; 
    }
	public void verifyEmail(String token) {
	   user user = userRepo.findByVerificationToken(token)
		        .orElseThrow(() -> new RuntimeException("Invalid or expired verification token"));
	   if (user.getVerificationExpires() != null &&
			    user.getVerificationExpires().isBefore(LocalDateTime.now())) {

			    throw new RuntimeException("Token expired. Please register/login again.");
			}

			user.setEmailverified(true);
			user.setVerificationToken(null);

			userRepo.save(user); 
	   }
	public Authresponse login(LoginRequest request) {
		user existingUser = userRepo.findByEmail(request.getEmail())
		        .orElseThrow(() -> new UsernameNotFoundException("Invalid email or password"));

		if (!passwordEncoder.matches(request.getPassword(), existingUser.getPassword())) {
		    throw new UsernameNotFoundException("Invalid email or password");
		}
		if(!existingUser.isEmailverified()) {
			throw new RuntimeException("Your email is not verified. Please verify your email before logging in.");
		}
		String token=jwtUtil.generateToken(existingUser.getId());
		System.out.println("Generated Token: " + token);
		Authresponse returnValue= toregister(existingUser);
		returnValue.setToken(token);
		return returnValue;
	}
	public void resendVerification(String email)
	{
		user user = userRepo.findByEmail(email)
		        .orElseThrow(() -> new RuntimeException("User not found"));

		if (user.isEmailverified()) {
		    throw new RuntimeException("Email is already verified");
		}

		user.setVerificationToken(UUID.randomUUID().toString());
		user.setVerificationExpires(LocalDateTime.now().plusHours(24));

		userRepo.save(user);

		sendVerificationEmail(user);
	}
	public Authresponse getProfile(user user)
	{
		user existinguser=user;
		return toregister(existinguser);
	}
    
}