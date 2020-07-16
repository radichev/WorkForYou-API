package com.radichev.workforyou.service.serviceImpl;

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
    public void initSubSpheres() {
        
    }
}
