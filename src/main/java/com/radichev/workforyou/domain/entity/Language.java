package com.radichev.workforyou.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "languages")
public class Language extends BaseEntity {
    private String language;
    LanguageLevel languageLevel;

    public Language() {
    }

    @Column(nullable = false)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    //May have to change the Relation
    @ManyToOne
    public LanguageLevel getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(LanguageLevel languageLevel) {
        this.languageLevel = languageLevel;
    }
}
