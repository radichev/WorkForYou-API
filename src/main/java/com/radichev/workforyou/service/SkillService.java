package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.SkillBindingModel.SkillBindingModel;

public interface SkillService {

    void addSkill(SkillBindingModel skillBindingModel, String userId);
}
