package com.codewithme.www.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithme.www.model.user;
import com.codewithme.www.service.TemplateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/templates")
@Slf4j
public class TemplatesController {
	private final TemplateService templateService;
	@GetMapping("/get_templates")
	public ResponseEntity<?> getTemplates(Authentication authentication){
		user user=(user)authentication.getPrincipal();
		  Map<String,Object> response = templateService.getTemplates(user);
		  return ResponseEntity.ok(response);
		
	}
	 

}
