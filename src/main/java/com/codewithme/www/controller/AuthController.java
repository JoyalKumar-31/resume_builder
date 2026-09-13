package com.codewithme.www.controller;

import static com.codewithme.www.util.AppConstants.AUTH_CONTROLLER;
import static com.codewithme.www.util.AppConstants.LOGIN;
import static com.codewithme.www.util.AppConstants.REGISTER;
import static com.codewithme.www.util.AppConstants.RESEND_VERIFICATION;
import static com.codewithme.www.util.AppConstants.UPLOAD_IMAGE;
import static com.codewithme.www.util.AppConstants.VERIFY_EMAIL;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithme.www.dto.Authresponse;
import com.codewithme.www.dto.LoginRequest;
import com.codewithme.www.dto.RegisterRequest;
import com.codewithme.www.model.user;
import com.codewithme.www.service.AuthService;
import com.codewithme.www.service.FileUploadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AUTH_CONTROLLER)
public class AuthController {

    private final AuthService authService;
    private final FileUploadService fileUploadService;
    
    public AuthController(AuthService authService,FileUploadService fileUploadService) {
        this.authService = authService;
        this.fileUploadService=fileUploadService;
    }
    @PostMapping(REGISTER)
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {

            Authresponse response = authService.register(request);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);       
         
    }
    @GetMapping(VERIFY_EMAIL)
     public ResponseEntity<?> verifyEmail(@RequestParam String token) 
     {
    	authService.verifyEmail(token);
    	return ResponseEntity.status(HttpStatus.OK)
    	        .body(Map.of("message", "Email verified successfully"));
     }
    @PostMapping(UPLOAD_IMAGE)
    public ResponseEntity<?> uploadImage(
            @RequestPart("image") MultipartFile file) throws IOException {

        Map<String, String> response = fileUploadService.uploadSingleImage(file);

        return ResponseEntity.ok(response);
    }
    @PostMapping(LOGIN)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
    	Authresponse response=authService.login(request);
    	return ResponseEntity.ok(response);
    }
    @PostMapping(RESEND_VERIFICATION)
    public ResponseEntity<?> resendVerification(@RequestBody Map<String,String> body){
    	 String email = body.get("email");

    	    if (Objects.isNull(email) || email.trim().isEmpty()) {
    	        return ResponseEntity.badRequest().body("Email is required.");
    	    }

    	    try {
    	        authService.resendVerification(email);
    	        return ResponseEntity.ok("Verification email sent successfully.");
    	    } catch (RuntimeException e) {
    	        return ResponseEntity.badRequest().body(e.getMessage());
    	    }
    	
    	 
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication){
    	// get princle object 
    	  user existinguser=(user)authentication.getPrincipal();//cast user object
    	// call the service method 
    	  Authresponse currentprofile=authService.getProfile(existinguser);
    	return ResponseEntity.ok(currentprofile);
    }
    
    
    
}