package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.educationBindingModels;


import javax.validation.constraints.NotNull;

public class EducationBindingModel {
    private CountryBindingModel countryBindingModel;
    private String universityName;
    private TitleTypeBindingModel titleTypeBindingModel;
    private String educationSubject;
    private int graduationYear;

    public EducationBindingModel() {
    }

    @NotNull
    public CountryBindingModel getCountryBindingModel() {
        return countryBindingModel;
    }

    public void setCountryBindingModel(CountryBindingModel countryBindingModel) {
        this.countryBindingModel = countryBindingModel;
    }

    @NotNull
    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @NotNull
    public TitleTypeBindingModel getTitleTypeBindingModel() {
        return titleTypeBindingModel;
    }

    public void setTitleTypeBindingModel(TitleTypeBindingModel titleTypeBindingModel) {
        this.titleTypeBindingModel = titleTypeBindingModel;
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
