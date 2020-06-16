package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.SkillBindingModels;

import javax.validation.constraints.NotNull;

public class SkillBindingModel {
    private String skill;
    private SkillLevelBindingModel skillLevel;

    public SkillBindingModel() {
    }

    @NotNull
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @NotNull
    public SkillLevelBindingModel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevelBindingModel skillLevel) {
        this.skillLevel = skillLevel;
    }
}
