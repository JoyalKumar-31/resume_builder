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
public class ProfileInfo {

	@Id
	private String id = UUID.randomUUID().toString();

    private String profilePreviewUrl;

    private String fullname;

    private String designation;

    private String summary;

    @OneToOne
    @JoinColumn(name="resume_id")
    private Resume resume;

}