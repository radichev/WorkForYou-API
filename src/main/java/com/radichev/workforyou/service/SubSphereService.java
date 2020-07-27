package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.SubSphere;
import com.radichev.workforyou.model.dtos.WorkSphereDto.SubSphereDto;

import java.util.List;

public interface SubSphereService {

    SubSphere findSubSphereById(String subSphereId);

    List<SubSphereDto> findFiveSubSpheres();
}
