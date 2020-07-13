package com.radichev.workforyou.service;

import com.radichev.workforyou.model.bindingModels.skillBindingModel.SkillBindingModel;

public interface SkillService {

    void addSkill(SkillBindingModel skillBindingModel, String userId);
}
