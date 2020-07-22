package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.SubSphere;
import com.radichev.workforyou.domain.entity.WorkSphere;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.dtos.WorkSphereDto.WorkSphereDto;
import com.radichev.workforyou.repository.WorkSphereRepository;
import com.radichev.workforyou.service.WorkSphereService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WorkSphereServiceImpl implements WorkSphereService {
    private final WorkSphereRepository workSphereRepository;
    private final ModelMapper modelMapper;

    public WorkSphereServiceImpl(WorkSphereRepository workSphereRepository, ModelMapper modelMapper) {
        this.workSphereRepository = workSphereRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initWorkSpheres() {
        if (this.workSphereRepository.count() == 0) {
            this.workSphereRepository.saveAndFlush(new WorkSphere("Programming & tech", Set.of(
                    new SubSphere("WordPress"),
                    new SubSphere("Web programming"),
                    new SubSphere("Game Development"),
                    new SubSphere("E-Commerce Development"),
                    new SubSphere("Mobile Development"),
                    new SubSphere("QA"),
                    new SubSphere("Databases"),
                    new SubSphere("Cybersecurity & Data Protection"),
                    new SubSphere("Desktop Applications"))));

            this.workSphereRepository.saveAndFlush(new WorkSphere("Graphics & Design", Set.of(
                    new SubSphere("Logo & Brand Identity"),
                    new SubSphere("Gaming"),
                    new SubSphere("Art & Illustration"),
                    new SubSphere("Visual Design"),
                    new SubSphere("Web & Mobile"),
                    new SubSphere("Merchandise"))));

            this.workSphereRepository.saveAndFlush(new WorkSphere("Video & Animation", Set.of(
                    new SubSphere("Video Editing"),
                    new SubSphere("Short Video Ads"),
                    new SubSphere("Animated GIFs"),
                    new SubSphere("Logo Animation"),
                    new SubSphere("3D Product Animation"),
                    new SubSphere("Visual Effects"),
                    new SubSphere("Intros & Outros"))));

            this.workSphereRepository.saveAndFlush(new WorkSphere("Digital Marketing", Set.of(
                    new SubSphere("Social Media Marketing"),
                    new SubSphere("SEO"),
                    new SubSphere("Marketing Strategy"),
                    new SubSphere("Content Marketing"),
                    new SubSphere("Web Analytics"),
                    new SubSphere("Social Media Advertising"),
                    new SubSphere("Influencer Marketing"),
                    new SubSphere("Mobile Marketing & Advertising"))));

            this.workSphereRepository.saveAndFlush(new WorkSphere("Music & Audio", Set.of(
                    new SubSphere("Songwriters"),
                    new SubSphere("Voice Over"),
                    new SubSphere("Producers & Composers"),
                    new SubSphere("Singers & Vocalists"),
                    new SubSphere("Mixing & Mastering"),
                    new SubSphere("Sound Effects"))));
        }
    }

    @Override
    public WorkSphere findWorkSphereById(String workSphereId) {
        return this.workSphereRepository.findById(workSphereId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("WorkSphere not found with %s id.", workSphereId)));
    }

    @Override
    public List<WorkSphereDto> findAllWorkSpheres() {
        return this.workSphereRepository.findAll()
                .stream()
                .map(workSphere -> this.modelMapper.map(workSphere, WorkSphereDto.class))
                .sorted(Comparator.comparing(WorkSphereDto::getWorkSphere))
                .collect(Collectors.toList());
    }
}
