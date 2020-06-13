package com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels;


import com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.SkillBindingModels.SkillBindingModel;
import com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.certificateBindingModels.CertificateBindingModel;
import com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.educationBindingModels.EducationBindingModel;
import com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.languageBindingModels.LanguageBindingModel;
import com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.pictureBindingModels.PictureBindingModel;
import com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.workSphereBindingModels.WorkSphereBindingModel;

public class UserProfileDetailsBindingModel {
    private String firstName;
    private String lastName;
    private String description;
    private String personalWebsite;
    private String country;
    private PictureBindingModel pictureBindingModel;
    private LanguageBindingModel languageBindingModels;
    private WorkSphereBindingModel workSphereBindingModels;
    private SkillBindingModel skillBindingModels;
    private EducationBindingModel educationBindingModels;
    private CertificateBindingModel certificateBindingModels;

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

    public PictureBindingModel getPictureBindingModel() {
        return pictureBindingModel;
    }

    public void setPictureBindingModel(PictureBindingModel pictureBindingModel) {
        this.pictureBindingModel = pictureBindingModel;
    }

    public LanguageBindingModel getLanguageBindingModels() {
        return languageBindingModels;
    }

    public void setLanguageBindingModels(LanguageBindingModel languageBindingModels) {
        this.languageBindingModels = languageBindingModels;
    }

    public WorkSphereBindingModel getWorkSphereBindingModels() {
        return workSphereBindingModels;
    }

    public void setWorkSphereBindingModels(WorkSphereBindingModel workSphereBindingModels) {
        this.workSphereBindingModels = workSphereBindingModels;
    }

    public SkillBindingModel getSkillBindingModels() {
        return skillBindingModels;
    }

    public void setSkillBindingModels(SkillBindingModel skillBindingModels) {
        this.skillBindingModels = skillBindingModels;
    }

    public EducationBindingModel getEducationBindingModels() {
        return educationBindingModels;
    }

    public void setEducationBindingModels(EducationBindingModel educationBindingModels) {
        this.educationBindingModels = educationBindingModels;
    }

    public CertificateBindingModel getCertificateBindingModels() {
        return certificateBindingModels;
    }

    public void setCertificateBindingModels(CertificateBindingModel certificateBindingModels) {
        this.certificateBindingModels = certificateBindingModels;
    }
}
