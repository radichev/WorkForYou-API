package com.radichev.workforyou.model.viewModels.userProfileDetails;

import com.radichev.workforyou.model.dtos.CertificateDto.CertificateDto;
import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import com.radichev.workforyou.model.dtos.EducationDto.EducationDto;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageDto;
import com.radichev.workforyou.model.dtos.SkillDto.SkillDto;
import com.radichev.workforyou.model.viewModels.jobViewModels.JobViewModel;

import java.time.LocalDate;
import java.util.Set;

public class UserProfileDetailsViewModel {
    private String id;
    private String userId;
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private String personalWebsite;
    private String profilePicture;
    private CountryDto country;
    private Boolean hasCompletedAccountSetup;
    private String email;
    private LocalDate createdDate;
    private Set<LanguageDto> languages;
    private Set<SkillDto> skills;
    private Set<EducationDto> educations;
    private Set<CertificateDto> certificates;
    private Set<JobViewModel> jobs;

    public UserProfileDetailsViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPersonalWebsite() {
        return personalWebsite;
    }

    public void setPersonalWebsite(String personalWebsite) {
        this.personalWebsite = personalWebsite;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public Boolean getHasCompletedAccountSetup() {
        return hasCompletedAccountSetup;
    }

    public void setHasCompletedAccountSetup(Boolean hasCompletedAccountSetup) {
        this.hasCompletedAccountSetup = hasCompletedAccountSetup;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<LanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<LanguageDto> languages) {
        this.languages = languages;
    }

    public Set<SkillDto> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillDto> skills) {
        this.skills = skills;
    }

    public Set<EducationDto> getEducations() {
        return educations;
    }

    public void setEducations(Set<EducationDto> educations) {
        this.educations = educations;
    }

    public Set<CertificateDto> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<CertificateDto> certificates) {
        this.certificates = certificates;
    }

    public Set<JobViewModel> getJobs() {
        return jobs;
    }

    public void setJobs(Set<JobViewModel> jobs) {
        this.jobs = jobs;
    }
}
