package com.codewithme.www.dto;

import java.util.List;

import lombok.Data;

@Data
public class UpdateResumeRequest {

    private String title;

    private String thumbnailLink;

    private CreateResumeRequest.TemplateRequest template;

    private CreateResumeRequest.ProfileInfoRequest profileInfo;

    private CreateResumeRequest.ContactInfoRequest contactInfo;

    private List<CreateResumeRequest.WorkExperienceRequest> workExperiences;

    private List<CreateResumeRequest.EducationRequest> educations;

    private List<CreateResumeRequest.SkillRequest> skills;

    private List<CreateResumeRequest.ProjectRequest> projects;

    private List<CreateResumeRequest.CertificationRequest> certifications;

    private List<CreateResumeRequest.LanguageRequest> languages;

    private List<CreateResumeRequest.InterestRequest> interests;
}