package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.user.skillBindingModel.SkillBindingModel;
import com.radichev.workforyou.model.dtos.SkillDto.SkillDto;

public interface SkillService {

    SkillDto addSkill(SkillBindingModel skillBindingModel, String userId);
}
