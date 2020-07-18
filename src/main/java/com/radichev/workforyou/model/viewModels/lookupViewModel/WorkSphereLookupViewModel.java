package com.radichev.workforyou.model.viewModels.lookupViewModel;

import com.radichev.workforyou.model.dtos.WorkSphereDto.WorkSphereDto;

import java.util.Set;

public class WorkSphereLookupViewModel {
    private Set<WorkSphereDto> workSpheres;

    public WorkSphereLookupViewModel() {
    }

    public Set<WorkSphereDto> getWorkSpheres() {
        return workSpheres;
    }

    public void setWorkSpheres(Set<WorkSphereDto> workSpheres) {
        this.workSpheres = workSpheres;
    }
}
