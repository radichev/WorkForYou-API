package com.radichev.workforyou.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "skills")
public class Skill extends BaseEntity {

    private String skill;
    private SkillLevel skillLevel;

    public Skill() {
    }

    @Column(nullable = false)
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    //May have to change the Relation
    @NotNull
    @ManyToOne
    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }
}
