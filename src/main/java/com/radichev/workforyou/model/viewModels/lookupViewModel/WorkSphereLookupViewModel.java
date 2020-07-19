package com.radichev.workforyou.model.viewModels.lookupViewModel;

import com.radichev.workforyou.model.dtos.WorkSphereDto.WorkSphereDto;

import java.util.List;

public class WorkSphereLookupViewModel {
    private List<WorkSphereDto> workSpheres;

    public WorkSphereLookupViewModel() {
    }

    public List<WorkSphereDto> getWorkSpheres() {
        return workSpheres;
    }

    public void setWorkSpheres(List<WorkSphereDto> workSpheres) {
        this.workSpheres = workSpheres;
    }
}
