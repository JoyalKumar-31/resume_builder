package com.codewithme.www.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resume")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

    @Id
    private String id = UUID.randomUUID().toString();

    private int userId;

    private String title;

    private String thumbnailLink;

    @OneToOne(mappedBy = "resume", cascade = CascadeType.ALL)
    private Template template;

    @OneToOne(mappedBy = "resume", cascade = CascadeType.ALL)
    private ProfileInfo profileInfo;

    @OneToOne(mappedBy = "resume", cascade = CascadeType.ALL)
    private ContactInfo contactInfo;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<WorkExperience> workExperiences;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Education> educations;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Project> projects;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Certification> certifications;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL)
    private List<Language> languages;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Interest> interests;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}