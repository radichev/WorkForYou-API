package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.educationBindingModels;


import javax.validation.constraints.NotNull;

public class EducationBindingModel {
    private CountryBindingModel country;
    private String universityName;
    private TitleTypeBindingModel titleType;
    private String educationSubject;
    private int graduationYear;

    public EducationBindingModel() {
    }

    @NotNull
    public CountryBindingModel getCountry() {
        return country;
    }

    public void setCountry(CountryBindingModel country) {
        this.country = country;
    }

    @NotNull
    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @NotNull
    public TitleTypeBindingModel getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleTypeBindingModel titleType) {
        this.titleType = titleType;
    }

    @NotNull
    public String getEducationSubject() {
        return educationSubject;
    }

    public void setEducationSubject(String educationSubject) {
        this.educationSubject = educationSubject;
    }

    @NotNull
    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }
}
