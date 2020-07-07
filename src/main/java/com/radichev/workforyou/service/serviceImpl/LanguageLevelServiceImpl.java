package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.model.dtos.LanguageDto.LanguageLevelDto;
import com.radichev.workforyou.repository.LanguageLevelRepository;
import com.radichev.workforyou.service.LanguageLevelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LanguageLevelServiceImpl implements LanguageLevelService {
    private final LanguageLevelRepository languageLevelRepository;
    private final ModelMapper modelMapper;

    public LanguageLevelServiceImpl(LanguageLevelRepository languageLevelRepository, ModelMapper modelMapper) {
        this.languageLevelRepository = languageLevelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<LanguageLevelDto> findAllLanguageLevels() {
        return this.languageLevelRepository.findAll()
                .stream()
                .map(languageLevel -> this.modelMapper.map(languageLevel, LanguageLevelDto.class))
                .collect(Collectors.toList());
    }
}
