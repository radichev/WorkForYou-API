package com.radichev.workforyou.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "certificates")
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

    @ManyToOne
    public UserProfileDetails getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(UserProfileDetails userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }

}
