package com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editWorkSphereDto;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class EditWorkSphereDto {
    String workSphere;
    Set<EditSubSphereDto> subSpheres;

    public EditWorkSphereDto() {
    }

    @NotNull
    public String getWorkSphere() {
        return workSphere;
    }

    public void setWorkSphere(String workSphere) {
        this.workSphere = workSphere;
    }

    @NotNull
    public Set<EditSubSphereDto> getSubSpheres() {
        return subSpheres;
    }

    public void setSubSpheres(Set<EditSubSphereDto> subSpheres) {
        this.subSpheres = subSpheres;
    }
}
