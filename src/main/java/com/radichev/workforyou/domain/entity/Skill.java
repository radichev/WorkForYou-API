package com.radichev.workforyou.domain.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "skills")
public class Skill extends BaseEntity {

    private String skill;
    private SkillLevel skillLevel;
    private Set<UserProfileDetails> userProfileDetails;

    public Skill() {
    }

    @Column(nullable = false, unique = true)
    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    //May have to change the Relation
    @NotNull
    @ManyToOne(cascade = CascadeType.REMOVE)
    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    @ManyToMany
    public Set<UserProfileDetails> getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(Set<UserProfileDetails> userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }
}
