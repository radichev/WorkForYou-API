package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels;


import com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.SkillBindingModels.SkillBindingModel;
import com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.certificateBindingModels.CertificateBindingModel;
import com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.educationBindingModels.EducationBindingModel;
import com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.languageBindingModels.LanguageBindingModel;
import com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.pictureBindingModels.PictureBindingModel;
import com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.workSphereBindingModels.WorkSphereBindingModel;

import java.util.Set;

public class UserProfileDetailsBindingModel {
    private String firstName;
    private String lastName;
    private String description;
    private String personalWebsite;
    private PictureBindingModel profilePicture;
    private String country;
    private String email;
    private Set<LanguageBindingModel> languages;
    private Set<WorkSphereBindingModel> workSpheres;
    private Set<SkillBindingModel> skills;
    private Set<EducationBindingModel> educations;
    private Set<CertificateBindingModel> certificates;

    public UserProfileDetailsBindingModel() {
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

    public PictureBindingModel getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(PictureBindingModel profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Set<LanguageBindingModel> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<LanguageBindingModel> languages) {
        this.languages = languages;
    }

    public Set<WorkSphereBindingModel> getWorkSpheres() {
        return workSpheres;
    }

    public void setWorkSpheres(Set<WorkSphereBindingModel> workSpheres) {
        this.workSpheres = workSpheres;
    }

    public Set<SkillBindingModel> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillBindingModel> skills) {
        this.skills = skills;
    }

    public Set<EducationBindingModel> getEducations() {
        return educations;
    }

    public void setEducations(Set<EducationBindingModel> educations) {
        this.educations = educations;
    }

    public Set<CertificateBindingModel> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<CertificateBindingModel> certificates) {
        this.certificates = certificates;
    }
}
