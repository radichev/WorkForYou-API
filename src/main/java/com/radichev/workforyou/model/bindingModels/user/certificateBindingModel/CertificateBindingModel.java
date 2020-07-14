package com.radichev.workforyou.model.bindingModels.user.certificateBindingModel;

public class CertificateBindingModel {
    private String certificateSubject;
    private String awardedFrom;
    private int graduationYear;

    public CertificateBindingModel() {
    }

    public String getCertificateSubject() {
        return certificateSubject;
    }

    public void setCertificateSubject(String certificateSubject) {
        this.certificateSubject = certificateSubject;
    }

    public String getAwardedFrom() {
        return awardedFrom;
    }

    public void setAwardedFrom(String awardedFrom) {
        this.awardedFrom = awardedFrom;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }
}
