package com.radichev.workforyou.model.dtos.CertificateDto;

import javax.validation.constraints.NotNull;

public class CertificateDto {
    private String certificateSubject;
    private String awardedFrom;
    private int graduationYear;

    public CertificateDto() {
    }

    @NotNull
    public String getCertificateSubject() {
        return certificateSubject;
    }

    public void setCertificateSubject(String certificateSubject) {
        this.certificateSubject = certificateSubject;
    }

    @NotNull
    public String getAwardedFrom() {
        return awardedFrom;
    }

    public void setAwardedFrom(String awardedFrom) {
        this.awardedFrom = awardedFrom;
    }

    @NotNull
    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }
}
