package com.radichev.workforyou.domain.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "languages")
@Where(clause="deleted = 0")
public class Language extends BaseEntity {
    private String language;
    LanguageLevel languageLevel;
    private Set<UserProfileDetails> userProfileDetails;

    public Language() {
    }

    @Column(nullable = false, unique = true)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    //May have to change the Relation
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    public LanguageLevel getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(LanguageLevel languageLevel) {
        this.languageLevel = languageLevel;
    }

    @ManyToMany
    public Set<UserProfileDetails> getUserProfileDetails() {
        return userProfileDetails;
    }

    public void setUserProfileDetails(Set<UserProfileDetails> userProfileDetails) {
        this.userProfileDetails = userProfileDetails;
    }
}
