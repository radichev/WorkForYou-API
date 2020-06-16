package com.radichev.workforyou.model.bindingModels.editUserProfileDetails.editSkillDto;

import javax.validation.constraints.NotNull;

public class EditSkillDto {
    private String skill;
    private EditSkillLevelDto skillLevel;

    public EditSkillDto() {
    }

    @NotNull
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @NotNull
    public EditSkillLevelDto getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(EditSkillLevelDto skillLevel) {
        this.skillLevel = skillLevel;
    }
}
