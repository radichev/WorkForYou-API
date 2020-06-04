package com.radichev.workforyou.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "skill_levels")
public class SkillLevel extends BaseEntity {
    String skillLevel;

    public SkillLevel() {
    }

    public SkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Column(name = "display_name", nullable = false)
    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }
}
