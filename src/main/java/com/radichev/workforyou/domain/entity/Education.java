package com.radichev.workforyou.domain.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "educations")
@Where(clause="deleted = 0")
public class Education extends BaseEntity {
    private Country country;
    private String universityName;
    private TitleType titleType;
    private String educationSubject;
    private int graduationYear;
    private UserProfileDetails userProfileDetails;

    public Education() {
    }

    @ManyToOne
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Column(name = "university_name", nullable = false)
    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    @NotNull
    @ManyToOne(cascade = CascadeType.REMOVE)
    public TitleType getTitleType() {
        return titleType;
    }

    public void setTitleType(TitleType titleType) {
        this.titleType = titleType;
    }

    @Column(name = "education_subject", nullable = false)
    public String getEducationSubject() {
        return educationSubject;
    }

    public void setEducationSubject(String educationSubject) {
        this.educationSubject = educationSubject;
    }

    @Column(name = "graduation_year", nullable = false)
    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    @ManyToOne
    public UserProfileDetails getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(UserProfileDetails userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }
}
