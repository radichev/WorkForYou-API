package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.workSphereBindingModels;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class WorkSphereBindingModel {
    String workSphere;
    Set<SubSphereBindingModel> subSphereBindingModels;

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
    public Set<SubSphereBindingModel> getSubSphereBindingModels() {
        return subSphereBindingModels;
    }

    public void setSubSphereBindingModels(Set<SubSphereBindingModel> subSphereBindingModels) {
        this.subSphereBindingModels = subSphereBindingModels;
    }
}
