package com.radichev.workforyou.model.dtos.SkillDto;

import javax.validation.constraints.NotNull;

public class SkillDto {
    private String id;
    private String skill;
    private SkillLevelDto skillLevel;

    public SkillDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
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
