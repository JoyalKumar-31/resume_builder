package com.codewithme.www.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codewithme.www.dto.Authresponse;
import com.codewithme.www.dto.CreateResumeRequest;
import com.codewithme.www.dto.ResumeResponse;
import com.codewithme.www.dto.UpdateResumeRequest;
import com.codewithme.www.model.Certification;
import com.codewithme.www.model.ContactInfo;
import com.codewithme.www.model.Education;
import com.codewithme.www.model.Interest;
import com.codewithme.www.model.Language;
import com.codewithme.www.model.ProfileInfo;
import com.codewithme.www.model.Project;
import com.codewithme.www.model.Resume;
import com.codewithme.www.model.Skill;
import com.codewithme.www.model.Template;
import com.codewithme.www.model.WorkExperience;
import com.codewithme.www.model.user;
import com.codewithme.www.repostiory.ResumeRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeService {

    private final ResumeRepo resumeRepo;
    private final AuthService authService;


    // =========================================================
    // CREATE RESUME
    // =========================================================

    public ResumeResponse createResume(
            CreateResumeRequest request,
            user user) {

        Authresponse profile =
                authService.getProfile(user);

        Resume resume = new Resume();

        resume.setUserId(profile.getId());

        resume.setTitle(
                request.getTitle()
        );

        resume.setThumbnailLink(
                request.getThumbnailLink()
        );


        // =====================================================
        // TEMPLATE
        // =====================================================

        if (request.getTemplate() != null) {

            Template template = new Template();

            template.setTheme(
                    request.getTemplate().getTheme()
            );

            template.setColorPalette(
                    request.getTemplate()
                            .getColorPalette()
            );

            template.setResume(resume);

            resume.setTemplate(template);
        }


        // =====================================================
        // PROFILE INFO
        // =====================================================

        if (request.getProfileInfo() != null) {

            ProfileInfo profileInfo =
                    new ProfileInfo();

            profileInfo.setFullname(
                    request.getProfileInfo()
                            .getFullname()
            );

            profileInfo.setDesignation(
                    request.getProfileInfo()
                            .getDesignation()
            );

            profileInfo.setProfilePreviewUrl(
                    request.getProfileInfo()
                            .getProfilePreviewUrl()
            );

            profileInfo.setSummary(
                    request.getProfileInfo()
                            .getSummary()
            );

            profileInfo.setResume(resume);

            resume.setProfileInfo(profileInfo);
        }


        // =====================================================
        // CONTACT INFO
        // =====================================================

        if (request.getContactInfo() != null) {

            ContactInfo contactInfo =
                    new ContactInfo();

            contactInfo.setEmail(
                    request.getContactInfo()
                            .getEmail()
            );

            contactInfo.setPhone(
                    request.getContactInfo()
                            .getPhone()
            );

            contactInfo.setLocation(
                    request.getContactInfo()
                            .getLocation()
            );

            contactInfo.setLinkedIn(
                    request.getContactInfo()
                            .getLinkedIn()
            );

            contactInfo.setGithub(
                    request.getContactInfo()
                            .getGithub()
            );

            contactInfo.setWebsite(
                    request.getContactInfo()
                            .getWebsite()
            );

            contactInfo.setResume(resume);

            resume.setContactInfo(contactInfo);
        }


        // =====================================================
        // WORK EXPERIENCE
        // =====================================================

        if (request.getWorkExperiences() != null) {

            List<WorkExperience> workExperiences =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.WorkExperienceRequest workRequest
                    : request.getWorkExperiences()
            ) {

                WorkExperience work =
                        new WorkExperience();

                work.setCompany(
                        workRequest.getCompany()
                );

                work.setRole(
                        workRequest.getRole()
                );

                work.setStartDate(
                        workRequest.getStartDate()
                );

                work.setEndDate(
                        workRequest.getEndDate()
                );

                work.setDescription(
                        workRequest.getDescription()
                );

                work.setResume(resume);

                workExperiences.add(work);
            }

            resume.setWorkExperiences(
                    workExperiences
            );
        }


        // =====================================================
        // EDUCATION
        // =====================================================

        if (request.getEducations() != null) {

            List<Education> educations =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.EducationRequest educationRequest
                    : request.getEducations()
            ) {

                Education education =
                        new Education();

                education.setDegree(
                        educationRequest.getDegree()
                );

                education.setInstitution(
                        educationRequest.getInstitution()
                );

                education.setStartDate(
                        educationRequest.getStartDate()
                );

                education.setEndDate(
                        educationRequest.getEndDate()
                );

                education.setResume(resume);

                educations.add(education);
            }

            resume.setEducations(educations);
        }


        // =====================================================
        // SKILLS
        // =====================================================

        if (request.getSkills() != null) {

            List<Skill> skills =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.SkillRequest skillRequest
                    : request.getSkills()
            ) {

                Skill skill = new Skill();

                skill.setName(
                        skillRequest.getName()
                );

                skill.setProgress(
                        skillRequest.getProgress()
                );

                skill.setResume(resume);

                skills.add(skill);
            }

            resume.setSkills(skills);
        }


        // =====================================================
        // PROJECTS
        // =====================================================

        if (request.getProjects() != null) {

            List<Project> projects =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.ProjectRequest projectRequest
                    : request.getProjects()
            ) {

                Project project =
                        new Project();

                project.setTitle(
                        projectRequest.getTitle()
                );

                project.setDescription(
                        projectRequest.getDescription()
                );

                project.setGithub(
                        projectRequest.getGithub()
                );

                project.setLiveDemo(
                        projectRequest.getLiveDemo()
                );

                project.setResume(resume);

                projects.add(project);
            }

            resume.setProjects(projects);
        }


        // =====================================================
        // CERTIFICATIONS
        // =====================================================

        if (request.getCertifications() != null) {

            List<Certification> certifications =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.CertificationRequest certificationRequest
                    : request.getCertifications()
            ) {

                Certification certification =
                        new Certification();

                certification.setTitle(
                        certificationRequest.getTitle()
                );

                certification.setIssuer(
                        certificationRequest.getIssuer()
                );

                certification.setYear(
                        certificationRequest.getYear()
                );

                certification.setResume(resume);

                certifications.add(certification);
            }

            resume.setCertifications(certifications);
        }


        // =====================================================
        // LANGUAGES
        // =====================================================

        if (request.getLanguages() != null) {

            List<Language> languages =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.LanguageRequest languageRequest
                    : request.getLanguages()
            ) {

                Language language =
                        new Language();

                language.setName(
                        languageRequest.getName()
                );

                language.setProgress(
                        languageRequest.getProgress()
                );

                language.setResume(resume);

                languages.add(language);
            }

            resume.setLanguages(languages);
        }


        // =====================================================
        // INTERESTS
        // =====================================================

        if (request.getInterests() != null) {

            List<Interest> interests =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.InterestRequest interestRequest
                    : request.getInterests()
            ) {

                Interest interest =
                        new Interest();

                interest.setName(
                        interestRequest.getName()
                );

                interest.setResume(resume);

                interests.add(interest);
            }

            resume.setInterests(interests);
        }


        // =====================================================
        // SAVE
        // =====================================================

        Resume savedResume =
                resumeRepo.save(resume);

        return mapToResponse(savedResume);
    }


    // =========================================================
    // GET ALL RESUMES
    // =========================================================

    public List<ResumeResponse> getresumes(user user) {

        Authresponse profile =
                authService.getProfile(user);

        List<Resume> resumes =
                resumeRepo
                        .findByUserIdOrderByUpdatedAtDesc(
                                profile.getId()
                        );

        return resumes
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }


    // =========================================================
    // GET RESUME BY ID
    // =========================================================

    public ResumeResponse getResumeById(
            String id,
            user user) {

        Resume resume =
                resumeRepo
                        .findByUserIdAndId(
                                user.getId(),
                                id
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Resume not found"
                                )
                        );

        return mapToResponse(resume);
    }


    // =========================================================
    // UPDATE RESUME
    // =========================================================

    public ResumeResponse updateResume(
            String id,
            UpdateResumeRequest request,
            user user) {

        Resume resume =
                resumeRepo
                        .findByUserIdAndId(
                                user.getId(),
                                id
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Resume not found"
                                )
                        );


        // =====================================================
        // BASIC FIELDS
        // =====================================================

        if (request.getTitle() != null) {

            resume.setTitle(
                    request.getTitle()
            );
        }

        if (request.getThumbnailLink() != null) {

            resume.setThumbnailLink(
                    request.getThumbnailLink()
            );
        }


        // =====================================================
        // TEMPLATE
        // =====================================================

        if (request.getTemplate() != null) {

            Template template =
                    resume.getTemplate();

            if (template == null) {

                template = new Template();

                template.setResume(resume);

                resume.setTemplate(template);
            }

            template.setTheme(
                    request.getTemplate().getTheme()
            );

            template.setColorPalette(
                    request.getTemplate()
                            .getColorPalette()
            );
        }


        // =====================================================
        // PROFILE INFO
        // =====================================================

        if (request.getProfileInfo() != null) {

            ProfileInfo profileInfo =
                    resume.getProfileInfo();

            if (profileInfo == null) {

                profileInfo =
                        new ProfileInfo();

                profileInfo.setResume(resume);

                resume.setProfileInfo(profileInfo);
            }

            profileInfo.setFullname(
                    request.getProfileInfo()
                            .getFullname()
            );

            profileInfo.setDesignation(
                    request.getProfileInfo()
                            .getDesignation()
            );

            profileInfo.setProfilePreviewUrl(
                    request.getProfileInfo()
                            .getProfilePreviewUrl()
            );

            profileInfo.setSummary(
                    request.getProfileInfo()
                            .getSummary()
            );
        }


        // =====================================================
        // CONTACT INFO
        // =====================================================

        if (request.getContactInfo() != null) {

            ContactInfo contactInfo =
                    resume.getContactInfo();

            if (contactInfo == null) {

                contactInfo =
                        new ContactInfo();

                contactInfo.setResume(resume);

                resume.setContactInfo(contactInfo);
            }

            contactInfo.setEmail(
                    request.getContactInfo()
                            .getEmail()
            );

            contactInfo.setPhone(
                    request.getContactInfo()
                            .getPhone()
            );

            contactInfo.setLocation(
                    request.getContactInfo()
                            .getLocation()
            );

            contactInfo.setLinkedIn(
                    request.getContactInfo()
                            .getLinkedIn()
            );

            contactInfo.setGithub(
                    request.getContactInfo()
                            .getGithub()
            );

            contactInfo.setWebsite(
                    request.getContactInfo()
                            .getWebsite()
            );
        }


        // =====================================================
        // WORK EXPERIENCES
        // =====================================================

        if (request.getWorkExperiences() != null) {

            List<WorkExperience> workExperiences =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.WorkExperienceRequest workRequest
                    : request.getWorkExperiences()
            ) {

                WorkExperience work =
                        new WorkExperience();

                work.setCompany(
                        workRequest.getCompany()
                );

                work.setRole(
                        workRequest.getRole()
                );

                work.setStartDate(
                        workRequest.getStartDate()
                );

                work.setEndDate(
                        workRequest.getEndDate()
                );

                work.setDescription(
                        workRequest.getDescription()
                );

                work.setResume(resume);

                workExperiences.add(work);
            }

            resume.setWorkExperiences(
                    workExperiences
            );
        }


        // =====================================================
        // EDUCATIONS
        // =====================================================

        if (request.getEducations() != null) {

            List<Education> educations =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.EducationRequest educationRequest
                    : request.getEducations()
            ) {

                Education education =
                        new Education();

                education.setDegree(
                        educationRequest.getDegree()
                );

                education.setInstitution(
                        educationRequest.getInstitution()
                );

                education.setStartDate(
                        educationRequest.getStartDate()
                );

                education.setEndDate(
                        educationRequest.getEndDate()
                );

                education.setResume(resume);

                educations.add(education);
            }

            resume.setEducations(educations);
        }


        // =====================================================
        // SKILLS
        // =====================================================

        if (request.getSkills() != null) {

            List<Skill> skills =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.SkillRequest skillRequest
                    : request.getSkills()
            ) {

                Skill skill = new Skill();

                skill.setName(
                        skillRequest.getName()
                );

                skill.setProgress(
                        skillRequest.getProgress()
                );

                skill.setResume(resume);

                skills.add(skill);
            }

            resume.setSkills(skills);
        }


        // =====================================================
        // PROJECTS
        // =====================================================

        if (request.getProjects() != null) {

            List<Project> projects =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.ProjectRequest projectRequest
                    : request.getProjects()
            ) {

                Project project =
                        new Project();

                project.setTitle(
                        projectRequest.getTitle()
                );

                project.setDescription(
                        projectRequest.getDescription()
                );

                project.setGithub(
                        projectRequest.getGithub()
                );

                project.setLiveDemo(
                        projectRequest.getLiveDemo()
                );

                project.setResume(resume);

                projects.add(project);
            }

            resume.setProjects(projects);
        }


        // =====================================================
        // CERTIFICATIONS
        // =====================================================

        if (request.getCertifications() != null) {

            List<Certification> certifications =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.CertificationRequest certificationRequest
                    : request.getCertifications()
            ) {

                Certification certification =
                        new Certification();

                certification.setTitle(
                        certificationRequest.getTitle()
                );

                certification.setIssuer(
                        certificationRequest.getIssuer()
                );

                certification.setYear(
                        certificationRequest.getYear()
                );

                certification.setResume(resume);

                certifications.add(certification);
            }

            resume.setCertifications(
                    certifications
            );
        }


        // =====================================================
        // LANGUAGES
        // =====================================================

        if (request.getLanguages() != null) {

            List<Language> languages =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.LanguageRequest languageRequest
                    : request.getLanguages()
            ) {

                Language language =
                        new Language();

                language.setName(
                        languageRequest.getName()
                );

                language.setProgress(
                        languageRequest.getProgress()
                );

                language.setResume(resume);

                languages.add(language);
            }

            resume.setLanguages(languages);
        }


        // =====================================================
        // INTERESTS
        // =====================================================

        if (request.getInterests() != null) {

            List<Interest> interests =
                    new ArrayList<>();

            for (
                    CreateResumeRequest.InterestRequest interestRequest
                    : request.getInterests()
            ) {

                Interest interest =
                        new Interest();

                interest.setName(
                        interestRequest.getName()
                );

                interest.setResume(resume);

                interests.add(interest);
            }

            resume.setInterests(interests);
        }


        // =====================================================
        // SAVE
        // =====================================================

        Resume savedResume =
                resumeRepo.save(resume);

        return mapToResponse(savedResume);
    }


    // =========================================================
    // DELETE RESUME
    // =========================================================

    public void deleteResume(
            String id,
            user user) {

        Resume resume =
                resumeRepo
                        .findByUserIdAndId(
                                user.getId(),
                                id
                        )
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Resume not found"
                                )
                        );

        resumeRepo.delete(resume);
    }


    // =========================================================
    // ENTITY → RESPONSE DTO
    // =========================================================

    private ResumeResponse mapToResponse(
            Resume resume) {

        ResumeResponse response =
                new ResumeResponse();


        // =====================================================
        // BASIC
        // =====================================================

        response.setId(
                resume.getId()
        );

        response.setUserId(
                resume.getUserId()
        );

        response.setTitle(
                resume.getTitle()
        );

        response.setThumbnailLink(
                resume.getThumbnailLink()
        );


        // =====================================================
        // TEMPLATE
        // =====================================================

        if (resume.getTemplate() != null) {

            ResumeResponse.TemplateResponse templateResponse =
                    new ResumeResponse.TemplateResponse();

            templateResponse.setId(
                    resume.getTemplate().getId()
            );

            templateResponse.setTheme(
                    resume.getTemplate().getTheme()
            );

            templateResponse.setColorPalette(
                    resume.getTemplate()
                            .getColorPalette()
            );

            response.setTemplate(
                    templateResponse
            );
        }


        // =====================================================
        // PROFILE INFO
        // =====================================================

        if (resume.getProfileInfo() != null) {

            ResumeResponse.ProfileInfoResponse profileResponse =
                    new ResumeResponse.ProfileInfoResponse();

            profileResponse.setId(
                    resume.getProfileInfo().getId()
            );

            profileResponse.setFullname(
                    resume.getProfileInfo().getFullname()
            );

            profileResponse.setDesignation(
                    resume.getProfileInfo().getDesignation()
            );

            profileResponse.setProfilePreviewUrl(
                    resume.getProfileInfo()
                            .getProfilePreviewUrl()
            );

            profileResponse.setSummary(
                    resume.getProfileInfo().getSummary()
            );

            response.setProfileInfo(
                    profileResponse
            );
        }


        // =====================================================
        // CONTACT INFO
        // =====================================================

        if (resume.getContactInfo() != null) {

            ResumeResponse.ContactInfoResponse contactResponse =
                    new ResumeResponse.ContactInfoResponse();

            contactResponse.setId(
                    resume.getContactInfo().getId()
            );

            contactResponse.setEmail(
                    resume.getContactInfo().getEmail()
            );

            contactResponse.setPhone(
                    resume.getContactInfo().getPhone()
            );

            contactResponse.setLocation(
                    resume.getContactInfo().getLocation()
            );

            contactResponse.setLinkedIn(
                    resume.getContactInfo().getLinkedIn()
            );

            contactResponse.setGithub(
                    resume.getContactInfo().getGithub()
            );

            contactResponse.setWebsite(
                    resume.getContactInfo().getWebsite()
            );

            response.setContactInfo(
                    contactResponse
            );
        }


        // =====================================================
        // WORK EXPERIENCES
        // =====================================================

        if (resume.getWorkExperiences() != null) {

            List<ResumeResponse.WorkExperienceResponse>
                    workResponses =
                    resume.getWorkExperiences()
                            .stream()
                            .map(work -> {

                                ResumeResponse.WorkExperienceResponse workResponse =
                                        new ResumeResponse.WorkExperienceResponse();

                                workResponse.setId(
                                        work.getId()
                                );

                                workResponse.setCompany(
                                        work.getCompany()
                                );

                                workResponse.setRole(
                                        work.getRole()
                                );

                                workResponse.setStartDate(
                                        work.getStartDate()
                                );

                                workResponse.setEndDate(
                                        work.getEndDate()
                                );

                                workResponse.setDescription(
                                        work.getDescription()
                                );

                                return workResponse;
                            })
                            .collect(Collectors.toList());

            response.setWorkExperiences(
                    workResponses
            );
        }


        // =====================================================
        // EDUCATIONS
        // =====================================================

        if (resume.getEducations() != null) {

            List<ResumeResponse.EducationResponse>
                    educationResponses =
                    resume.getEducations()
                            .stream()
                            .map(education -> {

                                ResumeResponse.EducationResponse educationResponse =
                                        new ResumeResponse.EducationResponse();

                                educationResponse.setId(
                                        education.getId()
                                );

                                educationResponse.setDegree(
                                        education.getDegree()
                                );

                                educationResponse.setInstitution(
                                        education.getInstitution()
                                );

                                educationResponse.setStartDate(
                                        education.getStartDate()
                                );

                                educationResponse.setEndDate(
                                        education.getEndDate()
                                );

                                return educationResponse;
                            })
                            .collect(Collectors.toList());

            response.setEducations(
                    educationResponses
            );
        }


        // =====================================================
        // SKILLS
        // =====================================================

        if (resume.getSkills() != null) {

            List<ResumeResponse.SkillResponse>
                    skillResponses =
                    resume.getSkills()
                            .stream()
                            .map(skill -> {

                                ResumeResponse.SkillResponse skillResponse =
                                        new ResumeResponse.SkillResponse();

                                skillResponse.setId(
                                        skill.getId()
                                );

                                skillResponse.setName(
                                        skill.getName()
                                );

                                skillResponse.setProgress(
                                        skill.getProgress()
                                );

                                return skillResponse;
                            })
                            .collect(Collectors.toList());

            response.setSkills(
                    skillResponses
            );
        }


        // =====================================================
        // PROJECTS
        // =====================================================

        if (resume.getProjects() != null) {

            List<ResumeResponse.ProjectResponse>
                    projectResponses =
                    resume.getProjects()
                            .stream()
                            .map(project -> {

                                ResumeResponse.ProjectResponse projectResponse =
                                        new ResumeResponse.ProjectResponse();

                                projectResponse.setId(
                                        project.getId()
                                );

                                projectResponse.setTitle(
                                        project.getTitle()
                                );

                                projectResponse.setDescription(
                                        project.getDescription()
                                );

                                projectResponse.setGithub(
                                        project.getGithub()
                                );

                                projectResponse.setLiveDemo(
                                        project.getLiveDemo()
                                );

                                return projectResponse;
                            })
                            .collect(Collectors.toList());

            response.setProjects(
                    projectResponses
            );
        }


        // =====================================================
        // CERTIFICATIONS
        // =====================================================

        if (resume.getCertifications() != null) {

            List<ResumeResponse.CertificationResponse>
                    certificationResponses =
                    resume.getCertifications()
                            .stream()
                            .map(certification -> {

                                ResumeResponse.CertificationResponse certificationResponse =
                                        new ResumeResponse.CertificationResponse();

                                certificationResponse.setId(
                                        certification.getId()
                                );

                                certificationResponse.setTitle(
                                        certification.getTitle()
                                );

                                certificationResponse.setIssuer(
                                        certification.getIssuer()
                                );

                                certificationResponse.setYear(
                                        certification.getYear()
                                );

                                return certificationResponse;
                            })
                            .collect(Collectors.toList());

            response.setCertifications(
                    certificationResponses
            );
        }


        // =====================================================
        // LANGUAGES
        // =====================================================

        if (resume.getLanguages() != null) {

            List<ResumeResponse.LanguageResponse>
                    languageResponses =
                    resume.getLanguages()
                            .stream()
                            .map(language -> {

                                ResumeResponse.LanguageResponse languageResponse =
                                        new ResumeResponse.LanguageResponse();

                                languageResponse.setId(
                                        language.getId()
                                );

                                languageResponse.setName(
                                        language.getName()
                                );

                                languageResponse.setProgress(
                                        language.getProgress()
                                );

                                return languageResponse;
                            })
                            .collect(Collectors.toList());

            response.setLanguages(
                    languageResponses
            );
        }


        // =====================================================
        // INTERESTS
        // =====================================================

        if (resume.getInterests() != null) {

            List<ResumeResponse.InterestResponse>
                    interestResponses =
                    resume.getInterests()
                            .stream()
                            .map(interest -> {

                                ResumeResponse.InterestResponse interestResponse =
                                        new ResumeResponse.InterestResponse();

                                interestResponse.setId(
                                        interest.getId()
                                );

                                interestResponse.setName(
                                        interest.getName()
                                );

                                return interestResponse;
                            })
                            .collect(Collectors.toList());

            response.setInterests(
                    interestResponses
            );
        }


        return response;
    }
}