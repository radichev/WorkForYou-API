package com.radichev.workforyou.model.dtos.EducationDto;


import javax.validation.constraints.NotNull;

public class EducationDto {
    private String id;
    private CountryDto country;
    private String universityName;
    private TitleTypeDto titleType;
    private String educationSubject;
    private int graduationYear;

    public EducationDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
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
    public TitleTypeDto getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleTypeDto titleType) {
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
