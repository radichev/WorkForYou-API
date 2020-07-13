package com.radichev.workforyou.service.serviceImpl;

import com.radichev.workforyou.domain.entity.*;
import com.radichev.workforyou.model.bindingModels.SkillBindingModel.SkillBindingModel;
import com.radichev.workforyou.repository.SkillRepository;
import com.radichev.workforyou.service.SkillLevelService;
import com.radichev.workforyou.service.SkillService;
import com.radichev.workforyou.service.UserProfileDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

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
    public void addSkill(SkillBindingModel skillBindingModel, String userId) {
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

        this.skillRepository.saveAndFlush(skill);
    }
}
