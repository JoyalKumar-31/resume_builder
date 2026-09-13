package com.codewithme.www.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateResumeRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String thumbnailLink;

    @Valid
    private TemplateRequest template;

    @Valid
    private ProfileInfoRequest profileInfo;

    @Valid
    private ContactInfoRequest contactInfo;

    @Valid
    private List<WorkExperienceRequest> workExperiences;

    @Valid
    private List<EducationRequest> educations;

    @Valid
    private List<SkillRequest> skills;

    @Valid
    private List<ProjectRequest> projects;

    @Valid
    private List<CertificationRequest> certifications;

    @Valid
    private List<LanguageRequest> languages;

    @Valid
    private List<InterestRequest> interests;

    @Data
    public static class TemplateRequest {

        @NotBlank(message = "Theme is required")
        private String theme;

        private List<String> colorPalette;
    }

    @Data
    public static class ProfileInfoRequest {

        @NotBlank(message = "Full Name is required")
        private String fullname;

        @NotBlank(message = "Designation is required")
        private String designation;

        private String profilePreviewUrl;

        @NotBlank(message = "Summary is required")
        private String summary;
    }

    @Data
    public static class ContactInfoRequest {

        @Email(message = "Invalid email")
        @NotBlank(message = "Email is required")
        private String email;

        @NotBlank(message = "Phone is required")
        private String phone;

        @NotBlank(message = "Location is required")
        private String location;

        private String linkedIn;
        private String github;
        private String website;
    }

    @Data
    public static class WorkExperienceRequest {

        @NotBlank(message = "Company is required")
        private String company;

        @NotBlank(message = "Role is required")
        private String role;

        @NotBlank(message = "Start Date is required")
        private String startDate;

        private String endDate;

        @NotBlank(message = "Description is required")
        private String description;
    }

    @Data
    public static class EducationRequest {

        @NotBlank(message = "Degree is required")
        private String degree;

        @NotBlank(message = "Institution is required")
        private String institution;

        @NotBlank(message = "Start Date is required")
        private String startDate;

        private String endDate;
    }

    @Data
    public static class SkillRequest {

        @NotBlank(message = "Skill name is required")
        private String name;

        @Min(0)
        @Max(100)
        private Integer progress;
    }

    @Data
    public static class ProjectRequest {

        @NotBlank(message = "Project title is required")
        private String title;

        @NotBlank(message = "Description is required")
        private String description;

        private String github;

        private String liveDemo;
    }

    @Data
    public static class CertificationRequest {

        @NotBlank(message = "Certification title is required")
        private String title;

        @NotBlank(message = "Issuer is required")
        private String issuer;

        @NotBlank(message = "Year is required")
        private String year;
    }

    @Data
    public static class LanguageRequest {

        @NotBlank(message = "Language name is required")
        private String name;

        @Min(0)
        @Max(100)
        private Integer progress;
    }

    @Data
    public static class InterestRequest {

        @NotBlank(message = "Interest is required")
        private String name;
    }
}