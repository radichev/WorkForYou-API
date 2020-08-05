package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.*;
import com.radichev.workforyou.exception.EntityNotFoundException;
import com.radichev.workforyou.model.bindingModels.user.skillBindingModel.SkillBindingModel;
import com.radichev.workforyou.model.dtos.SkillDto.SkillDto;
import com.radichev.workforyou.repository.SkillRepository;
import com.radichev.workforyou.service.SkillLevelService;
import com.radichev.workforyou.service.SkillService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public class SkillServiceImpl implements SkillService {
    private final SkillRepository skillRepository;
    private final ModelMapper modelMapper;
    private final UserProfileDetailsService userProfileDetailsService;
    private final SkillLevelService skillLevelService;

    public SkillServiceImpl(SkillRepository skillRepository, ModelMapper modelMapper, UserProfileDetailsService userProfileDetailsService, SkillLevelService skillLevelService) {
        this.skillRepository = skillRepository;
        this.modelMapper = modelMapper;
        this.userProfileDetailsService = userProfileDetailsService;
        this.skillLevelService = skillLevelService;
    }

    @Override
    public SkillDto addSkill(SkillBindingModel skillBindingModel, String userId) {
        UserProfileDetails userProfileDetails = this.userProfileDetailsService.findUserProfileDetailsById(userId);

        SkillLevel skillLevel = this.skillLevelService.findSkillLevelById(skillBindingModel.getSkillLevel().getId());

        Skill skill = this.skillRepository.findBySkill(skillBindingModel.getSkill());

        if (skill == null) {
            skill = this.modelMapper.map(skillBindingModel, Skill.class);
        }

        if (skill.getUserProfileDetails() != null) {
            skill.getUserProfileDetails().add(userProfileDetails);
        } else {
            skill.setUserProfileDetails(Set.of(userProfileDetails));
        }

        skill.setSkillLevel(skillLevel);

        return this.modelMapper.map(this.skillRepository.saveAndFlush(skill), SkillDto.class);
    }

    @Override
    public Skill findSkillById(String skillId) {
        return this.skillRepository.findById(skillId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format("Skill not found with %s id.", skillId)));
    }

    @Override
    public void deleteSkillById(String skillId) {
        Skill skill = this.findSkillById(skillId);
        skill.setDeleted(true);
        skill.setDeletedOn(LocalDate.now());

        this.skillRepository.save(skill);
    }
}
