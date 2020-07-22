package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.SubSphere;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.repository.SubSphereRepository;
import com.radichev.workforyou.service.SubSphereService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SubSphereServiceImpl implements SubSphereService {
    private final SubSphereRepository subSphereRepository;
    private final ModelMapper modelMapper;

    public SubSphereServiceImpl(SubSphereRepository subSphereRepository, ModelMapper modelMapper) {
        this.subSphereRepository = subSphereRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SubSphere findSubSphereById(String subSphereId) {
        return this.subSphereRepository.findById(subSphereId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("SubSphere not found with %s id.", subSphereId)));
    }
}
