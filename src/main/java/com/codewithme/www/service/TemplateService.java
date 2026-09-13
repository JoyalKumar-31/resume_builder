package com.codewithme.www.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.codewithme.www.dto.Authresponse;
import com.codewithme.www.model.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemplateService {
	private final AuthService authService;
	public Map<String, Object> getTemplates(user user){
		Authresponse authResponse = authService.getProfile(user);
		List<String> avialableTemplates;
		Boolean isPremium = "premium".equalsIgnoreCase(authResponse.getSubscriptionPlan());
		if (isPremium) {
			avialableTemplates=List.of("01","02","03");
		}
		else {
			avialableTemplates=List.of("01");
		}
		Map<String,Object> restrictions = new HashMap<>();
		restrictions.put("avialableTemplates", avialableTemplates);
		restrictions.put("allTemplates",List.of("01","02","03"));
		restrictions.put("subscriptionPlan", authResponse.getSubscriptionPlan());
		restrictions.put("isPremium", isPremium);
		return restrictions;
	}

}
