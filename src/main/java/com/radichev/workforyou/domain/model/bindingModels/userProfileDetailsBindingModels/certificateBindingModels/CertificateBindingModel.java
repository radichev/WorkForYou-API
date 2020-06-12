package com.radichev.workforyou.domain.model.bindingModels.userProfileDetailsBindingModels.certificateBindingModels;

import javax.validation.constraints.NotNull;

public class CertificateBindingModel {
    private String certificateSubject;
    private String awardedFrom;
    private int graduationYear;

    public CertificateBindingModel() {
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
