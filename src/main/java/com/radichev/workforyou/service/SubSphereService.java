package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.SubSphere;

public interface SubSphereService {

    void initSubSpheres();

    SubSphere findSubSphereById(String subSphereId);
}
