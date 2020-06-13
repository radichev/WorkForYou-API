package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.SkillBindingModels;

import javax.validation.constraints.NotNull;

public class SkillBindingModel {
    private String skill;
    private SkillLevelBindingModel skillLevelBindingModel;

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
    public SkillLevelBindingModel getSkillLevelBindingModel() {
        return skillLevelBindingModel;
    }

    public void setSkillLevelBindingModel(SkillLevelBindingModel skillLevelBindingModel) {
        this.skillLevelBindingModel = skillLevelBindingModel;
    }
}
