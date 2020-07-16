package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.WorkSphere;
import com.radichev.workforyou.repository.WorkSphereRepository;
import com.radichev.workforyou.service.WorkSphereService;
import org.springframework.stereotype.Service;

@Service
public class WorkSphereServiceImpl implements WorkSphereService {
    private final WorkSphereRepository workSphereRepository;

    public WorkSphereServiceImpl(WorkSphereRepository workSphereRepository) {
        this.workSphereRepository = workSphereRepository;
    }

    @Override
    public void initWorkSpheres() {

    }

    @Override
    public WorkSphere findWorkSphereById(String workSphereId) {
        return this.workSphereRepository.findById(workSphereId).get();
    }
}
