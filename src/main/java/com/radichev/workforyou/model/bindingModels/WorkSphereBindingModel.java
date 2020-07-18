package com.radichev.workforyou.model.bindingModels;

import javax.validation.constraints.NotNull;

public class WorkSphereBindingModel {
    private String id;
    private String workSphere;
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
    public String getWorkSphere() {
        return workSphere;
    }

    public void setWorkSphere(String workSphere) {
        this.workSphere = workSphere;
    }

    @NotNull
    public SubSphereBindingModel getSubSphere() {
        return subSphere;
    }

    public void setSubSphere(SubSphereBindingModel subSphere) {
        this.subSphere = subSphere;
    }
}
