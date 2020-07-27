package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.SubSphere;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.dtos.WorkSphereDto.SubSphereDto;
import com.radichev.workforyou.repository.SubSphereRepository;
import com.radichev.workforyou.service.SubSphereService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<SubSphereDto> findFiveSubSpheres() {
        return this.subSphereRepository.findFiveSubSpheres(PageRequest.of(0, 5))
                .stream()
                .map(subSphere -> this.modelMapper.map(subSphere, SubSphereDto.class))
                .collect(Collectors.toList());
    }
}
