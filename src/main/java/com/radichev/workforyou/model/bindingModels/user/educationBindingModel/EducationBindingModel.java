package com.radichev.workforyou.model.bindingModels.user.educationBindingModel;

import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import com.radichev.workforyou.model.dtos.EducationDto.TitleTypeDto;

public class EducationBindingModel {
    private CountryDto country;
    private String universityName;
    private TitleTypeDto titleType;
    private String educationSubject;
    private int graduationYear;

    public EducationBindingModel() {
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public TitleTypeDto getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleTypeDto titleType) {
        this.titleType = titleType;
    }

    public String getEducationSubject() {
        return educationSubject;
    }

    public void setEducationSubject(String educationSubject) {
        this.educationSubject = educationSubject;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }
}
