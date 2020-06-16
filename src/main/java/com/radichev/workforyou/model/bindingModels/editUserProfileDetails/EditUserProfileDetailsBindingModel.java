package com.radichev.workforyou.model.bindingModels.editUserProfileDetails;


import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editSkillDto.EditSkillDto;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editCertificateDto.EditCertificateDto;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editEducationDto.EditEducationDto;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editLanguageDto.EditLanguageDto;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editPictureDto.EditPictureDto;
import com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editWorkSphereDto.EditWorkSphereDto;

import java.util.Set;

public class EditUserProfileDetailsBindingModel {
    private String firstName;
    private String lastName;
    private String description;
    private String personalWebsite;
    private EditPictureDto profilePicture;
    private String country;
    private String email;
    private Set<EditLanguageDto> languages;
    private Set<EditWorkSphereDto> workSpheres;
    private Set<EditSkillDto> skills;
    private Set<EditEducationDto> educations;
    private Set<EditCertificateDto> certificates;

    public EditUserProfileDetailsBindingModel() {
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

    public EditPictureDto getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(EditPictureDto profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<EditLanguageDto> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<EditLanguageDto> languages) {
        this.languages = languages;
    }

    public Set<EditWorkSphereDto> getWorkSpheres() {
        return workSpheres;
    }

    public void setWorkSpheres(Set<EditWorkSphereDto> workSpheres) {
        this.workSpheres = workSpheres;
    }

    public Set<EditSkillDto> getSkills() {
        return skills;
    }

    public void setSkills(Set<EditSkillDto> skills) {
        this.skills = skills;
    }

    public Set<EditEducationDto> getEducations() {
        return educations;
    }

    public void setEducations(Set<EditEducationDto> educations) {
        this.educations = educations;
    }

    public Set<EditCertificateDto> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<EditCertificateDto> certificates) {
        this.certificates = certificates;
    }
}
