package com.radichev.workforyou.domain.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "certificates")
@Where(clause="deleted = 0")
public class Certificate extends BaseEntity {
    private String certificateSubject;
    private String awardedFrom;
    private int graduationYear;
    private UserProfileDetails userProfileDetails;

    public Certificate() {
    }

    @Column(name = "certificate_subject", nullable = false)
    public String getCertificateSubject() {
        return certificateSubject;
    }

    public void setCertificateSubject(String certificateSubject) {
        this.certificateSubject = certificateSubject;
    }

    @Column(name = "awarded_from", nullable = false)
    public String getAwardedFrom() {
        return awardedFrom;
    }

    public void setAwardedFrom(String awardedFrom) {
        this.awardedFrom = awardedFrom;
    }

    @Column(name = "graduation_year", nullable = false)
    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    @NotNull
    @ManyToOne
    public UserProfileDetails getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(UserProfileDetails userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }

}
