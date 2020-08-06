package com.radichev.workforyou.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "language_levels")
public class LanguageLevel extends BaseEntity {
    private String languageLevel;

    public LanguageLevel() {
    }

    public LanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }

    @Column(nullable = false)
    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }
}
