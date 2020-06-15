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
    private PictureBindingModel pictureBindingModel;
    private String country;
    private String email;
    private Set<LanguageBindingModel> languageBindingModels;
    private Set<WorkSphereBindingModel> workSphereBindingModels;
    private Set<SkillBindingModel> skillBindingModels;
    private Set<EducationBindingModel> educationBindingModels;
    private Set<CertificateBindingModel> certificateBindingModels;

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

    public PictureBindingModel getPictureBindingModel() {
        return pictureBindingModel;
    }

    public void setPictureBindingModel(PictureBindingModel pictureBindingModel) {
        this.pictureBindingModel = pictureBindingModel;
    }

    public Set<LanguageBindingModel> getLanguageBindingModels() {
        return languageBindingModels;
    }

    public void setLanguageBindingModels(Set<LanguageBindingModel> languageBindingModels) {
        this.languageBindingModels = languageBindingModels;
    }

    public Set<WorkSphereBindingModel> getWorkSphereBindingModels() {
        return workSphereBindingModels;
    }

    public void setWorkSphereBindingModels(Set<WorkSphereBindingModel> workSphereBindingModels) {
        this.workSphereBindingModels = workSphereBindingModels;
    }

    public Set<SkillBindingModel> getSkillBindingModels() {
        return skillBindingModels;
    }

    public void setSkillBindingModels(Set<SkillBindingModel> skillBindingModels) {
        this.skillBindingModels = skillBindingModels;
    }

    public Set<EducationBindingModel> getEducationBindingModels() {
        return educationBindingModels;
    }

    public void setEducationBindingModels(Set<EducationBindingModel> educationBindingModels) {
        this.educationBindingModels = educationBindingModels;
    }

    public Set<CertificateBindingModel> getCertificateBindingModels() {
        return certificateBindingModels;
    }

    public void setCertificateBindingModels(Set<CertificateBindingModel> certificateBindingModels) {
        this.certificateBindingModels = certificateBindingModels;
    }
}
