package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.WorkSphere;

public interface WorkSphereService {

    void initWorkSpheres();

    WorkSphere findWorkSphereById(String workSphereId);
}
