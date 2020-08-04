package com.radichev.workforyou.model.bindingModels.user.certificateBindingModel;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CertificateBindingModel {
    private String certificateSubject;
    private String awardedFrom;
    private int graduationYear;

    public CertificateBindingModel() {
    }

    @NotNull
    @NotBlank
    @Length(min = 3, max = 40)
    public String getCertificateSubject() {
        return certificateSubject;
    }

    public void setCertificateSubject(String certificateSubject) {
        this.certificateSubject = certificateSubject;
    }

    @NotNull
    @NotBlank
    @Length(min = 3, max = 40)
    public String getAwardedFrom() {
        return awardedFrom;
    }

    public void setAwardedFrom(String awardedFrom) {
        this.awardedFrom = awardedFrom;
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
