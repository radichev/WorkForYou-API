package com.radichev.workforyou.model.bindingModels.userProfileDetailsBindingModels.SkillBindingModels;

import javax.validation.constraints.NotNull;

public class SkillLevelBindingModel {
    String skillLevel;

    public SkillLevelBindingModel() {
    }

    @NotNull
    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
