package com.radichev.workforyou.model.dtos.WorkSphereDto;

import javax.validation.constraints.NotNull;

public class SubSphereDto {
    private String subSphere;

    public SubSphereDto() {
    }

    @NotNull
    public String getSubSphere() {
        return subSphere;
    }

    public void setSubSphere(String subSphere) {
        this.subSphere = subSphere;
    }
}
