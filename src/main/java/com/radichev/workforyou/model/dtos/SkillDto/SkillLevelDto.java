package com.radichev.workforyou.model.dtos.SkillDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SkillLevelDto {
    private String id;
    private String skillLevel;

    public SkillLevelDto() {
    }

    @NotNull
    @NotBlank
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotNull
    @NotBlank
    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
