package com.radichev.workforyou.model.dtos.SkillDto;

import javax.validation.constraints.NotNull;

public class SkillLevelDto {
    private String skillLevel;

    public SkillLevelDto() {
    }

    @NotNull
    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
