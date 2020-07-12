package com.radichev.workforyou.model.dtos.SkillDto;

import javax.validation.constraints.NotNull;

public class SkillLevelDto {
    private String id;
    private String skillLevel;

    public SkillLevelDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
