package com.radichev.workforyou.model.dtos.LanguageDto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LanguageLevelDto {
    private String id;
    private String languageLevel;

    public LanguageLevelDto() {
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
    public String getLanguageLevel() {
        return languageLevel;
    }

    public void setLanguageLevel(String languageLevel) {
        this.languageLevel = languageLevel;
    }
}
