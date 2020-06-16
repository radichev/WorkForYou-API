package com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editEducationDto;


import javax.validation.constraints.NotNull;

public class EditEducationDto {
    private EditCountryDto country;
    private String universityName;
    private EditTitleTypeDto titleType;
    private String educationSubject;
    private int graduationYear;

    public EditEducationDto() {
    }

    @NotNull
    public EditCountryDto getCountry() {
        return country;
    }

    public void setCountry(EditCountryDto country) {
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
    public EditTitleTypeDto getTitleType() {
        return titleType;
    }

    public void setTitleType(EditTitleTypeDto titleType) {
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
