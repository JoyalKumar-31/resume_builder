package com.codewithme.www.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    private String title;

    private String description;

    private String github;

    private String liveDemo;

    @ManyToOne
    @JoinColumn(name = "resume_id")
    private Resume resume;
}