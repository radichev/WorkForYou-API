package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.LanguageLevel;
import com.radichev.workforyou.domain.entity.SkillLevel;
import com.radichev.workforyou.model.dtos.SkillDto.SkillDto;
import com.radichev.workforyou.model.dtos.SkillDto.SkillLevelDto;
import com.radichev.workforyou.repository.SkillLevelRepository;
import com.radichev.workforyou.service.SkillLevelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillLevelServiceImpl implements SkillLevelService {
    private final SkillLevelRepository skillLevelRepository;
    private final ModelMapper modelMapper;

    public SkillLevelServiceImpl(SkillLevelRepository skillLevelRepository, ModelMapper modelMapper) {
        this.skillLevelRepository = skillLevelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<SkillLevelDto> findAllSkillLevels() {
        return this.skillLevelRepository.findAll()
                .stream()
                .map(skillLevel -> this.modelMapper.map(skillLevel, SkillLevelDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SkillLevel findBySkillLevel(String skillLevel) {
        return this.skillLevelRepository.findBySkillLevel(skillLevel);
    }

    @Override
    public void initSkillLevels() {
        if (this.skillLevelRepository.count() == 0) {
            this.skillLevelRepository.saveAndFlush(new SkillLevel("Beginner"));
            this.skillLevelRepository.saveAndFlush(new SkillLevel("Intermediate"));
            this.skillLevelRepository.saveAndFlush(new SkillLevel("Expert"));
        }
    }
}
