package com.radichev.workforyou.model.viewModels.getUserProfileDetails;

import com.radichev.workforyou.model.dtos.CertificateDto.CertificateDto;
import com.radichev.workforyou.model.dtos.EducationDto.EducationDto;
import com.radichev.workforyou.model.dtos.LanguageDto.LanguageDto;
import com.radichev.workforyou.model.dtos.PictureDto.PictureDto;
import com.radichev.workforyou.model.dtos.SkillDto.SkillDto;
import com.radichev.workforyou.model.dtos.WorkSphereDto.WorkSphereDto;

import java.util.Set;

public class UserProfileDetailsViewModel {
    private String firstName;
    private String lastName;
    private String description;
    private String personalWebsite;
    private PictureDto profilePicture;
    private String country;
    private String email;
    private Set<LanguageDto> languages;
    private Set<WorkSphereDto> workSpheres;
    private Set<SkillDto> skills;
    private Set<EducationDto> educations;
    private Set<CertificateDto> certificates;

    public UserProfileDetailsViewModel() {
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

    public PictureDto getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(PictureDto profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<LanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<LanguageDto> languages) {
        this.languages = languages;
    }

    public Set<WorkSphereDto> getWorkSpheres() {
        return workSpheres;
    }

    public void setWorkSpheres(Set<WorkSphereDto> workSpheres) {
        this.workSpheres = workSpheres;
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
