package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.WorkSphere;
import com.radichev.workforyou.model.dtos.WorkSphereDto.WorkSphereDto;

import java.util.Set;

public interface WorkSphereService {

    void initWorkSpheres();

    WorkSphere findWorkSphereById(String workSphereId);

    Set<WorkSphereDto> findAllWorkSpheres();
}
