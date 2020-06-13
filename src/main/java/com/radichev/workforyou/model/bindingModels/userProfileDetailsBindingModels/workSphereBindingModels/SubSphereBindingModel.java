package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.workSphereBindingModels;

import javax.validation.constraints.NotNull;

public class SubSphereBindingModel {
    private String subSphere;

    public SubSphereBindingModel() {
    }

    @NotNull
    public String getSubSphere() {
        return subSphere;
    }

    public void setSubSphere(String subSphere) {
        this.subSphere = subSphere;
    }
}
