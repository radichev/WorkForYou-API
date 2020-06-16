package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.workSphereBindingModels;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class WorkSphereBindingModel {
    String workSphere;
    Set<SubSphereBindingModel> subSpheres;

    public WorkSphereBindingModel() {
    }

    @NotNull
    public String getWorkSphere() {
        return workSphere;
    }

    public void setWorkSphere(String workSphere) {
        this.workSphere = workSphere;
    }

    @NotNull
    public Set<SubSphereBindingModel> getSubSpheres() {
        return subSpheres;
    }

    public void setSubSpheres(Set<SubSphereBindingModel> subSpheres) {
        this.subSpheres = subSpheres;
    }
}
