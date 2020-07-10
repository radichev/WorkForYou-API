package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.TitleType;
import com.radichev.workforyou.model.dtos.EducationDto.TitleTypeDto;
import com.radichev.workforyou.repository.TitleTypeRepository;
import com.radichev.workforyou.service.TitleTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleTypeServiceImpl implements TitleTypeService {
    private final TitleTypeRepository titleTypeRepository;
    private final ModelMapper modelMapper;

    public TitleTypeServiceImpl(TitleTypeRepository titleTypeRepository, ModelMapper modelMapper) {
        this.titleTypeRepository = titleTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initTitleTypes() {
        if (this.titleTypeRepository.count() == 0) {
            this.titleTypeRepository.saveAndFlush(new TitleType("Associate"));
            this.titleTypeRepository.saveAndFlush(new TitleType("Certificate"));
            this.titleTypeRepository.saveAndFlush(new TitleType("B.A."));
            this.titleTypeRepository.saveAndFlush(new TitleType("BArch"));
            this.titleTypeRepository.saveAndFlush(new TitleType("BM"));
            this.titleTypeRepository.saveAndFlush(new TitleType("BFA"));
            this.titleTypeRepository.saveAndFlush(new TitleType("B.Sc."));
            this.titleTypeRepository.saveAndFlush(new TitleType("M.A."));
            this.titleTypeRepository.saveAndFlush(new TitleType("M.B.A."));
            this.titleTypeRepository.saveAndFlush(new TitleType("M.Sc."));
            this.titleTypeRepository.saveAndFlush(new TitleType("Ph.D"));
        }
    }

    @Override
    public List<TitleTypeDto> findAllTitleTypes() {
        return this.titleTypeRepository.findAll()
                .stream()
                .map(titleType -> this.modelMapper.map(titleType, TitleTypeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TitleType findByTitleType(String titleType) {
        return this.titleTypeRepository.findByTitleType(titleType);
    }
}
