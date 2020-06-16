package com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editSkillDto;

import javax.validation.constraints.NotNull;

public class EditSkillLevelDto {
    private String skillLevel;

    public EditSkillLevelDto() {
    }

    @NotNull
    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
