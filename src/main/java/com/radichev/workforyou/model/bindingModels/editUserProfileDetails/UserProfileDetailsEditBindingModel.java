package com.radichev.workforyou.model.bindingModels.editUserProfileDetails;

import com.radichev.workforyou.model.dtos.SkillDto.SkillDto;
import com.radichev.workforyou.model.dtos.CertificateDto.CertificateDto;
import com.radichev.workforyou.model.dtos.EducationDto.EducationDto;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageDto;

import java.util.Set;

public class UserProfileDetailsEditBindingModel {
    private String id;
    private String firstName;
    private String lastName;
    private String description;
    private String personalWebsite;
    private String profilePicture;
    private String country;
    private String email;
    private Set<LanguageDto> languages;
    private Set<SkillDto> skills;
    private Set<EducationDto> educations;
    private Set<CertificateDto> certificates;

    public UserProfileDetailsEditBindingModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
