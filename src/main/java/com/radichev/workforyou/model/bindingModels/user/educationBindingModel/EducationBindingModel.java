package com.radichev.workforyou.model.bindingModels.user.educationBindingModel;

import com.radichev.workforyou.model.dtos.EducationDto.CountryDto;
import com.radichev.workforyou.model.dtos.EducationDto.TitleTypeDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EducationBindingModel {
    private CountryDto country;
    private String universityName;
    private TitleTypeDto titleType;
    private String educationSubject;
    private int graduationYear;

    public EducationBindingModel() {
    }

    @NotNull
    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    @NotNull
    @NotBlank
    @Length(min = 4, max = 40)
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
    @NotBlank
    @Length(min = 3, max = 40)
    public String getEducationSubject() {
        return educationSubject;
    }

    public void setEducationSubject(String educationSubject) {
        this.educationSubject = educationSubject;
    }

    @NotNull
    @NotBlank
    @Min(1915)
    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }
}
