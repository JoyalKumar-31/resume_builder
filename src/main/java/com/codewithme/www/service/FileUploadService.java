package com.codewithme.www.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.codewithme.www.dto.Authresponse;
import com.codewithme.www.model.ProfileInfo;
import com.codewithme.www.model.Resume;
import com.codewithme.www.model.user;
import com.codewithme.www.repostiory.ResumeRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FileUploadService {

    private final Cloudinary cloudinary;
    private final ResumeRepo resumeRepo;
    private final AuthService authService;

   
    public Map<String, String> uploadSingleImage(MultipartFile file) throws IOException {

        Map<String, Object> imageUploadResult =
                cloudinary.uploader().upload(file.getBytes(), Map.of());

        return Map.of(
                "imageUrl", imageUploadResult.get("secure_url").toString()
        );
    }
    public Map<String, String> uploadResumeImages(
            String resumeId,
            user user,
            MultipartFile thumbnail,
            MultipartFile profileImage) throws IOException{

        // Step 1: Get current profile
        Authresponse response = authService.getProfile(user);

        // Step 2: Get existing resume
        Resume existingResume = resumeRepo
                .findByUserIdAndId(response.getId(), resumeId)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        // Step 3: Upload images and set resume
        Map<String, String> returnValue = new HashMap<>();

        Map<String, String> uploadResult;

        // Upload thumbnail
        if (Objects.nonNull(thumbnail)) {

            uploadResult = uploadSingleImage(thumbnail);

            existingResume.setThumbnailLink(
                    uploadResult.get("imageUrl")
            );

            returnValue.put(
                    "thumbnailLink",
                    uploadResult.get("imageUrl")
            );
        }

        // Upload profile image
        if (Objects.nonNull(profileImage)) {

            uploadResult = uploadSingleImage(profileImage);

            if (Objects.isNull(existingResume.getProfileInfo())) {
                existingResume.setProfileInfo(
                        new ProfileInfo()
                );
            }

            existingResume.getProfileInfo().setProfilePreviewUrl(
                    uploadResult.get("imageUrl")
            );

            returnValue.put(
                    "profilePreviewUrl",
                    uploadResult.get("imageUrl")
            );
        }

        // Step 4: Update details into database
        resumeRepo.save(existingResume);

        // Step 5: Return result
        returnValue.put(
                "message",
                "Images uploaded successfully"
        );

        return returnValue;
    }
}