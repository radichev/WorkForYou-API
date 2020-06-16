package com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editWorkSphereDto;

import javax.validation.constraints.NotNull;

public class EditSubSphereDto {
    private String subSphere;

    public EditSubSphereDto() {
    }

    @NotNull
    public String getSubSphere() {
        return subSphere;
    }

    public void setSubSphere(String subSphere) {
        this.subSphere = subSphere;
    }
}
