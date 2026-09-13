package com.codewithme.www.dto;

import java.util.List;

import lombok.Data;

@Data
public class ResumeResponse {

    private String id;
    private int userId;
    private String title;
    private String thumbnailLink;

    private TemplateResponse template;
    private ProfileInfoResponse profileInfo;
    private ContactInfoResponse contactInfo;

    private List<WorkExperienceResponse> workExperiences;
    private List<EducationResponse> educations;
    private List<SkillResponse> skills;
    private List<ProjectResponse> projects;
    private List<CertificationResponse> certifications;
    private List<LanguageResponse> languages;
    private List<InterestResponse> interests;


    // =====================================================
    // TEMPLATE
    // =====================================================

    @Data
    public static class TemplateResponse {

        private String id;
        private String theme;
        private List<String> colorPalette;
    }


    // =====================================================
    // PROFILE INFO
    // =====================================================

    @Data
    public static class ProfileInfoResponse {

        private String id;
        private String fullname;
        private String designation;
        private String profilePreviewUrl;
        private String summary;
    }


    // =====================================================
    // CONTACT INFO
    // =====================================================

    @Data
    public static class ContactInfoResponse {

        private String id;
        private String email;
        private String phone;
        private String location;
        private String linkedIn;
        private String github;
        private String website;
    }


    // =====================================================
    // WORK EXPERIENCE
    // =====================================================

    @Data
    public static class WorkExperienceResponse {

        private String id;
        private String company;
        private String role;
        private String startDate;
        private String endDate;
        private String description;
    }


    // =====================================================
    // EDUCATION
    // =====================================================

    @Data
    public static class EducationResponse {

        private String id;
        private String degree;
        private String institution;
        private String startDate;
        private String endDate;
    }


    // =====================================================
    // SKILL
    // =====================================================

    @Data
    public static class SkillResponse {

        private String id;
        private String name;
        private Integer progress;
    }


    // =====================================================
    // PROJECT
    // =====================================================

    @Data
    public static class ProjectResponse {

        private String id;
        private String title;
        private String description;
        private String github;
        private String liveDemo;
    }


    // =====================================================
    // CERTIFICATION
    // =====================================================

    @Data
    public static class CertificationResponse {

        private String id;
        private String title;
        private String issuer;
        private String year;
    }


    // =====================================================
    // LANGUAGE
    // =====================================================

    @Data
    public static class LanguageResponse {

        private String id;
        private String name;
        private Integer progress;
    }


    // =====================================================
    // INTEREST
    // =====================================================

    @Data
    public static class InterestResponse {

        private String id;
        private String name;
    }
}