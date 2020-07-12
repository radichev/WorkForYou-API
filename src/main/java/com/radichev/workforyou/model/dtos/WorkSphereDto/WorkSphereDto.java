package com.radichev.workforyou.model.dtos.WorkSphereDto;

import javax.validation.constraints.NotNull;
import java.util.Set;

public class WorkSphereDto {
    private String id;
    String workSphere;
    Set<SubSphereDto> subSpheres;

    public WorkSphereDto() {
    }

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
    public Set<SubSphereDto> getSubSpheres() {
        return subSpheres;
    }

    public void setSubSpheres(Set<SubSphereDto> subSpheres) {
        this.subSpheres = subSpheres;
    }
}
