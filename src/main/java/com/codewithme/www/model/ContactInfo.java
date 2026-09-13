package com.codewithme.www.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfo {

	@Id
	private String id = UUID.randomUUID().toString();

    private String email;

    private String phone;

    private String location;

    private String linkedIn;

    private String github;

    private String website;

    @OneToOne
    @JoinColumn(name="resume_id")
    private Resume resume;

}