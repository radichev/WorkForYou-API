package com.radichev.workforyou.model.dtos.WorkSphereDto;

import javax.validation.constraints.NotNull;

public class SubSphereDto {
    private String id;
    private String subSphere;
    private String subSpherePicture;

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

    public String getSubSpherePicture() {
        return subSpherePicture;
    }

    public void setSubSpherePicture(String subSpherePicture) {
        this.subSpherePicture = subSpherePicture;
    }
}
