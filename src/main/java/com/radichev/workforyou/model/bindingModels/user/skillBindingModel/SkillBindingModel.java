package com.radichev.workforyou.model.bindingModels.user.skillBindingModel;

import com.radichev.workforyou.model.dtos.SkillDto.SkillLevelDto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SkillBindingModel {
    private String skill;
    private SkillLevelDto skillLevel;

    public SkillBindingModel() {
    }

    @NotNull
    @NotBlank
    @Length(min = 3, max = 30)
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @NotNull
    public SkillLevelDto getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevelDto skillLevel) {
        this.skillLevel = skillLevel;
    }
}
