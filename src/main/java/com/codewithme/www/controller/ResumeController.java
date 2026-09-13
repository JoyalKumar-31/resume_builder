package com.codewithme.www.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithme.www.dto.CreateResumeRequest;
import com.codewithme.www.dto.ResumeResponse;
import com.codewithme.www.dto.UpdateResumeRequest;
import com.codewithme.www.model.user;
import com.codewithme.www.service.FileUploadService;
import com.codewithme.www.service.ResumeService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/resumes")
@AllArgsConstructor
@Slf4j
public class ResumeController {

    private final ResumeService resumeService;
    private final FileUploadService fileUploadService;


    // =========================================================
    // CREATE
    // =========================================================

    @PostMapping("/create")
    public ResponseEntity<?> createResume(
            @Valid @RequestBody CreateResumeRequest request,
            Authentication authentication) {

        user currentUser =
                (user) authentication.getPrincipal();

        ResumeResponse response =
                resumeService.createResume(
                        request,
                        currentUser
                );

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // GET ALL RESUMES
    // =========================================================

    @GetMapping("/take")
    public ResponseEntity<?> getResumes(
            Authentication authentication) {

        user currentUser =
                (user) authentication.getPrincipal();

        List<ResumeResponse> responses =
                resumeService.getresumes(currentUser);

        return ResponseEntity.ok(responses);
    }


    // =========================================================
    // GET RESUME BY ID
    // =========================================================

    @GetMapping("/takeid/{id}")
    public ResponseEntity<?> getResumeById(
            @PathVariable String id,
            Authentication authentication) {

        user currentUser =
                (user) authentication.getPrincipal();

        ResumeResponse response =
                resumeService.getResumeById(
                        id,
                        currentUser
                );

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // UPDATE RESUME
    // =========================================================

    @PutMapping("/{id}")
    public ResponseEntity<?> updateResume(
            @PathVariable String id,
            @RequestBody UpdateResumeRequest request,
            Authentication authentication) {

        user currentUser =
                (user) authentication.getPrincipal();

        ResumeResponse response =
                resumeService.updateResume(
                        id,
                        request,
                        currentUser
                );

        return ResponseEntity.ok(response);
    }


    // =========================================================
    // DELETE RESUME
    // =========================================================

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResume(
            @PathVariable String id,
            Authentication authentication) {

        user currentUser =
                (user) authentication.getPrincipal();

        resumeService.deleteResume(
                id,
                currentUser
        );

        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "Resume deleted successfully"
                )
        );
    }


    // =========================================================
    // IMAGE UPLOAD
    // =========================================================

    @PutMapping("/image_upload/{id}")
    public ResponseEntity<?> uploadResumeImages(
            @PathVariable String id,

            @RequestPart(
                    value = "thumbnail",
                    required = false
            )
            MultipartFile thumbnail,

            @RequestPart(
                    value = "profileImage",
                    required = false
            )
            MultipartFile profileImage,

            Authentication authentication) {

        user currentUser =
                (user) authentication.getPrincipal();

        try {

            Map<String, String> response =
                    fileUploadService.uploadResumeImages(
                            id,
                            currentUser,
                            thumbnail,
                            profileImage
                    );

            return ResponseEntity.ok(response);

        } catch (IOException e) {

            return ResponseEntity
                    .internalServerError()
                    .body(
                            "Image upload failed: "
                            + e.getMessage()
                    );
        }
    }
}