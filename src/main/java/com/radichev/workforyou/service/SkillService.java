package com.radichev.workforyou.service;

import com.radichev.workforyou.domain.entity.Skill;
import com.radichev.workforyou.model.bindingModels.user.skillBindingModel.SkillBindingModel;
import com.radichev.workforyou.model.dtos.SkillDto.SkillDto;

public interface SkillService {

    SkillDto addSkill(SkillBindingModel skillBindingModel, String userId);

    Skill findSkillById(String skillId);

    void deleteSkillById(String skillId);

    SkillDto editSkillById(String skillId, SkillBindingModel skillBindingModel);
}
