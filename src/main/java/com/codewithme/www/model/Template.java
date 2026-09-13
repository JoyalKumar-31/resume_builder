package com.codewithme.www.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.ElementCollection;
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
public class Template {

	@Id
	private String id = UUID.randomUUID().toString();

    private String theme;

    @ElementCollection
    private List<String> colorPalette;

    @OneToOne
    @JoinColumn(name="resume_id")
    private Resume resume;

}