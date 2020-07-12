package com.radichev.workforyou.model.dtos.WorkSphereDto;

import javax.validation.constraints.NotNull;

public class SubSphereDto {
    private String id;
    private String subSphere;

    public SubSphereDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getSubSphere() {
        return subSphere;
    }

    public void setSubSphere(String subSphere) {
        this.subSphere = subSphere;
    }
}
