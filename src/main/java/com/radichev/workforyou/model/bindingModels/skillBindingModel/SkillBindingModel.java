package com.radichev.workforyou.model.bindingModels.skillBindingModel;

import com.radichev.workforyou.model.dtos.SkillDto.SkillLevelDto;

public class SkillBindingModel {
    private String skill;
    private SkillLevelDto skillLevel;

    public SkillBindingModel() {
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public SkillLevelDto getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevelDto skillLevel) {
        this.skillLevel = skillLevel;
    }
}
