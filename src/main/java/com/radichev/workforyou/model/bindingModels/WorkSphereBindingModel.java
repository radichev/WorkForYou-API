package com.radichev.workforyou.model.bindingModels;

import javax.validation.constraints.NotNull;

public class WorkSphereBindingModel {
    private String id;
    private SubSphereBindingModel subSphere;

    public WorkSphereBindingModel() {
    }

    @NotNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public SubSphereBindingModel getSubSphere() {
        return subSphere;
    }

    public void setSubSphere(SubSphereBindingModel subSphere) {
        this.subSphere = subSphere;
    }
}
